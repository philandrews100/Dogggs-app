package com.p.andrews.core.domain.model

data class DogBreed(
    val mainBreed: String,
    val subBreed: String? = null,
    val imageUrl: String? = null
) {
    val displayName: String
        get() = if (subBreed != null)
            "${subBreed.replaceFirstChar { it.uppercaseChar() }} ${mainBreed.replaceFirstChar { it.uppercaseChar() }}"
        else mainBreed.replaceFirstChar { it.uppercaseChar() }

    val apiPath: String
        get() = if (subBreed != null) "$mainBreed/$subBreed" else mainBreed
}