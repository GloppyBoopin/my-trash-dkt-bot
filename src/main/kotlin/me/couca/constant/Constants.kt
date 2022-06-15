package me.couca.constant

object Descriptions {
  const val SAY_DESC = "duplicate input into chat"
  const val AVATAR_DESC = "get any user's avatar"
  const val THANOS_SNAP_DESC = "thanos snap previous N messages"
  const val IP_DESC = "get IP of a user"
}

object Errors {
  const val NO_TOKEN_ERROR = "Expected the bot token as a command line argument"
  const val SELF_EXECUTION_ERROR = "Bots cannot execute commands posted by themselves"
  const val NO_PERMISSION_ERROR = "No permission to use this command"

  const val SAY_ERROR = "`say` error: Number of repetitions should be positive and not greater than 10"
  const val THANOS_SNAP_ERROR ="`thanos-snap` only can snap positive number of messages and not greater than 10"
}