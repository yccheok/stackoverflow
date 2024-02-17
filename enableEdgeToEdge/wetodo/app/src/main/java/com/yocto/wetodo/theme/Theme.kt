package com.yocto.wetodo.theme

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
enum class Theme {
    WHITE,
    DARK,
    SYSTEM
}