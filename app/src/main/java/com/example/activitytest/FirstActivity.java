package com.example.activitytest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    //Ctrl + O ： 弹出重写方法对话框，可以选择重写哪个方法

    /**
     * 选择一个菜单资源文件，创建相应菜单
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);  //用main这个资源文件创建菜单，添加到menu对象
        return true;
    }

    /**
     * 定义菜单相应事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    /**
     *
     * @param savedInstanceState
     */
    private static final String TAG = "FirstActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        /**
         * 选择布局
         */
        setContentView(R.layout.first_layout);
        /**
         * 如果之前有保存到bundle的临时数据：
         */
        if (savedInstanceState != null) {
            String tempData = savedInstanceState.getString("data_key");
            Log.d(TAG, tempData);
        }

        /**
         * Toast
         */
        Button buttonToast = (Button) findViewById(R.id.button_toast);
        buttonToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //参数：上下文，要显示的文字
                Toast.makeText(FirstActivity.this, "You clicked Button 1",
                        Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * destroy this activity: finish()效果和按下“back”建一样（手机上的返回键）
         */

        Button buttonFinish = (Button) findViewById(R.id.button_finish);
        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        /**
         * start a new activity ：显示Intent，指明启动哪个活动,这里启动Second Activity
         * 同时用putExtra给Second Activity 传了值
         */
        Button buttonExplicitIntent = (Button) findViewById(R.id.button_explicit_intent);
        buttonExplicitIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //参数：上下文，要打开的activity
                String data = "Hello SecondActivity";
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                intent.putExtra("extra_data", data);  //putExtra 用于把传递的数据暂存在intent中
                //startActivity(intent);  //该方法用于启动一个活动，可以给他传值，不能传值回来
                startActivityForResult(intent,1);  //请求码只要是一个唯一的值就可以了
            }
        });

        /**
         * start a new activity ：隐式Intent，只需指明一个action
         * 可以不添加category（有默认的），也可以添加多个category
         */
        Button buttonImplicitIntent = (Button) findViewById(R.id.button_implicit_intent);
        buttonImplicitIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //参数：要打开的活动可以响应的action
                //这里的action和catetory都是在AndroidManifest里，添加到某个活动里的
                Intent intent = new Intent("com.example.activitytest.ACTION_START");
                intent.addCategory("com.example.activity.MY_CATEGORY");
                startActivity(intent);
            }
        });

        /**
         * 用浏览器打开网站
         */
        Button buttonBrowser = (Button) findViewById(R.id.button_browser);
        buttonBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这个ACTION_VIEW是系统内置的
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);    //用能响应ACTION_VIEW的活动，打开该网页
            }
        });

        /**
         * 调用系统拨号界面
         */
        Button buttonDial = (Button) findViewById(R.id.button_dial);
        buttonDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ACTION_DIAL是系统内置的
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086")); //用能响应ACTION_DIAL的活动，拨号10086（未播出）
                startActivity(intent);
            }
        });
        /**
         * 打开normal activity & dialog activity 的按钮
         */
        Button startNormalActivity = (Button) findViewById(R.id.start_normal_activity);
        Button startDialogActivity = (Button) findViewById(R.id.start_dialog_activity);
        startNormalActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, NormalActivity.class);
                startActivity(intent);
            }
        });
        startDialogActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, DialogActivity.class);
                startActivity(intent);
            }
        });
        /**
         * 退出按钮
         */
        Button buttonClose = (Button) findViewById(R.id.button_close);
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCollector.finishAll();
                android.os.Process.killProcess(android.os.Process.myPid());//杀掉当前进程
            }
        });
        /**
         *启动一个活动的最佳方式：给它写一个启动函数，带参
         */
        Button buttonBest = (Button) findViewById(R.id.button_best);
        buttonBest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThirdActivity.actionStart(FirstActivity.this,"data1","data2");
            }
        });
    }

    /**
     * 重载onActivityResult,获得SecondActivity返回的数据
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("data_return");
                    Log.d("FirstActivity", returnedData);
                }break;
            default:
        }
    }
    /**
     * 重写生命周期回调方法
     * 当按下“start normal activity & dialog activity" 或从他们返回first activity时，观察logcat
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }
}
