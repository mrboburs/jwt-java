package com.example.todoapp.controller.user

import java.util.UUID

data class UserResponse(
    val id: UUID,
    val email: String,
) {
}