package com.example.apptest2;

import android.text.InputType;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class SwitchHandler implements CompoundButton.OnCheckedChangeListener {

    Switch sw;
    EditText e1, e2;

    public SwitchHandler(Switch swt, EditText _e1, EditText _e2){
        this.sw = swt;
        this.e1 = _e1;
        this.e2 = _e2;
        sw.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton btn, boolean b){
        switch (btn.getId()){
            case R.id.toggle:
                // 禁止运行时改变textview值
                if(b){

                    this.e1.setInputType(InputType.TYPE_NULL);
                    this.e1.setEnabled(false);
                    this.e2.setInputType(InputType.TYPE_NULL);
                    this.e2.setEnabled(false);
                } else {

                    this.e1.setInputType(InputType.TYPE_CLASS_TEXT);
                    this.e1.setEnabled(true);
                    this.e2.setInputType(InputType.TYPE_CLASS_TEXT);
                    this.e2.setEnabled(true);
                }
        }
    }
}
