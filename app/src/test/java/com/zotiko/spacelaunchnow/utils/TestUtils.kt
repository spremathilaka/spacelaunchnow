package com.zotiko.spacelaunchnow.utils

import com.google.gson.Gson

object TestUtils {

    fun readTestResourceFile(fileName: String): String {
        val fileInputStream = javaClass.classLoader?.getResourceAsStream(fileName)
        return fileInputStream?.bufferedReader()?.readText() ?: ""
    }

    fun <T> loadData(fileName: String, kClass: Class<T>): T? {
        return try {
            val stringFormattedData = readTestResourceFile(fileName)
            val gson = Gson()
            gson.fromJson(stringFormattedData, kClass)
        } catch (e: Exception) {
            null
        }
    }
}
