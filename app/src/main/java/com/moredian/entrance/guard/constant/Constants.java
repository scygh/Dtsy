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

    public static final String BREAKFEAST = "breakfeast";
    public static final String LAUNCH = "launch";
    public static final String DINNER = "dinner";
    public static final String MIDNIGHTSNACK = "midnightsnack";

    //url
    //public static final String SERVER_URL = "http://dev.machine_api.dt128.com/";
    public static final String SERVER_URL = "http://dev.open.api.dt128.com/";
    public static final String MODIAN_TOKEN = "116d758e-e603-4c26-a4a5-ab9cdb5c9a20";

    //intent
    public static final String INTENT_ROWSBEAN_POSITION = "intent_rowsbean_position";
    public static final String INTENT_ROWSBEAN_BEAN = "intent_rowsbean_bean";
    public static final String INTENT_FACEINPUT_RGBDATA = "intent_faceinput_rgbdata";
    public static final String INTENT_FACEINPUT_MEMBERID = "intent_faceinput_memberid";
    public static final String INTENT_CONSUME_SPSUCCESS = "intent_consume_spsuccess";
    public static final String INTENT_CONSUME_DESUCCESS = "intent_consume_desuccess";
    public static final String INTENT_CONSUME_QRSUCCESS = "intent_consume_qrsuccess";
    public static final String INTENT_CONSUME_FACESUCCESS = "intent_consume_facesuccess";
    public static final String INTENT_FACEINPUT_FLAG = "intent_faceinput_flag";

    public static final String INTENT_CONSUMERECORD = "intent_consume_record";
    public static final String INTENT_DEPOSITRECORD = "intent_deposit_record";

    //permission
    public static final int LOCATION_REQUEST = 1;
    public static final int LOCATION_REQUEST_2 = 2;

    //startActivityForREsult requestCode resultCode
    public static final int FACE_INPUT_REQUESTCODE = 3;
    public static final int FACE_INPUT_RESULTCODE = 4;

    //SerialPort
    public static final String SERIALPORT = "/dev/ttyMT2";
    public static final String BAUDRATE = "115200";

    public static final String BUZZING = "A1 B1 03 00 01 01 00 00 02";
    public static final String SETMONEYOK = "A1 B1 03 01 01 01 00 01 01 05";
    public static final String SETMONEYFAIL = "A1 B1 03 01 01 01 00 01 02 06";


    //fragmentTAG
    public static final String SHOWCARDMESSAGE = "ShowCardMessage";
    public static final String BUNDLE_TAG = "bundle_tag";
    public static final String BUNDLE_USERID = "bundle_userid";
    public static final String ARGUEMENT_NAME = "arguement_name";
    public static final String ARGUEMENT_EMPID = "arguement_empid";
    public static final String ARGUEMENT_IDCARD = "arguement_idcard";
    public static final String ARGUEMENT_PHONE = "arguement_phone";
    public static final String ARGUEMENT_ADDRESS = "arguement_address";

    public static final String ARGUEMENT_CONST = "arguement_const";
    public static final String ARGUEMENT_CASH = "arguement_cash";
    public static final String ARGUEMENT_DONATE = "arguement_donate";
    public static final String ARGUEMENT_CARDTYPE = "arguement_cardtype";
    public static final String ARGUEMENT_SUBSIDY = "arguement_subsidy";

    public static final String ARGUEMENT_SERIALNO = "arguement_serialNo";
    public static final String ARGUEMENT_PAYPASSWORD = "arguement_paypassword";
    public static final String ARGUEMENT_DEADLINE = "arguement_deadline";
    public static final String ARGUEMENT_DEPARTMENT = "arguement_department";
    public static final String ARGUEMENT_NUMBER = "arguement_number";

    public static final String DATE = "arguement_date";

    //code
    public static final int KIND_FIND = 1;
    public static final int KIND_CONSUME = 2;//刷卡支付
    public static final int KIND_CONSUME_TDC = 3;//二维码
    public static final int KIND_CONSUME_FACE = 4;//人脸支付
    
    //face
    public static final int KEY_DETECT_HIDE = 0;
    public static final int KEY_DETECT_FACE_SIZE_ERROR = 1;
    public static final int KEY_DETECT_FACE_ANGLE_ERROR = 2;
    public static final int KEY_DETECT_FACE_QUALITY_ERROR = 3;
    public static final int KEY_DETECT_USER_NAME = 4;
    public static final int KEY_SET_TEXT = 5;

    public static final int KEY_OPEN_NIR_CAMERA = 1000;
    public static final int OPEN_MIR_CAMERA_DELAY = 3 * 1000;

    public static final String SIZE_INCORRECT_TIPS = "请让我看清全部的脸";
    public static final String ANGLE_INCORRECT_TIPS = "请摆正脸";
    public static final String QUALITY_INCORRECT_TIPS = "请保持静止";

    public static final String DETECT_RESULT_ACTION = "com.moredian.facetrack.detectResult";
    public static final String NIR_RESULT_ACTION = "com.moredian.facetrack.nirResult";
    public static final String OFFLINE_RECOGNIZE_ACTION = "com.moredian.facetrack.offLineRecognize";
    public static final String ONLINE_RECOGNIZE_ACTION = "com.moredian.facetrack.onLineRecognize";

    public static final String CHECK_STATUS = "status";
    public static final String CHECK_FAIL_REASON = "failed_reason";
    public static final String RGB_DATA = "rgb_data";
    public static final String NIR_DATA = "nir_data";
    public static final String FACE_COUNT = "face_count";
    public static final String TRACK_ID = "track_id";
    public static final String USER_NAME = "user_name";
    public static final String PERSON_ID = "memberID";

    public static final String DETECT_FAIL_REASON_NOFACE = "detect_no_face";
    public static final String DETECT_FAIL_REASON_FACE_SIZE_INCORRECT = "detect_size_incorrect";
    public static final String DETECT_FAIL_REASON_FACE_ANGLE_INCORRECT = "detect_angle_incorrect";
    public static final String DETECT_FAIL_REASON_FACE_QUALITY_TOO_LOW = "detect_quality_too_low";
}
