import Models.*
import Services.WallService
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun add() {
        val post = Post(
            id = 0,
            ownerId = 123,
            fromId = 321,
            date = 548,
            text = "1 post",
            comments = Comments(),
            likes = Likes(),
            isFavorite = true
        )
        val newPost = WallService.add(post)

        assertTrue(newPost.id > 0)
    }

    @Test
    fun updateTrue() {
        val post = Post(
            id = 0,
            ownerId = 123,
            fromId = 321,
            date = 548,
            text = "1 post",
            comments = Comments(),
            likes = Likes(),
            isFavorite = true
        )
        WallService.add(post)

        val post1 = Post(
            id = 1,
            ownerId = 123,
            fromId = 321,
            date = 548,
            text = "3 post",
            comments = Comments(),
            likes = Likes(),
            isFavorite = true
        )
        assertTrue(WallService.update(post1))
    }

    @Test
    fun updateFalse() {
        val post = Post(
            id = 0,
            ownerId = 123,
            fromId = 321,
            date = 548,
            text = "1 post",
            comments = Comments(),
            likes = Likes(),
            isFavorite = true
        )
        WallService.add(post)

        val post1 = Post(
            id = 4,
            ownerId = 123,
            fromId = 321,
            date = 548,
            text = "3 post",
            comments = Comments(),
            likes = Likes(),
            isFavorite = true
        )
        assertFalse(WallService.update(post1))
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        val comment = Comment(1, 1, 1, "test", 1, 1, emptyArray())
        WallService.createComment(1, comment)
    }

    @Test
    fun addComment() {
        val post = Post(
            id = 1,
            ownerId = 123,
            fromId = 321,
            date = 548,
            text = "1 post",
            comments = Comments(),
            likes = Likes(),
            isFavorite = true
        )
        WallService.add(post)

        val comment = Comment(1, 1, 1, "test", 1, 1, emptyArray())
        val returnedComment = WallService.createComment(1, comment)

        assertEquals(returnedComment.id, 1)
    }

}