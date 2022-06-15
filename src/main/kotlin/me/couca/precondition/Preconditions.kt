package me.couca.precondition

import me.couca.constant.Errors.SELF_EXECUTION_ERROR
import me.couca.exception.TrashBotException
import me.jakejmattson.discordkt.dsl.precondition

fun botMessagePrecondition() = precondition {
  if (author.isBot) throw TrashBotException(SELF_EXECUTION_ERROR)
}