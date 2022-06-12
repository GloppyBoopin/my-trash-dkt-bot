package exception

class TrashBotException : RuntimeException {

  constructor() : super()
  constructor(message: String) : super(message)
}