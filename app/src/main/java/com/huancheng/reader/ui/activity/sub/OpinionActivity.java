package com.huancheng.reader.ui.activity.sub;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huancheng.reader.R;
import com.huancheng.reader.bean.FeedBackBean;
import com.huancheng.reader.bean.LoginPhoneBean;
import com.huancheng.reader.common.SaveLocalData;
import com.huancheng.reader.ui.activity.base.BaseBussActivity;
import com.huancheng.reader.util.GsonUtil;
import com.huancheng.reader.util.HttpUtil;
import com.huancheng.reader.util.SharedPreferencesUtil;
import com.huancheng.reader.util.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class OpinionActivity extends BaseBussActivity implements View.OnClickListener {


    @BindView(R.id.iv_left_title_bar)
    ImageView ivLeftTitleBar;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.iv_right_title_bar)
    ImageView ivRightTitleBar;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;
    @BindView(R.id.rbtn_proposal)
    RadioButton rbtnProposal;
    @BindView(R.id.rbtn_other)
    RadioButton rbtnOther;
    @BindView(R.id.radioGroup_gender)
    RadioGroup radioGroupGender;
    @BindView(R.id.rbtn_functional_abnormality)
    RadioButton rbtnFunctionalAbnormality;
    @BindView(R.id.btn_submission)
    Button btnSubmission;
    @BindView(R.id.et_describe)
    EditText etDescribe;//描述
    @BindView(R.id.et_time)
    EditText etTime;//时间
    @BindView(R.id.et_phone)
    EditText etPhone;//电话

    private String type = "";

    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        _context = OpinionActivity.this;
        setContentView(R.layout.activity_opinion);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();
        tvBack.setText("意见反馈");
        ivRightTitleBar.setVisibility(View.GONE);

    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    protected void BindComponentEvent() {
        super.BindComponentEvent();
        rbtnFunctionalAbnormality.setOnClickListener(this);
        rbtnProposal.setOnClickListener(this);
        rbtnOther.setOnClickListener(this);
    }

    @OnClick({R.id.iv_left_title_bar, R.id.btn_submission})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left_title_bar:
                onBackPressed();
                break;
            case R.id.btn_submission:
                //请求接口
                if (type.equals("")){
                    ToastUtil.showShort(_context,"请选择类型");
                }
                opinion();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rbtn_functional_abnormality:
                type = "功能异常";
                ToastUtil.showShort(_context, type);
                break;
            case R.id.rbtn_proposal:
                type = "建议";
                ToastUtil.showShort(_context, type);
                break;
            case R.id.rbtn_other:
                type = "其他";
                ToastUtil.showShort(_context, type);
                break;
        }
    }

    //意见反馈
    private void opinion() {
        String user_id = (String) SharedPreferencesUtil.getParam(_context, SaveLocalData.USER_ID, "");
        OkHttpUtils
                .get()
                .url(HttpUtil.SOCKET_HOST + HttpUtil.OPINION)
                .addParams("user_id", user_id)
                .addParams("contact_information", etPhone.getText().toString())
                .addParams("describe", etDescribe.getText().toString())
                .addParams("time", etTime.getText().toString())
                .addParams("type", type)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtil.showShort(_context, "失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        FeedBackBean feedBackBean = GsonUtil.getBeanFromJson(response, FeedBackBean.class);
                        switch (Integer.parseInt(feedBackBean.getCode())) {
                            case 0:
                                ToastUtil.showShort(_context,feedBackBean.getMsg());
                                onBackPressed();
                                break;
                            case 500:
                                ToastUtil.showShort(_context, feedBackBean.getMsg());
                                break;
                        }
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
