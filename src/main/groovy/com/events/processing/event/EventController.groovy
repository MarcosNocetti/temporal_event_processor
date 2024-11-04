package com.events.processing.event


import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/events")
class EventController {
    private final EventEmitter eventEmitter

    EventController(EventEmitter eventEmitter) {
        this.eventEmitter = eventEmitter
    }

    @PostMapping
    ResponseEntity sendEvent(@RequestBody String message) {
        eventEmitter.emitEvent(message)
        return new ResponseEntity<>("foi", HttpStatus.OK)
    }
}
