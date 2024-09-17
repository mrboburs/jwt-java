package com.example.todoapp.service

import com.example.todoapp.model.User
import com.example.todoapp.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.UUID


@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun createUser(user: User): User? {
        val found = userRepository.findByEmail(user.email)
        return if (found == null) {
            userRepository.save(user)
            user
        } else null
    }

    fun findById(id: UUID): User? =
        userRepository.findById(id)

    fun findAll(): List<User> =
        userRepository.findAll()

    fun deleteById(id: UUID): Boolean =
        userRepository.deleteById(id)
}