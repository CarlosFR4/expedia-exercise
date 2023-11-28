package com.carlos.fco.rdgz.expedia.domain.model

data class Pokemon(
    val name: String,
    val url: String,
    val height: Int? = null,
    val weight: Int? = null,
    val orderNumber: Int? = null,
)
