data class Note (
    override val id: Int,
    val ownerId: Int,
    var title: String,
    var text: String,
    val date: Int,
    val comments: Int = 0,
    val readComments: Int = 0,
    val viewUrl: String = "",
    val privacyView: String = "",
    val canComment: Boolean = true,
    val textWiki: String = "",
    val commentsSet: MutableSet<Comment> = mutableSetOf()
): Id

