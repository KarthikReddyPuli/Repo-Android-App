package com.example.repoapplication

import java.io.Serializable

data class Permissions(
    val admin: Boolean,
    val maintain: Boolean,
    val pull: Boolean,
    val push: Boolean,
    val triage: Boolean
) : Serializable