package com.events.processing.event

import com.events.processing.event.processors.Event

interface EventProcessor {
    boolean accept(String message)
    void process(Event message)
}
