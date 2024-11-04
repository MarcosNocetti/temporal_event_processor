package com.events.processing.event


import com.events.processing.event.configuration.RabbitConfiguration
import com.events.processing.event.processors.Event
import com.events.processing.event.workflows.EventProcessingWorkflow
import com.fasterxml.jackson.databind.ObjectMapper
import groovy.util.logging.Slf4j
import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowOptions
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.workflow.Workflow
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Slf4j
class EventConsumer {

    EventProcessorFactory factory
    EventProcessingWorkflow workflow

    EventConsumer(EventProcessorFactory factory, EventProcessingWorkflow workflow) {
        this.factory = factory
        this.workflow = workflow
    }

    @RabbitListener(queues = RabbitConfiguration.QUEUE_NAME)
    void receiveEvent(String message) {
        log.info("Evento recebido ${message}")
        Event event = new ObjectMapper().readValue(message, Event)
        EventProcessor eventProcessor = factory.getEventProcessor(event.type)
        eventProcessor.process(event)
    }

    @RabbitListener(queues = RabbitConfiguration.QUEUE_NAME_TEMPORAL)
    void processWorkFlow(String message) {
        log.info("Evento recebido temporal ${message}")

        Event event = new ObjectMapper().readValue(message, Event)

        workflow.processEvent(event)
    }
}
