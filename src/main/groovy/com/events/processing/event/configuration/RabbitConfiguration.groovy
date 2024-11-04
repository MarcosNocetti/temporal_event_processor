package com.events.processing.event.configuration

import org.springframework.amqp.core.FanoutExchange
import org.springframework.amqp.core.BindingBuilder
import org.springframework.context.annotation.Bean
import org.springframework.amqp.core.Queue

class RabbitConfiguration {
    static final String QUEUE_NAME = "myQueue"
    static final String QUEUE_NAME_TEMPORAL = "myQueueTemporal"


    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("my_fanout_exchange")
    }

    @Bean
    Binding binding(FanoutExchange fanoutExchange, Queue queue) {
        return BindingBuilder.bind(queue).to(fanoutExchange) as Binding
    }

    @Bean
    Binding bindingTemporal(FanoutExchange fanoutExchange, Queue queue) {
        return BindingBuilder.bind(queue).to(fanoutExchange) as Binding
    }

    @Bean
    Queue queue() {
        return new Queue(QUEUE_NAME, true)
    }


    @Bean
    Queue queueTemporal() {
        return new Queue(QUEUE_NAME_TEMPORAL, true)
    }
}
