package com.example.todoapp

import com.example.todoapp.config.JwtProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan


@SpringBootApplication
//@ComponentScan(basePackages = ["com.example.todoapp.service", "com.example.todoapp.controller.auth"])
class TodoappApplication

fun main(args: Array<String>) {
	runApplication<TodoappApplication>(*args)
}
