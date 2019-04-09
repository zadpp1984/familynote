package com.cay.familynote.util;

import com.alibaba.fastjson.JSONObject;
import com.sun.javafx.binding.StringFormatter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.text.MessageFormat;

@Setter
@Getter
@AllArgsConstructor
public class FamilyMessage implements Serializable {

    private int status;
    private String msgcode;
    private String message;

    public FamilyMessage fit(String... parm) {
        return new FamilyMessage( status, msgcode, MessageFormat.format( message, parm ) );
    }

    public String toJsonString() {
        JSONObject obj = new JSONObject();
        obj.put( "msgcode", msgcode );

        return obj.toJSONString();
    }

    @Override
    public String toString() {
        return toJsonString();
    }

    public static void main(String args[]) {
        String s = "111[{0}]111";
        System.out.println( StringFormatter.format( s, 5 ) );
    }
}
