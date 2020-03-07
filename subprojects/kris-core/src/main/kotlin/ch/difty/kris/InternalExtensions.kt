package ch.difty.kris

internal fun String.truncatedTo(maxLength: Int?): String = when {
    maxLength != null -> substring(0, kotlin.math.min(maxLength, length))
    else -> this
}
