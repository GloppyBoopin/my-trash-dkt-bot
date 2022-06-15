package me.couca.command

import me.jakejmattson.discordkt.arguments.DoubleArg
import me.jakejmattson.discordkt.commands.commands
import me.couca.util.respondColored

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