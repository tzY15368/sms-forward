/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



package com.example.apptest2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.text.method.ScrollingMovementMethod;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        EditText et_tg = (EditText) findViewById(R.id.telegram_group_id);
        et_tg.setText("-");
        EditText et_phone = (EditText) findViewById(R.id.phone);
        et_phone.setText("+");

        Switch toggle = (Switch) findViewById(R.id.toggle);
        toggle.setText("report");
        SwitchHandler sh = new SwitchHandler(toggle,et_tg,et_phone);

        TextView tv = (TextView) findViewById(R.id.log);
        tv.setMovementMethod(new ScrollingMovementMethod());
        LogManager lm = new LogManager(20, tv);
        LogManager.append("started");

        SmsListener smsListener = new SmsListener(toggle, et_tg, et_phone, tv);
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        this.registerReceiver(smsListener,filter);

    }


}

