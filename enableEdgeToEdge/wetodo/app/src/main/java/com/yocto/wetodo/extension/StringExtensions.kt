package com.yocto.wetodo.extension

fun String?.isNullOrTrimEmpty(): Boolean {
    return this?.trim().isNullOrEmpty()
}