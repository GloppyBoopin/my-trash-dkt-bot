package me.couca.command

import dev.kord.common.entity.Snowflake
import kotlinx.coroutines.flow.toList
import me.couca.constant.Descriptions.AVATAR_DESC
import me.couca.constant.Descriptions.IP_DESC
import me.couca.constant.Descriptions.SAY_DESC
import me.couca.constant.Descriptions.THANOS_SNAP_DESC
import me.couca.constant.Errors.NO_PERMISSION_ERROR
import me.couca.constant.Errors.SAY_ERROR
import me.couca.constant.Errors.THANOS_SNAP_ERROR
import me.couca.exception.TrashBotException
import me.couca.util.Utility
import me.couca.util.respondColored
import me.jakejmattson.discordkt.arguments.EveryArg
import me.jakejmattson.discordkt.arguments.IntegerArg
import me.jakejmattson.discordkt.arguments.UserArg
import me.jakejmattson.discordkt.commands.commands

fun textCommands() = commands("Text") {
  command("say") {
    description = SAY_DESC

    execute(IntegerArg, EveryArg) {
      if (args.first < 1 || args.first > 10) {
        throw TrashBotException(channel, SAY_ERROR)
      }
      repeat(args.first) { respondColored { title = args.second } }
    }

    execute(EveryArg) { respondColored { title = args.first } }
  }

  command("avatar") {
    description = AVATAR_DESC

    execute(UserArg) { respondColored { image = args.first.avatar?.url } }
  }

  command("ip") {
    description = IP_DESC

    execute(UserArg) {
      respondColored { title = Utility.randomIP() }
    }

  }
}