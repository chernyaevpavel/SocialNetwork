package Models

class PostNotFoundException(message: String) : RuntimeException(message)
data class Post(
    val id: Int,
    val ownerId: Int,
    val fromId: Int,
    val date: Int,
    val text: String,
    val friendOnly: Boolean = false,
    val comments: Comments?,
    val likes: Likes?,
    val postType: PostType = PostType.POST,
    val canPin: Boolean = true,
    val isPinned: Boolean = false,
    val isFavorite: Boolean
)
