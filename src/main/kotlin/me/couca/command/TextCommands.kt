package me.couca.command

import kotlinx.coroutines.flow.first
import me.couca.constant.Descriptions.AVATAR_DESC
import me.couca.constant.Descriptions.IP_DESC
import me.couca.constant.Descriptions.SAY_DESC
import me.couca.constant.Descriptions.TO_ASCII_DESC
import me.couca.constant.Errors.NOT_IMG_ERROR
import me.couca.constant.Errors.SAY_ERROR
import me.couca.exception.TrashBotException
import me.couca.integration.PasteBinClient
import me.couca.service.ImageService
import me.couca.util.Utility
import me.couca.util.respondColored
import me.jakejmattson.discordkt.arguments.EveryArg
import me.jakejmattson.discordkt.arguments.IntegerArg
import me.jakejmattson.discordkt.arguments.UrlArg
import me.jakejmattson.discordkt.arguments.UserArg
import me.jakejmattson.discordkt.commands.commands

fun textCommands(imgService: ImageService, pasteBin: PasteBinClient) = commands("Text") {
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

    execute(UserArg) { respondColored { title = Utility.randomIP() } }
  }

  command("to-ascii") {
    description = TO_ASCII_DESC

    execute(UrlArg) {
      respondColored { title = "Generating ASCII..." }
      val result = imgService.toAscii(args.first)
      if (result == null) {
        message.channel.getMessagesAfter(message.id, 1).first().delete()
        throw TrashBotException(channel, NOT_IMG_ERROR)
      }
      val resultUrl = pasteBin.uploadAndGet(result)
      respondColored { title = resultUrl }
      message.channel.getMessagesAfter(message.id, 1).first().delete()
    }
  }
}