package AttachmentContent

data class Video(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val description: String,
    val duration: Int,
    val image: Array<ImageSize>,
    val firstFrame: Array<FrameSize>,
    val date: Int,
    val addingDate: Int,
    val views: Int,
    val comments: Int,
    val canAdd: Boolean,
    val isPrivate: Boolean
)

data class ImageSize(
    val height: Int,
    val url: String,
    val width: Int,
    val withPadding: Int
)

data class FrameSize(
    val height: Int,
    val url: String,
    val width: Int,
)