package com.events.processing.event.workflows

import com.events.processing.event.processors.Event
import com.events.processing.message.MessageActivity
import io.temporal.activity.ActivityOptions
import io.temporal.workflow.Workflow

import java.time.Duration;

class EventProcessingWorkflowImpl implements EventProcessingWorkflow {

    private final MessageActivity messageActivity

    EventProcessingWorkflowImpl() {
        ActivityOptions activityOptions = ActivityOptions.newBuilder()
                .setStartToCloseTimeout(Duration.ofSeconds(10))
                .build()

        this.messageActivity = Workflow.newActivityStub(MessageActivity.class, activityOptions)
    }
    @Override
    void processEvent(Event event) {
        if ("message" == event.type) {
            messageActivity.saveMessage(event.message)
        }
    }
}
