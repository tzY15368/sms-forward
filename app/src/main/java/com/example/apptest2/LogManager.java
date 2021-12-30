package com.example.apptest2;

import android.widget.TextView;

import java.util.Date;

public class LogManager {
    private static TextView tv;
    private static String[] logs;
    private static int cap = 0;
    private static int count = 0;

    public LogManager(int logLength, TextView _tv){
        this.tv = _tv;
        this.cap = logLength;
        this.logs = new String[logLength];
    }

    public static void append(String log){
        logs[(count++)%cap] = new Date().toString()+":"+log;
        logs[count%cap] = "-----------------";
        render();
    }

    private static void render(){
        String data = "";
        for(int i=0;i<logs.length;i++){
            data += logs[i]!=null?logs[i]:"";
            data += "\n";
        }
        tv.setText(data);
    }

}
