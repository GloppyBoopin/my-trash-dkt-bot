package command

import me.jakejmattson.discordkt.api.arguments.DoubleArg
import me.jakejmattson.discordkt.api.dsl.commands
import util.respondColored

fun mathCommands() = commands("Math") {
  command("+") {
    execute(DoubleArg, DoubleArg) {
      respondColored { title = (args.first + args.second).toString() }
    }
  }

  command("-") {
    execute(DoubleArg, DoubleArg) {
      respondColored { title = (args.first - args.second).toString() }
    }
  }

  command("*") {
    execute(DoubleArg, DoubleArg) {
      respondColored { title = (args.first * args.second).toString() }
    }
  }

  command("/") {
    execute(DoubleArg, DoubleArg) {
      respondColored { title = (args.first / args.second).toString() }
    }
  }
}