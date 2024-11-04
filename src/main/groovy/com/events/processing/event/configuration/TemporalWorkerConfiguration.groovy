package com.events.processing.event.configuration

import com.events.processing.event.workflows.EventProcessingWorkflowImpl
import com.events.processing.event.workflows.WorkerFactoryStarter
import com.events.processing.message.MessageActivityImpl
import com.events.processing.message.MessageService
import io.temporal.client.WorkflowClient
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.worker.Worker
import io.temporal.worker.WorkerFactory
import org.springframework.context.annotation.Bean

class TemporalWorkerConfiguration {

    @Bean
    WorkerFactory workerFactory(WorkflowClient workflowClient) {
        return WorkerFactory.newInstance(workflowClient)
    }

    @Bean
    Worker eventProcessingWorker(WorkerFactory workerFactory, MessageService messageService) {
        Worker worker = workerFactory.newWorker("EventProcessingQueue")
        worker.registerWorkflowImplementationTypes(EventProcessingWorkflowImpl)
        worker.registerActivitiesImplementations(new MessageActivityImpl(messageService))
        return worker
    }

    @Bean
    WorkerFactoryStarter workerFactoryStarter(WorkerFactory workerFactory) {
        return new WorkerFactoryStarter(workerFactory)
    }

    @Bean
    WorkflowServiceStubs workflowServiceStubs() {
        return WorkflowServiceStubs.newInstance()
    }

    @Bean
    WorkflowClient workflowClient(WorkflowServiceStubs service) {
        return WorkflowClient.newInstance(service)
    }
}
