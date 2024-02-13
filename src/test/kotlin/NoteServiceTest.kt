import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class NoteServiceTest {
    @Before
    fun clearBeforeTest() {
        NoteService.clear()
    }

    @Test
    fun add() {
        val id = NoteService.add("test title", "test text")
        assertEquals(id, 1)
    }

    @Test
    fun createComment() {
        val id = NoteService.add("test title", "test text")
        val idComment = NoteService.createComment(id, 0, 0, "test comment", "")
        assertEquals(idComment, 1)

    }

    @Test
    fun deleteTrue() {
        val id = NoteService.add("test title", "test text")
        assertTrue(NoteService.delete(id))
    }

    @Test
    fun deleteFalse() {
        val result = NoteService.delete(5)
        assertFalse(result)
    }

    @Test
    fun deleteCommentTrue() {
        val noteId = NoteService.add("test title", "test text")
        val id = NoteService.createComment(noteId, 0, 0, "test comment", "", )
        assertTrue(NoteService.deleteComment(id))
    }

    @Test
    fun deleteCommentFalse() {
        val result = NoteService.deleteComment(5)
        assertFalse(result)
    }

    @Test
    fun edit() {
        val newTitle = "new Title"
        val newText = "new Text"
        val id = NoteService.add("test title", "test text")
        NoteService.edit(id, newTitle, newText)
        val note = NoteService.getById(NoteService.notesList, id)
        assertEquals(note.title, newTitle)
        assertEquals(note.text, newText)

    }

    @Test
    fun editComment() {
        val newComment = "new Comment"
        val noteId = NoteService.add("test title", "test text")
        val id = NoteService.createComment(noteId, 0, 0, "test comment", "", )
        NoteService.editComment(id, newComment)
        val comment = NoteService.getById(NoteService.commentsList, id)
        assertEquals(comment.text, newComment)
    }

    @Test(expected = ElementNotFoundException::class)
    fun shouldThrow() {
        NoteService.getById(NoteService.notesList, 10)
    }
}
