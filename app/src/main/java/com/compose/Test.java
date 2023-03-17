package com.compose;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Test extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    void show(){
        Toast.makeText(Test.this,"show message",Toast.LENGTH_LONG).show();

    }
}
