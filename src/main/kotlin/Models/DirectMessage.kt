package Models

class DirectMessageNotFoundException(message: String) : RuntimeException(message)
data class DirectMessage(
    override val id: Int,
    val ownerId: Int,
    val companionId: Int,
    val messageList: MutableList<Message> = mutableListOf(),
    var messageCounter: Int = 0,
) : Id {
    fun getNewId(): Int {
        messageCounter++
        return messageCounter
    }
}
