package Attachment

import AttachmentContent.*

interface Attachment {
    val type: String
}

data class AttachmentAudio(override val type: String, val audio: Audio) : Attachment

data class AttachmentFile(override val type: String, val file: File) : Attachment

data class AttachmentPhoto(override val type: String, val photo: Photo) : Attachment

data class AttachmentPresent(override val type: String, val present: Present) : Attachment

data class AttachmentVideo(override val type: String, val video: Video) : Attachment


