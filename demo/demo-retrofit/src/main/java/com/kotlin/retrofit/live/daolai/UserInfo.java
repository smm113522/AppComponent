package com.kotlin.retrofit.live.daolai;

import android.text.TextUtils;

import java.io.Serializable;

public class UserInfo implements Serializable {

    /**
     * userid : 17327148
     * nickname : 道友_151****5490
     * phone : 15122805490
     * password : ******
     * token : c9651e7c0131480a8a6501a96a45118417327148
     * sign :
     * gmtcreat : 2020-06-11 20:16:01
     * gmtmodify : 2020-06-11 20:16:01
     * ryToken : iSlJc3PUd/1zst933Xpwxnsb8u5qWv7ryl0q1aJEDko=@t7nf.cn.rongnav.com;t7nf.cn.rongcfg.com
     * address :
     * realname :
     * sex : M
     * idno :
     * idtype :
     * birthday :
     * detailaddress :
     * qq :
     * weibo :
     * wechat :
     * audituser :
     * status :
     * auditstatus :
     * auditcontent :
     * hsid :
     * front :
     * back :
     * hsurl :
     * yfollow : 0
     * upcount : 0
     * followers : 0
     * apple :
     */

    private String userid;
    private String nickname;
    private String phone;
    private String password;
    private String token;
    private String sign;
    private String gmtcreat;
    private String gmtmodify;
    private String ryToken;
    private String address;
    private String realname;
    private String departname;
    private String sex;
    private String idno;
    private String idtype;
    private String birthday;
    private String detailaddress;
    private String qq;
    private String weibo;
    private String wechat;
    private String audituser;
    private String status;
    private String auditstatus;//认证p 是通过
    private String auditcontent;
    private String hsid;
    private String front;
    private String back;
    private String hsurl;
    private int yfollow;
    private int upcount;
    private int followers;
    private int stores;
    private String apple;

    private String friendid;
    private String remark;
    private String gmtCreat;
    private String letter;
    private String star;
    private String isfollow;
    private String isfriend;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNickname() {
        if (TextUtils.isEmpty(remark)) {
            return nickname;
        }else {
            return remark;
        }
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getGmtcreat() {
        return gmtcreat;
    }

    public void setGmtcreat(String gmtcreat) {
        this.gmtcreat = gmtcreat;
    }

    public String getGmtmodify() {
        return gmtmodify;
    }

    public void setGmtmodify(String gmtmodify) {
        this.gmtmodify = gmtmodify;
    }

    public String getRyToken() {
        return ryToken;
    }

    public void setRyToken(String ryToken) {
        this.ryToken = ryToken;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getIdtype() {
        return idtype;
    }

    public void setIdtype(String idtype) {
        this.idtype = idtype;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDetailaddress() {
        return detailaddress;
    }

    public void setDetailaddress(String detailaddress) {
        this.detailaddress = detailaddress;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getAudituser() {
        return audituser;
    }

    public void setAudituser(String audituser) {
        this.audituser = audituser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuditstatus() {
        return auditstatus;
    }

    public void setAuditstatus(String auditstatus) {
        this.auditstatus = auditstatus;
    }

    public String getAuditcontent() {
        return auditcontent;
    }

    public void setAuditcontent(String auditcontent) {
        this.auditcontent = auditcontent;
    }

    public String getHsid() {
        return hsid;
    }

    public void setHsid(String hsid) {
        this.hsid = hsid;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String getHsurl() {
        return hsurl;
    }

    public void setHsurl(String hsurl) {
        this.hsurl = hsurl;
    }

    public int getYfollow() {
        return yfollow;
    }

    public void setYfollow(int yfollow) {
        this.yfollow = yfollow;
    }

    public int getUpcount() {
        return upcount;
    }

    public void setUpcount(int upcount) {
        this.upcount = upcount;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getStores() {
        return stores;
    }

    public void setStores(int stores) {
        this.stores = stores;
    }

    public String getApple() {
        return apple;
    }

    public void setApple(String apple) {
        this.apple = apple;
    }

    public String getFriendid() {
        return friendid;
    }

    public void setFriendid(String friendid) {
        this.friendid = friendid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGmtCreat() {
        return gmtCreat;
    }

    public void setGmtCreat(String gmtCreat) {
        this.gmtCreat = gmtCreat;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getIsfollow() {
        return isfollow;
    }

    public void setIsfollow(String isfollow) {
        this.isfollow = isfollow;
    }

    public String getIsfriend() {
        return isfriend;
    }

    public void setIsfriend(String isfriend) {
        this.isfriend = isfriend;
    }

    public String getDepartname() {
        return departname;
    }

    public void setDepartname(String departname) {
        this.departname = departname;
    }
}
