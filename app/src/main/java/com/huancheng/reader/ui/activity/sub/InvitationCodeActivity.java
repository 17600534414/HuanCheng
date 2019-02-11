package com.huancheng.reader.ui.activity.sub;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huancheng.reader.R;
import com.huancheng.reader.bean.GetInvitationCodeBean;
import com.huancheng.reader.bean.UserBean;
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



public class InvitationCodeActivity extends BaseBussActivity {

    @BindView(R.id.iv_left_title_bar)
    ImageView ivLeftTitleBar;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.iv_right_title_bar)
    ImageView ivRightTitleBar;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;
    @BindView(R.id.et_invitation_code)
    EditText etInvitationCode;
    @BindView(R.id.btn_get_book)
    ImageView btnGetBook;
    @BindView(R.id.tv_share_invitation)
    TextView tvShareInvitation;

    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        _context = InvitationCodeActivity.this;
        setContentView(R.layout.activity_invitation_code);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();
        tvBack.setText("填写邀请码");
        ivRightTitleBar.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void BindComponentEvent() {
        super.BindComponentEvent();
    }

    //填写邀请码
    private void getInvitation() {
        UserBean userBean = (UserBean) SharedPreferencesUtil.getObject(_context, SaveLocalData.USER_BEAN,UserBean.class);
        OkHttpUtils
                .get()
                .url(HttpUtil.SOCKET_HOST + HttpUtil.INVITATION_CODE)
                .addParams("userId", userBean.getId())
                .addParams("inviteCode", etInvitationCode.getText().toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtil.showShort(_context, "失败");
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        GetInvitationCodeBean getInvitationCodeBean = GsonUtil.getBeanFromJson(response, GetInvitationCodeBean.class);
                        switch (Integer.parseInt(getInvitationCodeBean.getCode())) {
                            case 0:
                                ToastUtil.showShort(_context, getInvitationCodeBean.getMsg());
                            case 500:
                                ToastUtil.showShort(_context, getInvitationCodeBean.getMsg());
                                break;
                        }
                    }
                });
    }

    @OnClick({R.id.iv_left_title_bar, R.id.btn_get_book})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left_title_bar:
                onBackPressed();
                break;
            case R.id.btn_get_book:
                getInvitation();
                break;
        }
    }
}
