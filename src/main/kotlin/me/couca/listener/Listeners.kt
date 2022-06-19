package me.couca.listener

import dev.kord.common.entity.Snowflake
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.event.message.ReactionAddEvent
import dev.kord.x.emoji.DiscordEmoji
import dev.kord.x.emoji.addReaction
import kotlinx.coroutines.flow.first
import me.couca.constant.Errors.NOT_IMG_ERROR
import me.couca.integration.PasteBinClient
import me.couca.service.ImageService
import me.couca.util.createEmbedColored
import me.jakejmattson.discordkt.dsl.listeners

private const val TO_ASCII_COMMAND = ".to-ascii"
private val FLAG_KZ = DiscordEmoji.Generic("\uD83C\uDDF0\uD83C\uDDFF")

fun defaultListeners(imgService: ImageService, pasteBin: PasteBinClient) = listeners {
  val listenedToMessage = Snowflake(986180756785229824)

  on<ReactionAddEvent> {
    if (messageId == listenedToMessage && userId == guild!!.asGuild().ownerId) message.addReaction(emoji)
  }

  //Bootleg command implementation
  on<MessageCreateEvent> {
    if (TO_ASCII_COMMAND == message.content.substring(0, TO_ASCII_COMMAND.length - 1)) {
      message.addReaction(FLAG_KZ)

      if (message.attachments.size == 1) {
        message.channel.createEmbedColored { title = "Generating ASCII..." }
        message.channel.getMessagesAfter(message.id, 1).first().delete()
        if (!message.attachments.elementAt(0).isImage) message.channel.createEmbedColored {
          title = NOT_IMG_ERROR
        }
        val result = imgService.toAscii(message.attachments.elementAt(0).url)
          ?: message.channel.createEmbedColored { title = NOT_IMG_ERROR }
        val resultUrl = pasteBin.uploadAndGet(result)
        message.channel.createEmbedColored { title = resultUrl }
      } else {
        message.channel.createEmbedColored {
          title = "Cannot execute $TO_ASCII_COMMAND with these arguments"
        }
      }

      val toDelete = message.channel.getMessagesAfter(message.id, 1).first()
      toDelete.delete()
    }
  }
}