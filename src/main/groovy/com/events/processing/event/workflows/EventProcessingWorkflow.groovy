package com.events.processing.event.workflows

import com.events.processing.event.processors.Event
import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod

@WorkflowInterface
interface EventProcessingWorkflow {

    @WorkflowMethod
    void processEvent(Event event);
}
