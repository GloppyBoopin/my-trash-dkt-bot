package me.couca

import dev.kord.common.annotation.KordPreview
import dev.kord.common.entity.PresenceStatus
import dev.kord.gateway.Intents
import dev.kord.x.emoji.Emojis
import kotlinx.coroutines.flow.toList
import me.couca.constant.Errors.NO_TOKEN_ERROR
import me.couca.exception.TrashBotException
import me.couca.util.Utility
import me.couca.util.createEmbedColored
import me.jakejmattson.discordkt.dsl.CommandException
import me.jakejmattson.discordkt.dsl.ListenerException
import me.jakejmattson.discordkt.dsl.bot
import me.jakejmattson.discordkt.extensions.addField

@KordPreview
fun main(args: Array<String>) {

  val token = args.firstOrNull()
    ?: throw TrashBotException(NO_TOKEN_ERROR)

  bot(token) {
    prefix {
      "."
    }

    configure {
      theme = Utility.randomColor()
      commandReaction = Emojis.flagKz
      deleteInvocation = false
      intents = Intents.nonPrivileged
    }

    mentionEmbed {
      title = "Trash bot"
      color = Utility.randomColorK()

      author {
        with(it.author) {
          icon = avatar?.url
          name = tag
          url = avatar?.url
        }
      }

      thumbnail {
        url = it.discord.kord.getSelf().avatar?.url!!
      }

      footer {
        val versions = it.discord.versions
        text = "DiscordKt: ${versions.library} - Kord: ${versions.kord} - Kotlin: ${versions.kotlin}"
      }

      addField("Why you ping me", it.author.mention)
    }

    presence {
      status = PresenceStatus.DoNotDisturb
      playing(".help for help")
    }

    onStart {
      val guilds = kord.guilds.toList().joinToString { it.name }
      println("Servers: $guilds")
    }

    onException {
      if (exception is TrashBotException) {
        (exception as TrashBotException).channel?.createEmbedColored { title = exception.message }
        return@onException
      }

      when (this) {
        is CommandException -> event.channel.createEmbedColored { title = exception.message }
        is ListenerException -> println("Exception ${exception::class.simpleName}" +
            " in listener ${event::class.simpleName}: ${exception.message}")
      }
    }
  }
}