package com.cay.familynote.util;

import java.lang.reflect.Field;
import java.text.DecimalFormat;

public class FamilyMessageUtil {


    private static final String SUCCESS = "SUCCESS";
    private static final String ERR = "ERR";
    private static final String WARN = "WARN";

    private static int SUCCESS_CODE = 0;
    private static int WARN_CODE = 1000;
    private static int ERR_CODE = 10000;

    public static final FamilyMessage END_SUCCESS = createMessageCode( SUCCESS, "业务正常终了！" );
    public static final FamilyMessage END_WARN = createMessageCode( WARN, "无法识别的警告！" );
    public static final FamilyMessage END_ERROR = createMessageCode( ERR, "无法识别的异常！" );

    public static final FamilyMessage CREATE_LOGINUSER_SUCCESS = createMessageCode( SUCCESS, "用户注册成功！:[{0}]" );
    public static final FamilyMessage CREATE_LOGINUSER_ERR = createMessageCode( ERR, "用户注册失败！:[{0}]" );

    public static final FamilyMessage UPDATE_FNUSER_SUCCESS = createMessageCode( SUCCESS, "用户信息更新成功！:[{0}]" );
    public static final FamilyMessage UPDATE_FNUSER_ERR = createMessageCode( ERR, "用户信息更新失败！:[{0}]" );

    public static final FamilyMessage SAVE_NOTE_SUCCESS = createMessageCode( SUCCESS, "Note保存成功！" );
    public static final FamilyMessage SAVE_NOTE_ERR = createMessageCode( ERR, "Note保存失败！" );

    public static final FamilyMessage DBACCESS_SUCCESS = createMessageCode( SUCCESS, "DB操作成功！" );
    public static final FamilyMessage DBACCESS_ERR = createMessageCode( ERR, "DB操作出错！" );


    public static final FamilyMessage USER_OR_PASSWORD_NOTEXISTS_ERR = createMessageCode( ERR, "用戶不存在！:[{0}]" );

    private static FamilyMessage createMessageCode(String type, String message) {
        FamilyMessage familyMessage = null;

        if (type.equals( SUCCESS )) {
            familyMessage = new FamilyMessage( SUCCESS_CODE, "MSG_" + type + "_" + (new DecimalFormat( "0000" ).format( SUCCESS_CODE )), message );
            SUCCESS_CODE++;
        } else if (type.equals( WARN )) {
            familyMessage = new FamilyMessage( WARN_CODE, "MSG_" + type + "_" + (new DecimalFormat( "0000" ).format( WARN_CODE )), message );
            WARN_CODE++;
        } else if (type.equals( ERR )) {
            familyMessage = new FamilyMessage( ERR_CODE, "MSG_" + type + "_" + (new DecimalFormat( "0000" ).format( ERR_CODE )), message );
            ERR_CODE++;
        } else {
            familyMessage = new FamilyMessage( ERR_CODE, "MSG_UNKNOW_" + type + "_" + (new DecimalFormat( "0000" ).format( ERR_CODE )), message );
            ERR_CODE++;
        }
        return familyMessage;
    }

    public static void report() {
        try {
            Class clazz = Class.forName( "com.cay.familynote.util.FamilyMessageUtil" );
            Field[] fields = clazz.getFields();
            for (Field field : fields) {
                System.out.println();
                Field a = field.get( null ).getClass().getDeclaredField( "msgCode" );
                Field b = field.get( null ).getClass().getDeclaredField( "message" );
                a.setAccessible( true );
                b.setAccessible( true );
                System.out.println(
                        field.getName() + "    " + a.get( field.get( null ) ) + "   " + b.get( field.get( null ) ) );

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FamilyMessageUtil.report();
    }
}
