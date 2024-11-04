package com.events.processing.message

class MessageActivityImpl implements MessageActivity {

    private final MessageService messageService

    MessageActivityImpl(MessageService messageService) {
        this.messageService = messageService
    }

    @Override
    void saveMessage(Message message) {
        messageService.saveMessage(message)
    }
}