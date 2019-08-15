package com.moredian.entrance.guard.constant;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/7/25 15:37
 */
public class Constants {

    //SharedPreference const
    public static final String ISLOGIN = "islogin";
    public static final String USRTNAME = "username";
    public static final String PASSWORD = "password";
    public static final String MACHINE_NUMBER = "machineNumber";
    public static final String MACHINE_PORT = "machinePort";
    public static final String MACHINE_BAUDRTE = "machineBaudrate";

    public static final String ACCESSTOKEN = "AccessToken";
    public static final String USERID = "userId";
    public static final String ACCOUNT = "Account";

    //url
    public static final String SERVER_URL = "http://dev.machine_api.dt128.com/";

    //intent
    public static final String INTENT_ROWSBEAN_NAME = "intent_rowsbean_name";
    public static final String INTENT_ROWSBEAN_IDCARD = "intent_rowsbean_idcard";
    public static final String INTENT_ROWSBEAN_STUID = "intent_rowsbean_stuid";
    public static final String INTENT_ROWSBEAN_PHONE = "intent_rowsbean_phone";
    public static final String INTENT_ROWSBEAN_ID = "intent_rowsbean_id";
    public static final String INTENT_FACEINPUT_RGBDATA = "intent_faceinput_rgbdata";
    public static final String INTENT_FACEINPUT_MEMBERID = "intent_faceinput_memberid";
    public static final String INTENT_CONSUME_SPSUCCESS = "intent_consume_spsuccess";
    public static final String INTENT_CONSUME_QRSUCCESS = "intent_consume_qrsuccess";
    public static final String INTENT_CONSUME_FACESUCCESS = "intent_consume_facesuccess";

    //permission
    public static final int LOCATION_REQUEST = 1;
    public static final int LOCATION_REQUEST_2 = 2;

    //startActivityForREsult requestCode resultCode
    public static final int FACE_INPUT_REQUESTCODE = 3;
    public static final int FACE_INPUT_RESULTCODE = 4;

    //SerialPort
    public static final String SERIALPORT = "/dev/ttyMT2";
    public static final int BAUDRATE = 115200;

    public static final String BUZZING = "A1 B1 03 00 01 01 00 00 02";
    public static final String SETMONEYOK = "A1 B1 03 01 01 01 00 01 01 05";
    public static final String SETMONEYFAIL = "A1 B1 03 01 01 01 00 01 02 06";


    //fragmentTAG
    public static final String SHOWCARDMESSAGE = "ShowCardMessage";
    public static final String BUNDLE_TAG = "bundle_tag";

    //code
    public static final int KIND_FIND = 1;
    public static final int KIND_CONSUME = 2;//刷卡支付
    public static final int KIND_CONSUME_TDC = 3;//二维码
    public static final int KIND_CONSUME_FACE = 4;//人脸支付
}
