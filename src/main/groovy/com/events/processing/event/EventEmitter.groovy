package com.events.processing.event

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class EventEmitter {
    private final RabbitTemplate rabbitTemplate

    EventEmitter(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate
    }

    void emitEvent(String message) {
        rabbitTemplate.convertAndSend("my_fanout_exchange", '',message)
        println "[x] Sent: ${message}"
    }
}