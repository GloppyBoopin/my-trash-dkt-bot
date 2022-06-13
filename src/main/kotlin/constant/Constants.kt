package constant

object Descriptions {
  const val SAY_DESC = "duplicate input into chat"
  const val AVATAR_DESC = "get any user's avatar"
}

object Errors {
  const val NO_TOKEN_ERROR = "Expected the bot token as a command line argument"
  const val SELF_EXECUTION_ERROR = "Bots cannot execute commands posted by themselves"
  const val SAY_ERROR = "`say` error: Number of repetitions should be positive and not greater than 10"
}