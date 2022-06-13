package precondition

import constant.Errors.SELF_EXECUTION_ERROR
import exception.TrashBotException
import me.jakejmattson.discordkt.api.dsl.precondition

fun botMessagePrecondition() = precondition {
  if (author.isBot) throw TrashBotException(SELF_EXECUTION_ERROR)
}