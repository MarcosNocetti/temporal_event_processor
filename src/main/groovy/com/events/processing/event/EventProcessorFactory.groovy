package com.events.processing.event

class EventProcessorFactory {
    private List<EventProcessor> processors

    EventProcessorFactory(List<EventProcessor> processors) {
        this.processors = processors
    }

    EventProcessor getEventProcessor(String messageType) {
        return processors.stream()
                .filter(processor -> processor.accept(messageType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported message type: " + messageType));
    }
}
