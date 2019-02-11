package com.huancheng.reader.ui.activity.sub;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.huancheng.reader.R;
import com.huancheng.reader.ui.activity.base.BaseBussActivity;
import com.huancheng.reader.util.HttpUtil;
import com.huancheng.reader.util.StringUtil;
import com.huancheng.reader.util.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import okhttp3.Call;

public class RegisterActivity extends BaseBussActivity implements View.OnClickListener{

    private EditText et_register_phone,et_captcha,et_password,et_register_passwordagain;//手机号码、验证码、密码、再次输入密码
    private TextView tv_captcha;//验证码按钮
    private ImageView iv_register_eye_close1,iv_register_eye_close2,iv_left_title_bar,iv_right_title_bar;//密码后面的小图标、再次输入密码后的小图标、头部左边返回键，头部右边图标
    private Button btn_register;//注册按钮
    private TextView tv_register_wechar,tv_register_qq,tv_back;//微信登录、QQ登录、头部文字显示
    private boolean flag = true;
    private boolean flag1 = true;
    private String phone;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (msg.arg1 == 0) {
                        tv_captcha.setText("验证码");
                        tv_captcha.setClickable(true);
                    } else {
                        tv_captcha.setText("(" + msg.arg1 + ")秒");
                        tv_captcha.setClickable(false);
                    }
                    break;
            }
        }
    };
    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        _context = RegisterActivity.this;
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void initView() {
        super.initView();
        et_register_phone = findViewById(R.id.et_register_phone);
        et_captcha = findViewById(R.id.et_captcha);
        et_password = findViewById(R.id.et_password);
        et_register_passwordagain = findViewById(R.id.et_register_passwordagain);
        tv_captcha = findViewById(R.id.tv_captcha);
        iv_register_eye_close1 = findViewById(R.id.iv_register_eye_close1);
        iv_register_eye_close2 = findViewById(R.id.iv_register_eye_close2);
        btn_register = findViewById(R.id.btn_register);
        tv_register_wechar = findViewById(R.id.tv_register_wechar);
        tv_register_qq = findViewById(R.id.tv_register_qq);
        tv_back = findViewById(R.id.tv_back);
        iv_left_title_bar = findViewById(R.id.iv_left_title_bar);
        iv_right_title_bar = findViewById(R.id.iv_right_title_bar);
        iv_right_title_bar.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        super.initData();
        et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        et_register_passwordagain.setTransformationMethod(PasswordTransformationMethod.getInstance());
        iv_register_eye_close1.setBackgroundResource(R.mipmap.login_code2);
        iv_register_eye_close2.setBackgroundResource(R.mipmap.login_code2);
        tv_back.setText("注册");
    }

    @Override
    protected void BindComponentEvent() {
        super.BindComponentEvent();
        tv_captcha.setOnClickListener(this);
        iv_register_eye_close1.setOnClickListener(this);
        iv_register_eye_close2.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        tv_register_wechar.setOnClickListener(this);
        tv_register_qq.setOnClickListener(this);
        iv_left_title_bar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_left_title_bar:
                onBackPressed();
                break;
            case R.id.tv_captcha://验证码
                if (StringUtil.isEmpty(et_register_phone.getText().toString())){
                    ToastUtil.showLong(_context,"请输入手机号码");
                    return;
                }
                if (!checkMobile(et_register_phone.getText().toString())){
                    ToastUtil.showShort(_context,"请输入正确的手机号码");
                    return;
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 59; i >= 0; i--) {
                            Message msg = handler.obtainMessage();
                            msg.arg1 = i;
                            handler.sendMessage(msg);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            captcha();//短信验证
            break;
            case R.id.iv_register_eye_close1://输入密码后的小图标
                if (flag == true) {
                    // TODO Auto-generated method stub
                    iv_register_eye_close1.setBackgroundResource(R.mipmap.login_code1);
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    flag = false;
                } else {
                    iv_register_eye_close1.setBackgroundResource(R.mipmap.login_code2);
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    flag = true;
                }
                break;
            case R.id.iv_register_eye_close2://再次输入密码的小图标
                if (flag1 == true) {
                    // TODO Auto-generated method stub
                    iv_register_eye_close2.setBackgroundResource(R.mipmap.login_code1);
                    et_register_passwordagain.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    flag1 = false;
                } else {
                    iv_register_eye_close2.setBackgroundResource(R.mipmap.login_code2);
                    et_register_passwordagain.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    flag1 = true;
                }
                break;
            case R.id.tv_register_wechar://微信登录

                break;
            case R.id.tv_register_qq://qq登录

                break;
            case R.id.btn_register://注册
                if (StringUtil.isEmpty(et_register_phone.getText().toString())){
                    ToastUtil.showShort(_context,"手机号码不能为空");
                    return;
                }
                if(StringUtil.isEmpty(et_captcha.getText().toString())){
                    ToastUtil.showShort(_context,"验证码不能为空");
                    return;
                }
                if (StringUtil.isEmpty(et_password.getText().toString())){
                    ToastUtil.showShort(_context,"密码不能为空");
                    return;
                }
                if (StringUtil.isEmpty(et_register_passwordagain.getText().toString())){
                    ToastUtil.showShort(_context,"请再次输入密码");
                    return;
                }
                if (!checkMobile(et_register_phone.getText().toString())){
                    ToastUtil.showShort(_context,"请输入正确的手机号码");
                    return;
                }
                if (!et_password.getText().toString().equals(et_register_passwordagain.getText().toString())){
                    ToastUtil.showShort(_context,"两次输入的密码不一致");
                    return;
                }
                if (!checkPwd(et_password.getText().toString())){
                    ToastUtil.showLong(_context,"密码格式不正确");
                    return;
                }
                //这里需要调用接口，写注册方法
                register();
                break;
        }
    }
    private void captcha(){
        OkHttpUtils
                .get()
                .url(HttpUtil.HOST+HttpUtil.CODE)
                .addParams("phone", et_register_phone.getText().toString())
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtil.showShort(_context,"失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ToastUtil.showShort(_context,response);
                    }
                });
    }
    private void register(){
        OkHttpUtils
                .get()
                .url(HttpUtil.SOCKET_HOST+HttpUtil.REGISTER)
                .addParams("tel", et_register_phone.getText().toString())
                .addParams("pwd", et_password.getText().toString())
                .addParams("code",et_captcha.getText().toString())
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtil.showShort(_context,"注册失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ToastUtil.showShort(_context,response);
                        Intent intent = new Intent();
                        intent.putExtra("tel",et_register_phone.getText().toString());
                        intent.putExtra("pwd",et_password.getText().toString());
                        setResult(RESULT_OK,intent);
                        onBackPressed();
                    }
                });
    }

    //手机号码格式验证
    public static boolean checkMobile(String mobile) {
        String regex = "(\\+\\d+)?1[345768]\\d{9}$";
        return Pattern.matches(regex, mobile);
    }
    //密码格式验证
    public static boolean checkPwd(String pwd){
        String regex = "[0-9a-zA-Z_]{6,18}";
        return Pattern.matches(regex,pwd);
    }
}
