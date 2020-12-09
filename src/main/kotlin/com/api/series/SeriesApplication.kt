package com.api.series

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = arrayOf(SecurityAutoConfiguration::class))
class SeriesApplication

fun main(args: Array<String>) {
	runApplication<SeriesApplication>(*args)
}
