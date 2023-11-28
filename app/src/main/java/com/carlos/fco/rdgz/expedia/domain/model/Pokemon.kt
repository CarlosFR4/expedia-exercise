package com.carlos.fco.rdgz.expedia.domain.model

data class Pokemon(
    val name: String,
    val url: String,
    var height: Int? = null,
    var weight: Int? = null,
    val orderNumber: Int? = null,
)
