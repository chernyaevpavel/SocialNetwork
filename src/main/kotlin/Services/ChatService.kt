package Services

import Models.DirectMessage
import Models.DirectMessageNotFoundException
import Models.Message

object ChatService {
    //private -> public only for autotests
    public var directMessageCounter = 0
    public var directMessageList: MutableList<DirectMessage> = mutableListOf()

    fun clear() {
        ChatService.directMessageList = mutableListOf()
        ChatService.directMessageCounter = 0
    }

    fun createDirectMessage(ownerId: Int, companionId: Int): Int {
        val existsDirectMessage = directMessageList.find { it.ownerId == ownerId && it.companionId == companionId }
        if (existsDirectMessage != null) {
            return existsDirectMessage.id
        }
        directMessageCounter++
        val directMessage = DirectMessage(directMessageCounter, ownerId, companionId)
        directMessageList += directMessage
        return directMessage.id
    }

    fun deleteDirectMessage(directMessageId: Int): Boolean {
        val directMessage = getDirectMessageById(directMessageId) ?: return false
        return directMessageList.remove(directMessage)
    }

    fun getChats() = directMessageList

    fun getDirectMessageById(directMessageId: Int): DirectMessage? =
        directMessageList.find { it.id == directMessageId }

    private fun getMessageById(directMessage: DirectMessage, messageId: Int): Message? =
        directMessage.messageList.find { it.id == messageId }

    fun <E : DirectMessage> MutableList<E>.findDirectMessageId(ownerId: Int, companionId: Int): Int? {
        val directMessage = this.find { it.ownerId == ownerId && it.companionId == companionId }
        if (directMessage != null) return directMessage.id
        return null
    }

    fun <E : Message> MutableList<E>.isHaveUnreadMessage(): Boolean = this.none { !it.isRead }

    fun createMessage(ownerId: Int, companionId: Int, text: String): Int {
        val directMessageId =
            directMessageList.findDirectMessageId(ownerId, companionId) ?: createDirectMessage(ownerId, companionId)
        val directMessage = getDirectMessageById(directMessageId)
        if (directMessage == null) throw DirectMessageNotFoundException("Чат с ID $directMessageId не найден")
        val id = directMessage.getNewId()
        val message = Message(id, ownerId, text)
        directMessage.messageList.add(message)
        return id
    }

    fun deleteMessage(directMessageId: Int, messageId: Int): Boolean {
        val directMessage = getDirectMessageById(directMessageId) ?: return false
        val message = getMessageById(directMessage, messageId) ?: return false
        return directMessage.messageList.remove(message)
    }

    fun editMessage(directMessageId: Int, messageId: Int, newText: String): Boolean {
        val directMessage = getDirectMessageById(directMessageId) ?: return false
        val message = getMessageById(directMessage, messageId) ?: return false
        message.text = newText
        return true
    }

    fun getUnreadChatsCount(): Int = directMessageList.map { it.messageList.isHaveUnreadMessage() }.size

    private fun readMessages(messageList: List<Message>) {
        for (message in messageList) {
            message.isRead = true
        }
    }

    fun getNewMessages(companionId: Int, countMessage: Int): List<Message> {
        val directMessage = directMessageList.find { it.companionId == companionId }
            ?: throw DirectMessageNotFoundException("Чат с ID собедника $companionId не найден")
        val messages = directMessage.messageList.filter { !it.isRead }.take(countMessage)
        readMessages(messages)
        return messages
    }
}
