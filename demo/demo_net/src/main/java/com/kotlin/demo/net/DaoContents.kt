package com.kotlin.demo.net

object DaoContents {

    const val HTTPS = "http://59.111.89.141:8769/"

    //    道来发声系统 APIs
    //    im-controller : Im ControllerShow/HideList OperationsExpand Operations
//    POST /im/addBlack 添加黑名单
    const val addBlack = "/admin/addBlack"

    //    POST /im/addFriend 添加好友
    const val addFriend = "/admin/addFriend"

    //    POST /im/addStar 标星好友
    const val addStar = "/admin/addStar"

    //    POST /im/cancelMessageToFriend 好友消息撤回
    const val cancelMessageToFriend = "/admin/cancelMessageToFriend"

    //    POST /im/cancelMessageToGroup 群组消息撤回
    const val cancelMessageToGroup = "/admin/cancelMessageToGroup"
//    POST /im/cancelStar 取消标星好友
//    POST /im/createGroup 建群
//    POST /im/createGroupByFaceToFace 面对面建群
//    POST /im/delBlack 删除黑名单
//    POST /im/delFriend 删除好友
//    POST /im/delRequest 删除请求
//    POST /im/delRequestList 删除请求列表
//    POST /im/delStar 取消标星好友
//    POST /im/deliverGroupAdmin 转交群主权限
//    POST /im/dismissGroup 解散群聊
//    POST /im/exitGroupByFaceToFace 退出面对面建群
//    GET /im/findUser 查找用户
//    POST /im/getAddressList 通讯录好友
//    GET /im/getBlackList 获取黑名单列表
//    GET /im/getRequestInfo 获取请求信息
//    GET /im/getRequestList 获取请求列表
//    POST /im/inviteJoinGroup 邀请用户进群
//    POST /im/isfriend 是否为好友接口
//    POST /im/joinGroup 加入群组
//    POST /im/modifyGroupName 修改群名
//    POST /im/modifyGroupNickName 修改群昵称
//    POST /im/modifyGroupSign修改群公告
//    POST /im/passFriend通过好友
//    POST /im/quitGroup退出群聊
//    POST /im/removeGroup移除群成员
//    POST /im/replyRequest回复请求
//    POST /im/sameGroup共同群信息
//    POST /im/senIntroduceToFriend发送名片消息到好友
//    POST /im/sendFileMessageToFriend发送文件消息给好友
//    POST /im/sendFileMessageToGroup发送文件消息到群
//    POST /im/sendImageText发送图文消息到好友
//    POST /im/sendImageTextGroup发送图文消息到群
//    POST /im/sendIntroduceToGroup发送名片消息到群
//    POST /im/sendLBSMsgToFriend发送位置消息
//    POST /im/sendLBSMsgToGroup发送位置消息到群
//    POST /im/sendMediaMessageToGroup发送多媒体消息到群
//    POST /im/sendMediaMsgToFriend发送多媒体消息
//    POST /im/sendMessageToFriend发送文本消息到好友
//    POST /im/sendMessageToGroup发送文本消息到群
//    POST /im/updateFriendRemark修改好友备注
//    pay-controller : Pay ControllerShow/HideList OperationsExpand Operations
//    shop-controller : Shop ControllerShow/HideList OperationsExpand Operations
//    sys-controller : Sys ControllerShow/HideList OperationsExpand Operations
//    POST /sys/addIdea添加意见
//    GET /sys/getArea获取区信息
//    GET /sys/getAttachmentAddress获取附件地址
//    DELETE /sys/getAttachments附件展示
//    GET /sys/getAttachments附件展示
//    HEAD /sys/getAttachments附件展示
//    OPTIONS /sys/getAttachments附件展示
//    PATCH /sys/getAttachments附件展示
//    POST /sys/getAttachments附件展示
//    PUT /sys/getAttachments附件展示
//    GET /sys/getCity获取市信息
//    GET /sys/getCodeAndName获取字典信息
//    GET /sys/getCountry获取乡信息
//    GET /sys/getCulture获取文化信息
//    GET /sys/getCultureCategory获取文化类别
//    GET /sys/getDepart获取部门/村信息
//    GET /sys/getMixCodeAndName地区信息模糊查询
//    GET /sys/getProvience获取省信息
//    GET /sys/getQrcodeInfo获取二维码信息
//    GET /sys/getVersion获取最新版本信息
//    DELETE /sys/qrcode获取二维码信息
//    GET /sys/qrcode获取二维码信息
//    HEAD /sys/qrcode获取二维码信息
//    OPTIONS /sys/qrcode获取二维码信息
//    PATCH /sys/qrcode获取二维码信息
//    POST /sys/qrcode获取二维码信息
//    PUT /sys/qrcode获取二维码信息
//    POST /sys/viewCulture更新文化查阅次数
//    sys-controller-v-2 : Sys Controller V 2Show/HideList OperationsExpand Operations
//    GET /sys/v2/getArea获取区信息
//    GET /sys/v2/getAttachmentAddress获取附件地址
//    DELETE /sys/v2/getAttachments附件展示
//    GET /sys/v2/getAttachments附件展示
//    HEAD /sys/v2/getAttachments附件展示
//    OPTIONS /sys/v2/getAttachments附件展示
//    PATCH /sys/v2/getAttachments附件展示
//    POST /sys/v2/getAttachments附件展示
//    PUT /sys/v2/getAttachments附件展示
//    GET /sys/v2/getCity获取市信息
//    GET /sys/v2/getCodeAndName获取字典信息
//    GET /sys/v2/getCountry获取乡信息
//    GET /sys/v2/getCulture获取文化信息
//    GET /sys/v2/getCultureCategory获取文化类别
//    GET /sys/v2/getDepart获取部门/村信息
//    GET /sys/v2/getMixCodeAndName地区信息模糊查询
//    GET /sys/v2/getProvience获取省信息
//    GET /sys/v2/getQrcodeInfo获取二维码信息
//    GET /sys/v2/getVersion获取最新版本信息
//    DELETE /sys/v2/qrcode获取二维码信息
//    GET /sys/v2/qrcode获取二维码信息
//    HEAD /sys/v2/qrcode获取二维码信息
//    OPTIONS /sys/v2/qrcode获取二维码信息
//    PATCH /sys/v2/qrcode获取二维码信息
//    POST /sys/v2/qrcode获取二维码信息
//    PUT /sys/v2/qrcode获取二维码信息
//    user-controller : User ControllerShow/HideList OperationsExpand Operations
//    POST /user/addContentNoise添加文章评论
//    POST /user/addContentReply添加部门回复
//    POST /user/addFollow添加关注
//    POST /user/addReportNoise添加反映评论
//    POST /user/addSoundNoise添加发声评论
//    POST /user/addStore添加收藏
//    POST /user/addUserAddress添加用户地址
//    POST /user/auditDepart机构认证审核
//    POST /user/auditReport反映审核
//    POST /user/auditUser用户实名认证审核
//    POST /user/cancelFollow取消关注
//    POST /user/changeAdress修改所在地
//    POST /user/changeNickName修改昵称
//    POST /user/changePassword修改密码
//    POST /user/changePasswordNew手机验证码后修改密码
//    POST /user/changePhone修改手机号码
//    POST /user/changeSex修改性别
//    POST /user/changeSign修改签名
//    POST /user/completeMaterial完善资料
//    POST /user/contentSupply文章补充
//    POST /user/delContent删除文章
//    POST /user/delContentNoise删除评论
//    POST /user/delMessage删除消息
//    POST /user/delReport删除反映
//    POST /user/delReportNoise删除反映评论
//    POST /user/delSound删除发声
//    POST /user/delSoundNoise删除发声评论
//    POST /user/delStore取消收藏
//    POST /user/delUserAddress删除地址
//    POST /user/departIdentify部门认证接口
//    GET /user/findCityInfo查找市信息
//    GET /user/findGroup查找群
//    GET /user/findUserAndGroup查找群&好友列表
//    GET /user/findUserFriend查找好友
//    GET /user/findUserGroup查找群列表
//    GET /user/findUserGroupInfo查找群成员列表
//    POST /user/forgetPassword忘记密码
//    POST /user/forgetPasswordVcCode忘记密码验证验证码
//    GET /user/getAuditDepart获取审核部门清单
//    GET /user/getAuditReportList审核反映查询
//    GET /user/getAuditUser获取实名审核用户清单
//    GET /user/getBFollow获取我的粉丝
//    GET /user/getContentNoise获取文章评论
//    GET /user/getContents文章查询
//    GET /user/getFollow获取关注列表
//    GET /user/getMessage获取消息列表
//    GET /user/getMyFavorite获取我的喜欢
//    GET /user/getReportNoise获取反映评论
//    GET /user/getReports反映查询
//    GET /user/getSoundNoise获取发声评论
//    GET /user/getSounds发声查询
//    GET /user/getStore获取收藏列表
//    GET /user/getUserAddress获取用户地址
//    GET /user/getUserInfo通过token获取用户信息
//    POST /user/getVcode获取验证码
//    POST /user/login用户登录
//    POST /user/logout用户登出
//    POST /user/modifyHs修改头像
//    POST /user/modifyUserAddress修改用户地址
//    POST /user/readMessage阅读消息
//    POST /user/reportFriend投诉好友
//    POST /user/reportSupply反映补充
//    POST /user/reportUp点赞反映
//    POST /user/scoreContent打分文章
//    POST /user/scoreReport打分反映
//    POST /user/scoreSound打分发声
//    POST /user/setUserAddressDefault设置默认地址
//    POST /user/shareContent文章分享
//    POST /user/shareContentNew文章分享
//    POST /user/soundSupply发声补充
//    POST /user/soundUp点赞发声
//    POST /user/supplyNaterial补充身份证件信息
//    POST /user/thirdLock绑定第三方账号
//    POST /user/thirdLogin第三方登录
//    POST /user/uploadAttachments附件上传
//    POST /user/userComplain用户投诉
//    POST /user/userContent用户发文章
//    POST /user/userNewRegister新用户注册
//    POST /user/userRegister用户注册
//    POST /user/userReport举报
//    POST /user/userReports用户反映
//    POST /user/userSounds用户发声
//    POST /user/userUp用户点赞
//    user-controller-v-2 : User Controller V 2Show/HideList OperationsExpand Operations
//    POST /user/v2/forgetPassword忘记密码
//    POST /user/v2/login登录接口

}