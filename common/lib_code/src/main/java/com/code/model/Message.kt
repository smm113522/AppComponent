package com.code.model

import java.io.Serializable


/**
 * Created by WZ on 2018-01-29.
 */

class Message : Serializable {
    var msgSvrId = ""
    var type = ""
    var isSend = ""           // 1：发送   0：接收   2：系统消息
    var createTime = ""
    var talker = ""
    var content = ""
    var imgPath = ""
}