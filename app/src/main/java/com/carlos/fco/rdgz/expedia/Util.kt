package com.carlos.fco.rdgz.expedia

fun String.capitalize() = this.replaceFirstChar {
    if (it.isLowerCase()) it.titlecase() else it.toString()
}

fun Int.toPokemonOrderNumber() = "#${toString().padStart(4, '0')}"