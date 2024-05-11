package com.github.emmpann.core.domain.model

data class User(
    val name: String,
    val emailVerifiedAt: String? = null,
    val id: Int,
    val email: String,
    val token: String,
)