package com.example.apptest2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SmsListener extends BroadcastReceiver {

    String msg_from;
    private Switch tg;
    private EditText tg_edit, phone_edit;
    private TextView log;

    public SmsListener(Switch _tg, EditText _tg_edit, EditText _phone_edit, TextView _log) {
        this.tg = _tg;
        this.tg_edit = _tg_edit;
        this.phone_edit = _phone_edit;
        this.log = _log;
        // start ping
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder().get().url("").build();
                        Call call = client.newCall(request);
                        Response response = call.execute();
                        response.close();
                        System.out.println("status code:"+response.code());

                        Thread.sleep(10000);
                        System.out.println("sleep 1000");
                    } catch (Exception e){
                        LogManager.append("Exception:"+e.getMessage());
                    }
                }
            }
        }).start();
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        // TODO Auto-generated method stub
        System.out.println("onreceive");
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {

            Bundle bundle = intent.getExtras();           //---get the SMS message passed in---
            SmsMessage[] msgs = null;

            if (bundle != null) {
                //---retrieve the SMS message received---
                try {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for (int i = 0; i < msgs.length; i++) {
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        msg_from = msgs[i].getOriginatingAddress();
                        final String msgBody = msgs[i].getMessageBody();
                        String msg = "["+msg_from+"]\n"+msgBody;
                        LogManager.append(msg);

                        long chat_id = Long.valueOf(tg_edit.getText().toString());
                        String dst = phone_edit.getText().toString();
                        LogManager.append("sending to:"+chat_id+"&"+dst);

                        if(!this.tg.isChecked()){
                            LogManager.append("report cancelled");
                            return;
                        }
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                BotOperator.sendMsg(chat_id,msg);
                            }
                        }).start();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                SMSOperator.sendMsg(dst,msg);
                            }
                        }).start();
                    }
                } catch (Exception e) {
                    LogManager.append("Exception caught"+e.getMessage());
                }
            }
        }
    }
}
