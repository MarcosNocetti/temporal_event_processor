package com.events.processing.event.workflows

import io.temporal.worker.WorkerFactory
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
class WorkerFactoryStarter {
    private final WorkerFactory workerFactory;

    WorkerFactoryStarter(WorkerFactory workerFactory) {
        this.workerFactory = workerFactory;
    }

    @PostConstruct
    void startFactory() {
        workerFactory.start();
    }
}
