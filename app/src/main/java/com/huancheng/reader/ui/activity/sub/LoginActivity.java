package com.huancheng.reader.ui.activity.sub;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.huancheng.reader.R;
import com.huancheng.reader.common.SaveLocalData;
import com.huancheng.reader.ui.activity.base.BaseBussActivity;
import com.huancheng.reader.util.GetFileUtil;
import com.huancheng.reader.util.SharedPreferencesUtil;
import com.huancheng.reader.util.StringUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoginActivity extends BaseBussActivity implements View.OnClickListener{

    private ImageView iv_line,iv_qq,iv_phone;
    private LinearLayout ll_login_phone;
    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        _context = LoginActivity.this;
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initView() {
        super.initView();
        iv_line = findViewById(R.id.iv_line);
        iv_qq = findViewById(R.id.iv_qq);
        iv_phone = findViewById(R.id.iv_phone);
        ll_login_phone = findViewById(R.id.ll_login_phone);
    }

    @Override
    protected void initData() {
        super.initData();
//        String user_id = (String) SharedPreferencesUtil.getParam(_context, SaveLocalData.USER_ID,"");
//        if(!StringUtil.isEmpty(user_id)){
////            startActivity(MainActivity.class,null);
//            onBackPressed();
//        }

    }

    @Override
    protected void BindComponentEvent() {
        super.BindComponentEvent();
        iv_phone.setOnClickListener(this);
        iv_qq.setOnClickListener(this);
        iv_line.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_line:
                startActivity(MainActivity.class,null);
                break;
            case R.id.iv_qq:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                        && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            1);
                    return;
                } else {

                }

                break;
            case R.id.iv_phone:
                startActivity(LoginPhoneActivity.class,null);
                break;

        }
    }
    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted.

                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    File path = Environment.getExternalStorageDirectory();// 获得SD卡路径
//                     File path = new File("/mnt/sdcard/");
                    name = new ArrayList();
                    File[] files = path.listFiles();// 读取
                    getFileName(files);

                }
            }
        }
    }
    ArrayList name;
        private void getFileName(File[] files) {

            if (files != null) {// 先判断目录是否为空，否则会报空指针
                for (File file : files) {
                    if (file.isDirectory()) {
                        Log.i("zeng", "若是文件目录。继续读1" + file.getName().toString() + file.getPath().toString());
                        getFileName(file.listFiles());
                        Log.i("zeng", "若是文件目录。继续读2" + file.getName().toString() + file.getPath().toString());
                    } else {
                        String fileName = file.getName();
                        if (fileName.endsWith(".txt")) {
                            HashMap map = new HashMap();
                            String s = fileName.substring(0, fileName.lastIndexOf(".")).toString();
                            Log.i("zeng", "文件名txt：：  " + s);
                            map.put("Name", fileName .substring(0, fileName.lastIndexOf(".")));
                            name.add(map);
                        }
                    }
                }
            }
        }
    }

