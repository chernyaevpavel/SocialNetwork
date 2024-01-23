object WallService {
    var posts = emptyArray<Post>()
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
}