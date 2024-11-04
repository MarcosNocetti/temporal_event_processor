package com.events.processing.message

class MessageService {

    MessageRepository messageRepository

    MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository
    }

    void saveMessage(Message message){
        messageRepository.saveMessage(message)
    }
}
