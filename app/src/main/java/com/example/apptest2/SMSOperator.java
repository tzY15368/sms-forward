package com.example.apptest2;

import android.app.PendingIntent;
import android.telephony.SmsManager;

public class SMSOperator {
    private static final SmsManager smsManager = SmsManager.getDefault();


    public static void sendMsg(String dst, String text){
        PendingIntent sentIntent = null;
        PendingIntent deliveryIntent = null;
        smsManager.sendTextMessage(dst,null,text,sentIntent,deliveryIntent);
    }
}
