package com.huancheng.reader.ui.activity.sub;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.huancheng.reader.R;
import com.huancheng.reader.ui.activity.base.BaseBussActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by admin on 2018/12/3.
 */

public class WelComeActivity extends BaseBussActivity implements View.OnClickListener{

    private int time = 4;
    private TextView mTimer1;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    mTimer1.setEnabled(true);
                    mTimer1.setText("跳过"+time);
                    break;
            }

        }
    };

    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        _context = WelComeActivity.this;
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void initView() {
        super.initView();
        mTimer1 = findViewById(R.id.mTimer1);

    }

    @Override
    protected void initData() {
        super.initData();
        startTime1();
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(LoginActivity.class,null);
                onBackPressed();
            }
        };
        timer.schedule(task,3000);
    }

    @Override
    protected void BindComponentEvent() {
        super.BindComponentEvent();

    }
    public void startTime1() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                time--;
                if (time <= 0){
                    mHandler.sendEmptyMessage(2);
                }
                    mHandler.sendEmptyMessage(1);
                    mHandler.postDelayed(this, 1000);
            }
        };
        new Thread(runnable).start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mTimer1:
                startActivity(MainActivity.class,null);
                onBackPressed();
                break;
            default:
                break;
        }
    }
}
