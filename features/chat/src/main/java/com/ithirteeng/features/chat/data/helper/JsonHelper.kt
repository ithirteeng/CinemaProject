package com.ithirteeng.features.chat.data.helper

import android.util.Log
import com.google.gson.Gson
import com.ithirteeng.features.chat.domain.entity.MessageEntity
import com.ithirteeng.shared.network.common.API_ERROR

object JsonHelper {
    fun jsonToMessageEntity(json: String): MessageEntity? {
        return try {
            val gson = Gson()
            gson.fromJson(json, MessageEntity::class.java)
        } catch (e: java.lang.Exception) {
            Log.e(API_ERROR, "failed to parse message", e)
            null
        }
    }
}