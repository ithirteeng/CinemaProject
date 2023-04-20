package com.ithirteeng.features.chat.domain.model

import com.ithirteeng.features.chat.domain.entity.MessageEntity

sealed class Message(val messageEntity: MessageEntity?) {
    class MineMessage(d: MessageEntity?) : Message(d)
    class OthersMessage(d: MessageEntity?) : Message(d)
    class DateMessage(d: MessageEntity?) : Message(d)
    class Error : Message(null)
}
