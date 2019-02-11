package com.huancheng.reader.ui.activity.sub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.huancheng.reader.R;
import com.huancheng.reader.bean.LoginPhoneBean;
import com.huancheng.reader.bean.UserBean;
import com.huancheng.reader.common.SaveLocalData;
import com.huancheng.reader.ui.activity.base.BaseBussActivity;
import com.huancheng.reader.util.GsonUtil;
import com.huancheng.reader.util.HttpUtil;
import com.huancheng.reader.util.SharedPreferencesUtil;
import com.huancheng.reader.util.StringUtil;
import com.huancheng.reader.util.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.regex.Pattern;

import okhttp3.Call;


public class LoginPhoneActivity extends BaseBussActivity implements View.OnClickListener{

    private EditText et_login_phone,et_login_password;//手机号、密码
    private Button btn_login_register,btn_login_login;//注册按钮
    private TextView tv_login_wechat,tv_login_qq,tv_forget_password,tv_back;//微信登录、qq登录
    private ImageView iv_eye,iv_left_title_bar,iv_right_title_bar;//设置密码后面的小图
    private boolean flag = true;
    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        _context = LoginPhoneActivity.this;
        setContentView(R.layout.activity_login_phone);
    }

    @Override
    protected void initView() {
        super.initView();
        iv_eye = findViewById(R.id.iv_eye);
        et_login_phone = findViewById(R.id.et_login_phone);
        et_login_password = findViewById(R.id.et_login_password);
        btn_login_register = findViewById(R.id.btn_login_register);
        tv_login_wechat = findViewById(R.id.tv_login_wechat);
        tv_login_qq = findViewById(R.id.tv_login_qq);
        btn_login_login = findViewById(R.id.btn_login_login);
        tv_forget_password = findViewById(R.id.tv_forget_password);
        tv_back = findViewById(R.id.tv_back);
        iv_left_title_bar = findViewById(R.id.iv_left_title_bar);
        iv_right_title_bar = findViewById(R.id.iv_right_title_bar);
        iv_left_title_bar.setVisibility(View.GONE);
        iv_right_title_bar.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        super.initData();
        et_login_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        iv_eye.setBackgroundResource(R.mipmap.login_code2);
        tv_back.setText("登录");
//        String user_id = (String) SharedPreferencesUtil.getParam(_context,SaveLocalData.USER_ID,"");
//        if(!StringUtil.isEmpty(user_id)) {
//            startActivity(MainActivity.class, null);
//            onBackPressed();
//        }
    }

    @Override
    protected void BindComponentEvent() {
        super.BindComponentEvent();
        btn_login_register.setOnClickListener(this);
        btn_login_login.setOnClickListener(this);
        tv_forget_password.setOnClickListener(this);
        tv_login_wechat.setOnClickListener(this);
        tv_login_qq.setOnClickListener(this);
        iv_eye.setOnClickListener(this);
//        iv_left_title_bar.setOnClickListener(this);
//        iv_right_title_bar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login_register://注册按钮
                startActivityForResult(RegisterActivity.class,null,1);
                break;
            case R.id.btn_login_login://登录按钮
                if (StringUtil.isEmpty(et_login_phone.getText().toString())){
                    ToastUtil.showShort(_context,"手机号码不能为空");
                    return;
                }
                if (StringUtil.isEmpty(et_login_password.getText().toString())){
                    ToastUtil.showShort(_context,"密码不能为空");
                }
                if (!checkMobile(et_login_phone.getText().toString())){
                    ToastUtil.showShort(_context,"请输入正确的手机号码");
                    return;
                }
                if (!checkPwd(et_login_password.getText().toString())){
                    ToastUtil.showLong(_context,"密码格式不正确");
                    return;
                }
                login();

                //这里需要用到接口，登录方法
                break;
            case R.id.tv_forget_password://忘记密码
                startActivity(ForgetPasswordActivity.class,null);
                break;
            case R.id.tv_login_wechat://微信登录

                break;
            case R.id.tv_login_qq://QQ登录

                break;
            case R.id.iv_eye:
                if (flag == true) {
                    // TODO Auto-generated method stub
                    iv_eye.setBackgroundResource(R.mipmap.login_code1);
                    et_login_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    flag = false;
                } else {
                    iv_eye.setBackgroundResource(R.mipmap.login_code2);
                    et_login_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    flag = true;
                }

                break;
        }
    }
    //登录
    private void login(){
        OkHttpUtils
                .get()
                .url(HttpUtil.SOCKET_HOST+HttpUtil.LOGIN)
                .addParams("tel", et_login_phone.getText().toString())
                .addParams("pwd", et_login_password.getText().toString())
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtil.showShort(_context,"登录失败");
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        LoginPhoneBean loginPhoneBean = GsonUtil.getBeanFromJson(response,LoginPhoneBean.class);
                        switch (loginPhoneBean.getCode()){
                            case 0:
                                 UserBean userBean = loginPhoneBean.getUser();
//                                Bundle bundle = new Bundle();
//                                bundle.putSerializable("userbean",userBean);
                                //将用户的ID和對象保存到本地
                                SharedPreferencesUtil.setParam(_context,SaveLocalData.USER_ID,userBean.getId());
                                SharedPreferencesUtil.setObject(_context,SaveLocalData.USER_BEAN,userBean);
                                startActivity(MainActivity.class,null);
                                long time=System.currentTimeMillis();
                                SharedPreferencesUtil.setParam(_context,SaveLocalData.TIME,time);
                                onBackPressed();
                                break;
                            case 500:
                                ToastUtil.showShort(_context,loginPhoneBean.getMsg());
                                break;
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode){
            case 1:
                if(intent != null){
                    et_login_phone.setText(intent.getStringExtra("tel"));
                    et_login_password.setText(intent.getStringExtra("pwd"));
                }
                break;
        }
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

