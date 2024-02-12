object WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()
    private var idCounter = 0

    fun add(post: Post): Post {
        idCounter++
        val postCopy = post.copy(id = idCounter)
        posts += postCopy
        return postCopy
    }

    fun update(post: Post): Boolean {
        for ((index, postIt) in posts.withIndex()) {
            if (postIt.id == post.id) {
                posts[index] = post
                return true
            }
        }
        return false
    }

    fun clear() {
        posts = emptyArray()
        idCounter = 0
    }

    fun createComment(postId: Int, comment: Comment): Comment {
        if (!isPostExists(postId)) {
            throw PostNotFoundException("Пост с id $postId не найден")
        }
        comments += comment
        return comment
    }

    private fun isPostExists(postId: Int): Boolean {
        for ((index, postIt) in posts.withIndex()) {
            if (postIt.id == postId) {
                return true
            }
        }
        return false
    }
}