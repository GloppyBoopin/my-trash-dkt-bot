package me.couca.command

import dev.kord.common.entity.Snowflake
import kotlinx.coroutines.flow.toList
import me.couca.constant.Descriptions.INVITE_DESC
import me.couca.constant.Descriptions.THANOS_SNAP_DESC
import me.couca.constant.Errors.NO_PERMISSION_ERROR
import me.couca.constant.Errors.THANOS_SNAP_ERROR
import me.couca.exception.TrashBotException
import me.jakejmattson.discordkt.arguments.IntegerArg
import me.jakejmattson.discordkt.arguments.UserArg
import me.jakejmattson.discordkt.commands.commands
import me.jakejmattson.discordkt.dsl.permission

const val INVITE_LINK = "https://discord.gg/6eyp5bwBtF"

fun moderationCommands() = commands("Moderation") {
  permission("moderation") { users(guild!!.ownerId) }

  command("thanos-snap") {
    description = THANOS_SNAP_DESC

    execute(IntegerArg) {
      if (message.author?.id != Snowflake(267293217307754506)) {
        throw TrashBotException(channel, NO_PERMISSION_ERROR)
      }
      if (args.first < 1 || args.first > 10) {
        throw TrashBotException(channel, THANOS_SNAP_ERROR)
      }
      val messages = channel.getMessagesBefore(message.id, args.first).toList()
      message.delete()
      channel.bulkDelete(messages.map { it.id })
    }
  }

  command("invite") {
    description = INVITE_DESC

    execute(UserArg) {
      args.first.getDmChannel().createMessage(INVITE_LINK)
    }
  }
}