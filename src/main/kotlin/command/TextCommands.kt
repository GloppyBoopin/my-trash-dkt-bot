package command

import constant.Descriptions.AVATAR_DESC
import constant.Descriptions.SAY_DESC
import constant.Errors.SAY_ERROR
import me.jakejmattson.discordkt.api.arguments.EveryArg
import me.jakejmattson.discordkt.api.arguments.IntegerArg
import me.jakejmattson.discordkt.api.arguments.UserArg
import me.jakejmattson.discordkt.api.dsl.commands
import util.respondColored

fun textCommands() = commands("Text") {
  command("say") {
    description = SAY_DESC

    execute(IntegerArg, EveryArg) {
      if (args.first < 0 || args.first > 10) {
        respond(SAY_ERROR)
      }
      repeat(args.first) { respondColored { title = args.second } }
    }

    execute(EveryArg) { respondColored { title = args.first } }
  }

  command("avatar") {
    description = AVATAR_DESC

    execute(UserArg) { respondColored { image = args.first.avatar.url } }
  }
}