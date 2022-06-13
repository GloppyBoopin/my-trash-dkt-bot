package command

import me.jakejmattson.discordkt.api.dsl.commands

fun useless() = commands("useless") {
  command("say-nigger") {
    description = "say the n-word"
    execute {
      respond {
        title = "Nigger"
      }
    }
  }
}