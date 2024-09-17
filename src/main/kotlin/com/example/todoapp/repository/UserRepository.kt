package com.example.todoapp.repository

import com.example.todoapp.model.Role
import com.example.todoapp.model.User
import org.springframework.data.jpa.domain.AbstractPersistable_.id

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository
import java.util.*


@Repository
class UserRepository(
    private val encoder: PasswordEncoder
) {
    private var users = mutableListOf(
        User(
            id = UUID.randomUUID(),
            email = "test1@test.com",
            password = encoder.encode("test1"),
            role = Role.ADMIN,
        ),
        User(
            id = UUID.randomUUID(),
            email = "test2@test.com",
            password = encoder.encode("test2"),
            role = Role.ADMIN,
        ),
        User(
            id = UUID.randomUUID(),
            email = "test3@test.com",
            password = encoder.encode("test3"),
            role = Role.USER,
        ),

        )

    fun save(user: User): Boolean {
        val updated = user.copy(password = encoder.encode(user.password))
        users.add(user)
        return users.add(updated)
    }


    fun findByEmail(email: String): User? =
        users
            .firstOrNull { it.email == email }

    fun findAll(): List<User> = users
    fun findById(id: UUID): User? =
        users
            .firstOrNull { it.id == id }

    fun deleteById(id: UUID): Boolean {
        val foundUser = findById(id)
        return foundUser?.let {
            users.remove(it)
        } ?: false
    }
}