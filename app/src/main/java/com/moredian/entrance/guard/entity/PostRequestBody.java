package com.moredian.entrance.guard.entity;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/1 16:00
 */
public class PostRequestBody {

    private String UserId;
    private String MemberName;
    private String Mobile;
    private String FaceBase64;

    public PostRequestBody() {
    }

    public PostRequestBody(String userId) {
        UserId = userId;
    }

    public PostRequestBody(String userId, String memberName, String mobile) {
        UserId = userId;
        MemberName = memberName;
        Mobile = mobile;
    }

    public PostRequestBody(String userId, String faceBase64) {
        UserId = userId;
        FaceBase64 = faceBase64;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getMemberName() {
        return MemberName;
    }

    public void setMemberName(String memberName) {
        MemberName = memberName;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getFaceBase64() {
        return FaceBase64;
    }

    public void setFaceBase64(String faceBase64) {
        FaceBase64 = faceBase64;
    }
}
