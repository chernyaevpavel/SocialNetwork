object NoteService {
    var notesList: MutableList<Note> = mutableListOf()
    var commentsList: MutableList<Comment> = mutableListOf()
    private var deletedCommentsList: MutableList<Comment> = mutableListOf()
    private var notesCounter = 0
    private var commentCounter = 0

    fun clear() {
        notesList = mutableListOf()
        notesCounter = 0
        commentsList = mutableListOf()
        commentCounter = 0

    }

    fun add(
        title: String,
        text: String,
        privacy: Int = 0,
        commentPrivacy: Int = 0,
        privacyView: String = "all",
        privacyComment: String = "all"
    ): Int {
        notesCounter++
        val note = Note(notesCounter, 0, title, text, 0)
        notesList.add(note)
        return note.id
    }

    fun createComment(noteId: Int, ownerId: Int, replyTo: Int, message: String, guid: String): Int {
        val note: Note
        try {
            note = getById(notesList, noteId)
        } catch (err: ElementNotFoundException) {
            println(err)
            return 0
        }
        commentCounter++
        val comment = Comment(commentCounter, ownerId, 0, message, 0, 0, emptyArray(), noteId)
        note.commentsSet.add(comment)
        commentsList.add(comment)

        return comment.id
    }

    fun <T : Id> getById(list: MutableList<T>, id: Int): T {
        val element = list.find { it.id == id }
        if (element == null) throw ElementNotFoundException("Элемент с id $id не найден")
        return element
    }

    fun delete(noteId: Int): Boolean {
        val note: Note
        try {
            note = getById(notesList, noteId)
        } catch (err: ElementNotFoundException) {
            println("delete: $err")
            return false
        }
        for (comment: Comment in note.commentsSet) {
            deleteComment(comment.id)
        }
        notesList.remove(note)
        return true
    }

    fun deleteComment(commentId: Int): Boolean {
        val comment: Comment
        try {
            comment = getById(commentsList, commentId)
        } catch (err: ElementNotFoundException) {
            println("deleteComment: $err")
            return false
        }
        deletedCommentsList.add(comment)
        commentsList.remove(comment)
        return true
    }

    fun edit(noteId: Int, title: String, text: String): Boolean {
        val note: Note
        try {
            note = getById(notesList, noteId)
        } catch (err: ElementNotFoundException) {
            println("edit: $err")
            return false
        }
        note.title = title
        note.text = text
        return true
    }

    fun editComment(commentId: Int, message: String): Boolean {
        val comment: Comment
        try {
            comment = getById(commentsList, commentId)
        } catch (err: ElementNotFoundException) {
            println("editComment: $err")
            return false
        }
        comment.text = message
        return true
    }

    fun get(noteIds: List<Int>): List<Note> {
        val notesList: MutableList<Note> = mutableListOf()
        for (noteId in noteIds) {
            try {
                notesList.add(getById(notesList, noteId))
            } catch (err: ElementNotFoundException){
                println("get: $err")
            }

        }
        return notesList
    }
    fun getComments(noteId: Int, count: Int): List<Comment> {
        val note: Note
        var commentList: MutableList<Comment> = mutableListOf()
        try {
            note = getById(notesList, noteId)
        } catch (err: ElementNotFoundException) {
            println("getComments")
            return commentList
        }
        for (comment in note.commentsSet) {
            commentList.add(comment)
            if (commentList.size == count) return commentList
        }
        return commentList
    }
     fun restoreComment(commentId: Int): Boolean {
         val comment: Comment
         try {
             comment = getById(deletedCommentsList, commentId)
         } catch (err: ElementNotFoundException){
             println("restoreComment $err")
             return false
         }
         deletedCommentsList.remove(comment)
         commentsList.add(comment)
         return true
     }
}