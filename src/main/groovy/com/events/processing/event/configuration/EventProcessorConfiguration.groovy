package com.events.processing.event.configuration

import com.events.processing.event.EventConsumer
import com.events.processing.event.EventProcessorFactory
import com.events.processing.event.processors.BotMessageProcessor
import com.events.processing.event.workflows.EventProcessingWorkflow
import com.events.processing.message.MessageService
import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowOptions
import io.temporal.serviceclient.WorkflowServiceStubs
import org.springframework.context.annotation.Bean

class EventProcessorConfiguration {

    @Bean
    EventProcessorFactory eventProcessorFactory(MessageService messageService){
        return new EventProcessorFactory([
                new BotMessageProcessor(messageService)
        ])
    }

    @Bean
    EventConsumer eventConsumer(EventProcessorFactory factory, WorkflowServiceStubs stubs, WorkflowClient client){
        WorkflowOptions workflowOptions = WorkflowOptions.newBuilder()
                .setTaskQueue("EventProcessingQueue")
                .build()

        EventProcessingWorkflow workflow = client.newWorkflowStub(
                EventProcessingWorkflow.class,
                workflowOptions
        )
        return new EventConsumer(factory, workflow)
    }
}
