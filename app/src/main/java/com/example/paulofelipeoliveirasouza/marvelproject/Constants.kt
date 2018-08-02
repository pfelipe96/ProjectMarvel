package com.example.paulofelipeoliveirasouza.marvelproject

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class Constants{
    companion object {
        @JvmStatic
        val API_KEY_PUBLIC = "3e616b2f82f2ba22d71e96aba9c54f2f"

        @JvmStatic
        private val API_KEY_PRIVATE = "0bbac4c7765f3d15170d5cda7c24edda6f969978"

        @JvmStatic
        val TS = (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()

        @JvmStatic
        val HASH = "$TS$API_KEY_PRIVATE$API_KEY_PUBLIC".md5()

        @JvmStatic
        fun String.md5(): String {
            val md = MessageDigest.getInstance("MD5")
            val digested = md.digest(toByteArray())
            return digested.joinToString("") {
                String.format("%02x", it)
            }
        }
    }
}