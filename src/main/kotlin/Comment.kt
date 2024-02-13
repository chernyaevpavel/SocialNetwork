data class Comment(
    override val id: Int,
    val fromId: Int,
    val date: Int,
    var text: String,
    val replyToUser: Int,
    val replyToComment: Int,
    val parentsStack: Array<Comment>,
    val noteId: Int = 0
): Id