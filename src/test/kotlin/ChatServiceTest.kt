import Services.ChatService
import Services.ChatService.isHaveUnreadMessage
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ChatServiceTest {
    @Before
    fun clearBeforeTest() {
        ChatService.clear()
    }

    @Test
    fun createDirectMessage() {
        ChatService.createDirectMessage(1, 1)
        ChatService.createDirectMessage(1, 2)
        assertEquals(2, ChatService.directMessageCounter)
        ChatService.createDirectMessage(1, 2)
        assertEquals(2, ChatService.directMessageCounter)
    }

    @Test
    fun deleteDirectMessage() {
        val id = ChatService.createDirectMessage(1, 1)
        ChatService.deleteDirectMessage(id)
        assertEquals(0, ChatService.directMessageList.size)
    }

    @Test
    fun getChats() {
        ChatService.createDirectMessage(1, 1)
        ChatService.createDirectMessage(2, 2)
        val chats = ChatService.getChats()
        assertEquals(2, chats.size)
    }

    @Test
    fun haveUnreadMessage() {
        val id = ChatService.createDirectMessage(1, 1)
        val directMessage = ChatService.getDirectMessageById(id)
        val result = directMessage?.messageList?.isHaveUnreadMessage()
        assertTrue(result!!)
    }

    @Test
    fun createMessage() {
        ChatService.createMessage(1, 1, "text")
        assertEquals(1, ChatService.directMessageList.size)
    }

    @Test
    fun deleteMessage() {
        ChatService.createMessage(1, 1, "text")
        ChatService.createMessage(1, 1, "text2")
        ChatService.createMessage(1, 1, "text3")
        ChatService.deleteMessage(1, 1)
        val directMessage = ChatService.getDirectMessageById(1)
        assertEquals(2, directMessage?.messageList?.size)
    }

    @Test
    fun editMessage() {
        ChatService.createMessage(1, 1, "text")
        ChatService.createMessage(1, 1, "text 1")
        ChatService.createMessage(1, 1, "text 2")
        val result = ChatService.editMessage(1, 1, "new Text")
        assertTrue(result)
        val directMessage = ChatService.getDirectMessageById(1)
        assertEquals("new Text", directMessage?.messageList?.get(0)?.text)
    }
    @Test
    fun getUnreadChatsCount(){
        ChatService.createMessage(1, 1, "text")
        assertEquals(1, ChatService.getUnreadChatsCount())
        ChatService.createMessage(1, 2, "text")
        assertEquals(2, ChatService.getUnreadChatsCount())
    }
    @Test
    fun getNewMessages(){
        ChatService.createMessage(1, 1, "text")
        ChatService.createMessage(1, 1, "text")
        ChatService.createMessage(1, 1, "text")
        ChatService.createMessage(1, 1, "text")
        var messages = ChatService.getNewMessages(1,2)
        assertEquals(2, messages.size)
        messages = ChatService.getNewMessages(1,4)
        assertEquals(2, messages.size)
        messages = ChatService.getNewMessages(1,4)
        assertEquals(0, messages.size)
    }
}