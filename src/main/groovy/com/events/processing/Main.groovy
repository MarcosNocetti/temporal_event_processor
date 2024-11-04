package com.events.processing

import com.events.processing.event.configuration.EventProcessorConfiguration
import com.events.processing.event.configuration.JdbcConfiguration
import com.events.processing.event.configuration.MessageConfiguration
import com.events.processing.event.configuration.TemporalWorkerConfiguration
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import([EventProcessorConfiguration, JdbcConfiguration, MessageConfiguration, TemporalWorkerConfiguration])
class Main {

	static void main(String[] args) {
		SpringApplication.run(Main, args)
	}

}
