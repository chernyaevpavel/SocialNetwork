package Models

class ElementNotFoundException(message: String) : RuntimeException(message)

interface Id {
    val id: Int
}
