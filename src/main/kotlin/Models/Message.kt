package Models

data class Message(
    override val id: Int,
    val ownerId: Int,
    var text: String,
    var isRead: Boolean = false

): Id
