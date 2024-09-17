package com.example.todoapp.controller.user

import com.example.todoapp.model.Role
import com.example.todoapp.model.User
import com.example.todoapp.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*


@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService
) {
    @PostMapping
    fun create(@RequestBody userRequest: UserRequest): UserResponse = userService.createUser(
        user = userRequest.toModel()
    )?.toResponse() ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot create user")

    @GetMapping
    fun getAll(): List<UserResponse> = userService.findAll().map { it.toResponse() }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): UserResponse =
        userService.findById(id)?.toResponse() ?:
        throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: UUID): ResponseEntity<Boolean> {
        val success = userService.deleteById(id)
        return if (success) ResponseEntity.noContent().build()
        else throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
    }


    private fun UserRequest.toModel(): User = User(
        id = UUID.randomUUID(),
        email = this.email,
        password = this.password,
        role = Role.USER,
    )

    private fun User.toResponse(): UserResponse = UserResponse(
        id = this.id,
        email = this.email,
    )
}