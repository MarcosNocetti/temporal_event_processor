package com.events.processing.event.processors

import com.events.processing.event.EventProcessor
import com.events.processing.message.MessageService

class BotMessageProcessor implements EventProcessor{

    MessageService messageService

    BotMessageProcessor(MessageService messageService) {
        this.messageService = messageService
    }

    @Override
    boolean accept(String type) {
        return type == 'message'
    }

    @Override
    void process(Event event) {
        messageService.saveMessage(event.message)
    }
}
