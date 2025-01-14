package ru.javabegin.micro.planner.utils

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class PlannerUtilsApplication

fun main(args: Array<String>) {
     runApplication<PlannerUtilsApplication>(*args)
}

