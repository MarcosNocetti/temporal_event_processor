package com.events.processing.event.configuration

import com.events.processing.message.MessageRepository
import com.events.processing.message.MessageService
import org.springframework.context.annotation.Bean

class MessageConfiguration {

    @Bean
    MessageService messageService(MessageRepository messageRepository){
        return new MessageService(messageRepository)
    }
}
