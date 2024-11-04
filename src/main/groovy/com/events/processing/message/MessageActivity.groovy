package com.events.processing.message

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

@ActivityInterface
interface MessageActivity {

    @ActivityMethod
    void saveMessage(Message message)
}