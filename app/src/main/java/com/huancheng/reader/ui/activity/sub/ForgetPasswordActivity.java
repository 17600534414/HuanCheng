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
import com.huancheng.reader.bean.ForgetPasswordBean;
import com.huancheng.reader.bean.GetCodeBean;
import com.huancheng.reader.ui.activity.base.BaseBussActivity;
import com.huancheng.reader.util.GsonUtil;
import com.huancheng.reader.util.HttpUtil;
import com.huancheng.reader.util.StringUtil;
import com.huancheng.reader.util.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;

public class ForgetPasswordActivity extends BaseBussActivity implements View.OnClickListener{

    private EditText et_forget_phone,et_forget_code,et_forget_password,et_forget_password_again;//手机号、验证码、第一次输入密码、再次确认密码
    private TextView tv_forget_code,tv_forget_wechar,tv_forget_qq;//验证码点击、微信登录、QQ登录
    private ImageView iv_forget_eye1,iv_forget_eye2;//密码后面的小图,再次输入密码后的小图
    private Button btn_confirm;//确认按钮
    private ImageView iv_left_title_bar,iv_right_title_bar;//头部左边返回键、头部右边图标
    private TextView tv_back;//头部中间文字
    private boolean flag =true;
    private boolean flag1 = true;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (msg.arg1 == 0) {
                        tv_forget_code.setText("验证码");
                        tv_forget_code.setClickable(true);
                    } else {
                        tv_forget_code.setText("(" + msg.arg1 + ")秒");
                        tv_forget_code.setClickable(false);
                    }
                    break;
            }
        }
    };

    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        _context = ForgetPasswordActivity.this;
        setContentView(R.layout.activity_forget_password);
    }

    @Override
    protected void initView() {
        super.initView();
        et_forget_phone = findViewById(R.id.et_forget_phone);
        et_forget_code = findViewById(R.id.et_forget_code);
        et_forget_password = findViewById(R.id.et_forget_password);
        et_forget_password_again = findViewById(R.id.et_forget_password_again);
        tv_forget_code = findViewById(R.id.tv_forget_code);
        tv_forget_wechar = findViewById(R.id.tv_forget_wechar);
        tv_forget_qq = findViewById(R.id.tv_forget_qq);
        iv_forget_eye1 = findViewById(R.id.iv_forget_eye1);
        iv_forget_eye2 = findViewById(R.id.iv_forget_eye2);
        btn_confirm = findViewById(R.id.btn_confirm);
        tv_back = findViewById(R.id.tv_back);
        iv_left_title_bar = findViewById(R.id.iv_left_title_bar);
        iv_right_title_bar = findViewById(R.id.iv_right_title_bar);
        iv_right_title_bar.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        super.initData();
        et_forget_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        et_forget_password_again.setTransformationMethod(PasswordTransformationMethod.getInstance());
        iv_forget_eye1.setBackgroundResource(R.mipmap.login_code2);
        iv_forget_eye2.setBackgroundResource(R.mipmap.login_code2);
        tv_back.setText("忘记密码");
    }

    @Override
    protected void BindComponentEvent() {
        super.BindComponentEvent();
        tv_forget_code.setOnClickListener(this);
        tv_forget_wechar.setOnClickListener(this);
        tv_forget_qq.setOnClickListener(this);
        iv_forget_eye1.setOnClickListener(this);
        iv_forget_eye2.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);
        iv_left_title_bar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_left_title_bar:
                onBackPressed();
                break;
            case R.id.tv_forget_code://获取验证码按钮

            if (StringUtil.isEmpty(et_forget_phone.getText().toString())){
                ToastUtil.showLong(_context,"请输入手机号码");
                return;
            }
            if (!checkMobile(et_forget_phone.getText().toString())){
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
            captcha();
                break;
            case R.id.tv_forget_wechar://微信登录

                break;
            case R.id.tv_forget_qq://QQ登录

                break;
            case R.id.iv_forget_eye1://密码后面的小图
                if (flag == true) {
                    // TODO Auto-generated method stub
                    iv_forget_eye1.setBackgroundResource(R.mipmap.login_code1);
                    et_forget_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    flag = false;
                } else {
                    iv_forget_eye1.setBackgroundResource(R.mipmap.login_code2);
                    et_forget_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    flag = true;
                }
                break;
            case R.id.iv_forget_eye2://再次输入密码后的小图
                if (flag1 == true) {
                    // TODO Auto-generated method stub
                    iv_forget_eye2.setBackgroundResource(R.mipmap.login_code1);
                    et_forget_password_again.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    flag1 = false;
                } else {
                    iv_forget_eye2.setBackgroundResource(R.mipmap.login_code2);
                    et_forget_password_again.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    flag1 = true;
                }
                break;
            case R.id.btn_confirm://确认按钮
                if (StringUtil.isEmpty(et_forget_phone.getText().toString())){
                    ToastUtil.showShort(_context,"手机号码不能为空");
                    return;
                }
                if (StringUtil.isEmpty(et_forget_code.getText().toString())){
                    ToastUtil.showShort(_context,"验证码不能为空");
                    return;
                }
                if (StringUtil.isEmpty(et_forget_password.getText().toString())){
                    ToastUtil.showShort(_context,"密码不能为空");
                    return;
                }
                if (StringUtil.isEmpty(et_forget_password_again.getText().toString())){
                    ToastUtil.showShort(_context,"请再次输入密码");
                    return;
                }
                if (!checkMobile(et_forget_phone.getText().toString())){
                    ToastUtil.showShort(_context,"请输入正确的手机号码");
                    return;
                }
                if (!et_forget_password.getText().toString().equals(et_forget_password_again.getText().toString())){
                    ToastUtil.showShort(_context,"两次输入的密码不一致");
                    return;
                }
                if (!checkPwd(et_forget_password.getText().toString())){
                    ToastUtil.showLong(_context,"密码格式不正确");
                    return;
                }
                forgetPassword();
                //这里要调用接口修改密码方法

                break;
        }
    }
    //忘记密码
    private void forgetPassword(){
        OkHttpUtils
                .get()
                .url(HttpUtil.SOCKET_HOST+HttpUtil.FORGET_PASSWORD)
                .addParams("tel", et_forget_phone.getText().toString())
                .addParams("pwd", et_forget_password.getText().toString())
                .addParams("code",et_forget_code.getText().toString())
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtil.showShort(_context,"修改失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ToastUtil.showShort(_context,response);
                        ForgetPasswordBean forgetPasswordBean = GsonUtil.getBeanFromJson(response,ForgetPasswordBean.class);
                        switch (Integer.valueOf(forgetPasswordBean.getCode())){
                            case 0:
                                ToastUtil.showShort(_context,forgetPasswordBean.getMsg());
                                startActivity(MainActivity.class,null);
                                onBackPressed();
                                break;
                            case 500:
                                ToastUtil.showShort(_context,forgetPasswordBean.getMsg());
                                break;
                        }
                    }
                });
    }
    //获取验证码
    private void captcha(){
        OkHttpUtils
                .get()
                .url(HttpUtil.HOST+HttpUtil.CODE)
                .addParams("phone", et_forget_phone.getText().toString())
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
//                        GetCodeBean getCodeBean = GsonUtil.getBeanFromJson(response, GetCodeBean.class);
//                        int code = Integer.valueOf(getCodeBean.getCode());
//                        switch (code){
//                            case 0:
//                                ToastUtil.showShort(_context,getCodeBean.getMsg());
//                                break;
//                            case 500:
//                                ToastUtil.showShort(_context,getCodeBean.getMsg());
//                                break;
//                        }
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
