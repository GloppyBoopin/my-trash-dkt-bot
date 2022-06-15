package me.couca.command

import dev.kord.common.entity.Snowflake
import kotlinx.coroutines.flow.toList
import me.couca.constant.Descriptions.THANOS_SNAP_DESC
import me.couca.constant.Errors.NO_PERMISSION_ERROR
import me.couca.constant.Errors.THANOS_SNAP_ERROR
import me.couca.exception.TrashBotException
import me.jakejmattson.discordkt.arguments.IntegerArg
import me.jakejmattson.discordkt.commands.commands

fun moderationCommands() = commands("Moderation") {
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
}