package com.example.activitytest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        //打印传入的值
        Intent intent = getIntent();  //获取用于启动SecondActivity的Intent
        String data = intent.getStringExtra("extra_data"); //获取String类型的数据
        Log.d("SecondActivity", data);

        //处理传出的值
        Button button2 = (Button) findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("data_return", "Hello FirstActivity");
                setResult(RESULT_OK, intent);  //专门用于向上一个活动传回数据，第一个参数是处理结果，第二个是数据
                finish();
                /*
                * 由于我们是使用startActivityForResult() 方法来启动SecondActivity的，
                * 在SecondActivity被销毁之后会回调上一个活动的onActivityResult() 方法，
                * 因此我们需要在FirstActivity中重写这个方法来得到返回的数据
                * */
            }
        });
    }

    /**
     * 如果按back，同样返回数据
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("data_return", "Hello FirstActivity");
        setResult(RESULT_OK, intent);
        finish();
    }
}
