package com.ithirteeng.features.chat.domain.model

import com.ithirteeng.features.chat.domain.entity.MessageEntity

sealed class Message {
    class MineMessage(val messageEntity: MessageEntity?) : Message()
    class OthersMessage(val messageEntity: MessageEntity?) : Message()
    class DateMessage(val messageEntity: MessageEntity?) : Message()
    class Error(val error: String) : Message()
}
