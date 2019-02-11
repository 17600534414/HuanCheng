package com.huancheng.reader.ui.activity.sub;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huancheng.reader.R;
import com.huancheng.reader.bean.LoginPhoneBean;
import com.huancheng.reader.bean.UserBean;
import com.huancheng.reader.common.SaveLocalData;
import com.huancheng.reader.dialog.ActionSheetDialog;
import com.huancheng.reader.ui.activity.base.BaseBussActivity;
import com.huancheng.reader.util.BitmapImageUtil;
import com.huancheng.reader.util.GsonUtil;
import com.huancheng.reader.util.HttpUtil;
import com.huancheng.reader.util.PhotoUtils;
import com.huancheng.reader.util.SharedPreferencesUtil;
import com.huancheng.reader.util.ToastUtil;
import com.ywp.addresspickerlib.AddressPickerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


import android.view.ViewGroup.LayoutParams;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ModifyDataActivity extends BaseBussActivity {


    @BindView(R.id.iv_left_title_bar)
    ImageView ivLeftTitleBar;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.iv_right_title_bar)
    ImageView ivRightTitleBar;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.rl_birthday)
    RelativeLayout rlBirthday;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.rl_sex)
    RelativeLayout rlSex;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.rl_address)
    RelativeLayout rlAddress;
    @BindView(R.id.iv_complete)
    ImageView ivComplete;

    //拍照打开系统相册获取图片
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
    private Uri imageUri;
    private Uri cropImageUri;
    private Bitmap bitmap;//压缩之前的
    private Bitmap bitmap1;//压缩之后的

    private String sex;
    int M_NSCREENWIDTH;//屏幕宽度
    int M_NSCREENHEIGHT;//屏幕高度
    private Calendar calendar;

    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        _context = ModifyDataActivity.this;
        setContentView(R.layout.activity_modify_data);
        ButterKnife.bind(this);
        // 获取屏幕的高宽
        DisplayMetrics metrics = new DisplayMetrics();
        _context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        M_NSCREENWIDTH = metrics.widthPixels; // 屏幕宽度
        M_NSCREENHEIGHT = metrics.heightPixels; // 屏幕高度
    }

    @Override
    protected void initView() {
        super.initView();
        tvBack.setText("我的资料");
        ivRightTitleBar.setVisibility(View.GONE);

    }

    @Override
    protected void initData() {
        super.initData();
        UserBean userBean = SharedPreferencesUtil.getObject(_context,SaveLocalData.USER_BEAN,UserBean.class);

        Glide.with(this)
                .load(userBean.getIconurl())
                .placeholder(R.mipmap.me_data_haed)
                .into(ivHead);
        tvName.setText(userBean.getName());
        tvBirthday.setText(userBean.getAge());
        tvSex.setText(userBean.getSex());
        tvAddress.setText(userBean.getAddress());
    }

    @Override
    protected void BindComponentEvent() {
        super.BindComponentEvent();
    }

    @OnClick({R.id.iv_left_title_bar, R.id.iv_head, R.id.rl_name, R.id.rl_birthday, R.id.rl_sex, R.id.rl_address, R.id.iv_complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left_title_bar:
                onBackPressed();
                break;
            case R.id.iv_head://头像
                GetheadImage();
                break;
            case R.id.rl_name:
                rename();//昵称
                break;
            case R.id.rl_birthday://生日
                setDate();
                break;
            case R.id.rl_sex://性别
                ActionSheetDialog dialog = new ActionSheetDialog(_context).builder()
                        .addSheetItem("男", ActionSheetDialog.SheetItemColor.BLUES, new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                tvSex.setText("男");
                            }
                        })
                        .addSheetItem("女", ActionSheetDialog.SheetItemColor.BLUES, new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                tvSex.setText("女");
                            }
                        })
                        .setCancelable(true)
                        .setTitle("请选择");
                dialog.show();
                break;
            case R.id.rl_address://地区选择器
                showAddressPickerPop();

                break;
            case R.id.iv_complete:
                if (!String.valueOf(cropImageUri).equals("")){
                    if (bitmap1 != null){
                        File file= null;//将要保存图片的路径
                        try {
                            file = new File(new URI(cropImageUri.toString()));
                            modifyInfo(file);
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }

                    } else {
                        ToastUtil.showShort(_context,"请选择头像");
                    }
                } else {
                    ToastUtil.showShort(_context,"请选择头像1");
                }

                break;
        }
    }

    //个人资料修改
    private void modifyInfo(File file){
        final UserBean userBean = (UserBean) SharedPreferencesUtil.getObject(_context,SaveLocalData.USER_BEAN,UserBean.class);
        OkHttpUtils
                .post()
                .url(HttpUtil.SOCKET_HOST+HttpUtil.USER_INFO)
                .addFile("file","crop_photo.jpg",file)//
                .addParams("user_name", tvName.getText().toString())
                .addParams("sex", tvSex.getText().toString())
                .addParams("address", tvAddress.getText().toString())
                .addParams("age", tvBirthday.getText().toString())
                .addParams("user_id", userBean.getId())
                .addParams("imagetype", "multipart/form-data")
                .addHeader("Connection","close")
                .build()//
                .execute(new StringCallback()
                {
                     @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtil.showShort(_context,e.getMessage());
                        Log.e("看看看",e.getMessage());
                        userBean.setAddress(tvAddress.getText().toString());
                        userBean.setSex(tvSex.getText().toString());
                        userBean.setName(tvName.getText().toString());
                        userBean.setAge(tvBirthday.getText().toString());
                        userBean.setIconurl(cropImageUri.toString());
                        SharedPreferencesUtil.setObject(_context,SaveLocalData.USER_BEAN,userBean);
                        onBackPressed();
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
//                                SharedPreferencesUtil.setParam(_context, SaveLocalData.USER_ID,userBean.getId());
//                                SharedPreferencesUtil.setObject(_context,SaveLocalData.USER_BEAN,userBean);
                                startActivity(MainActivity.class,null);
                                onBackPressed();
                                break;
                            case 500:
                                ToastUtil.showShort(_context,loginPhoneBean.getMsg());
                                break;
                        }
                    }
                });
    }

//    private void get(File bitMapFile){
//        UserBean userBean = (UserBean) SharedPreferencesUtil.getObject(_context,SaveLocalData.USER_BEAN,UserBean.class);
//        OkHttpClient mOkHttpClent = new OkHttpClient();
//        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
//        if(bitMapFile!= null){//bitMapFile你要上传的图片文件   file格式的
//            // MediaType.parse() 里面是上传的文件类型。
//            RequestBody body = RequestBody.create(MediaType.parse("image/*"), bitMapFile);
//            String filename = bitMapFile.getName();
//            // 参数分别为， 请求key ，文件名称 ， RequestBody
//            builder.addFormDataPart(filename, bitMapFile.getName(), body);
//            builder.addFormDataPart("user_name", tvName.getText().toString());//写其他参数键值对的形式
//            builder.addFormDataPart("sex", tvSex.getText().toString());
//            builder.addFormDataPart("address", tvAddress.getText().toString());
//            builder.addFormDataPart("age", tvBirthday.getText().toString());
//            builder.addFormDataPart("user_id", userBean.getId());
//            builder.addFormDataPart("imagetype", "multipart/form-data");
//        }
//        RequestBody requestBody = builder.build();
//        Request request = new Request.Builder()
//                .url(HttpUtil.SOCKET_HOST+HttpUtil.USER_INFO)
//                .post(requestBody)
//                .build();
//        Call call = mOkHttpClent.newCall(request);
//        call.enqueue(new okhttp3.Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//                ToastUtil.showShort(_context,e.getMessage());
//                        Log.e("看看看",e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.e("看看看",response.toString());
//                LoginPhoneBean loginPhoneBean = GsonUtil.getBeanFromJson(response.body().toString(),LoginPhoneBean.class);
//                        switch (loginPhoneBean.getCode()){
//                            case 0:
//                                UserBean userBean = loginPhoneBean.getUser();
////                                Bundle bundle = new Bundle();
////                                bundle.putSerializable("userbean",userBean);
//                                //将用户的ID和對象保存到本地
//                                SharedPreferencesUtil.setParam(_context, SaveLocalData.USER_ID,userBean.getId());
//                                SharedPreferencesUtil.setObject(_context,SaveLocalData.USER_BEAN,userBean);
//                                startActivity(MainActivity.class,null);
//                                onBackPressed();
//                                break;
//                            case 500:
//                                ToastUtil.showShort(_context,loginPhoneBean.getMsg());
//                                break;
//                        }
//            }
//        });
//
//    }

    /**
     * 日期选择
     */
    public void setDate() {
        //点击"日期"按钮布局 设置日期
        //通过自定义控件AlertDialog实现
        AlertDialog.Builder builder = new AlertDialog.Builder(_context);
        View view = (LinearLayout) getLayoutInflater().inflate(R.layout.date, null);
        final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
        //设置日期简略显示 否则详细显示 包括:星期\周
        datePicker.setCalendarViewShown(false);
        //初始化当前日期
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), null);
        //设置date布局
        builder.setView(view);
        builder.setTitle("设置日期信息");
        builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //日期格式
                tvBirthday.setText(datePicker.getYear() + "-" + (datePicker.getMonth()+1) + "-" + datePicker.getDayOfMonth());
                dialog.cancel();
            }
        });
        builder.setNegativeButton("取  消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    //编辑昵称
    public void rename() {
        LayoutInflater factory = LayoutInflater.from(this);
        View textEntryView = factory.inflate(R.layout.edit_name, null);
        // removeView();
        //内部局部类，只能访问方法的final类型的变量
        final EditText mname_edit = (EditText) textEntryView
                .findViewById(R.id.rename_edit);
        // 不是用这个方法获取EditText的内容的
        // mname_edit.addTextChangedListener(mTextWatcher);
        // create a dialog
        new AlertDialog.Builder(_context)
                .setTitle("请出入昵称")
                .setView(textEntryView)
                .setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // dialog.
                                // TODO Auto-generated method stub
                                if (!mname_edit.getText().toString().equals("")) {
                                    tvName.setText(mname_edit.getText().toString());
                                }
                                // newName = mname_edit.getText().toString();

                            }

                        }).show();

    }

    //选择地区
    private void showAddressPickerPop() {
        final PopupWindow popupWindow = new PopupWindow(_context);
        View rootView = LayoutInflater.from(this).inflate(R.layout.address, null, false);
        AddressPickerView addressView = rootView.findViewById(R.id.apvAddress);
        addressView.setOnAddressPickerSure(new AddressPickerView.OnAddressPickerSureListener() {
            @Override
            public void onSureClick(String address, String provinceCode, String cityCode, String districtCode) {
                tvAddress.setText(address);
                popupWindow.dismiss();
            }
        });
        popupWindow.setContentView(rootView);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.showAsDropDown(tvAddress);

    }

    //获取头像
    private void GetheadImage(){
        ActionSheetDialog dialog = new ActionSheetDialog(_context);
        dialog.builder();
        dialog.addSheetItem("拍照", ActionSheetDialog.SheetItemColor.BLUES, new ActionSheetDialog.OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
               //拍照
                autoObtainCameraPermission();
            }
        });
        dialog.addSheetItem("相册", ActionSheetDialog.SheetItemColor.BLUES, new ActionSheetDialog.OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //调用相册里的照片
                autoObtainStoragePermission();
            }
        }).setCancelable(true);
        dialog.setTitle("请选择");
        dialog.show();
    }

    /**
     * 自动获取相机权限
     */
    private void autoObtainCameraPermission() {

        if (ContextCompat.checkSelfPermission(_context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(_context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(_context, Manifest.permission.CAMERA)) {
                ToastUtil.showShort(_context, "您已经拒绝过一次");
            }
            ActivityCompat.requestPermissions(_context, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, CAMERA_PERMISSIONS_REQUEST_CODE);
        } else {//有权限直接调用系统相机拍照
            if (hasSdcard()) {
                imageUri = Uri.fromFile(fileUri);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    imageUri = FileProvider.getUriForFile(_context, "com.zz.fileprovider", fileUri);//通过FileProvider创建一个content类型的Uri
                PhotoUtils.takePicture(_context, imageUri, CODE_CAMERA_REQUEST);
            } else {
                ToastUtil.showShort(_context, "设备没有SD卡！");
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case CAMERA_PERMISSIONS_REQUEST_CODE: {//调用系统相机申请拍照权限回调
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (hasSdcard()) {
                        imageUri = Uri.fromFile(fileUri);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            imageUri = FileProvider.getUriForFile(_context, "com.zz.fileprovider", fileUri);//通过FileProvider创建一个content类型的Uri
                        PhotoUtils.takePicture(_context, imageUri, CODE_CAMERA_REQUEST);
                    } else {
                        ToastUtil.showShort(_context, "设备没有SD卡！");
                    }
                } else {

                    ToastUtil.showShort(_context, "请允许打开相机！！");
                }
                break;


            }
            case STORAGE_PERMISSIONS_REQUEST_CODE://调用系统相册申请Sdcard权限回调
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    PhotoUtils.openPic(_context, CODE_GALLERY_REQUEST);
                } else {

                    ToastUtil.showShort(_context, "请允许打操作SDCard！！");
                }
                break;
        }
    }

    private int output_X = 300;
    private int output_Y = 300;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CODE_CAMERA_REQUEST://拍照完成回调
                    cropImageUri = Uri.fromFile(fileCropUri);
                    PhotoUtils.cropImageUri(_context, imageUri, cropImageUri, 1, 1, output_X, output_Y, CODE_RESULT_REQUEST);
                    break;
                case CODE_GALLERY_REQUEST://访问相册完成回调
                    if (hasSdcard()) {
                        cropImageUri = Uri.fromFile(fileCropUri);
                        Uri newUri = Uri.parse(PhotoUtils.getPath(_context, data.getData()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            newUri = FileProvider.getUriForFile(_context, "com.zz.fileprovider", new File(newUri.getPath()));
                        PhotoUtils.cropImageUri(_context, newUri, cropImageUri, 1, 1, output_X, output_Y, CODE_RESULT_REQUEST);
                    } else {
                        ToastUtil.showShort(_context, "设备没有SD卡！");
                    }
                    break;
                case CODE_RESULT_REQUEST:
                    bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, _context);
                    bitmap1 = BitmapImageUtil.comp(bitmap);
                    if (bitmap1 != null) {
                        showImages(bitmap1);
                    }
                    break;
            }
        }
    }


    /**
     * 自动获取sdk权限
     */

    private void autoObtainStoragePermission() {
        if (ContextCompat.checkSelfPermission(_context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(_context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSIONS_REQUEST_CODE);
        } else {
            PhotoUtils.openPic(_context, CODE_GALLERY_REQUEST);
        }

    }

    private void showImages(Bitmap bitmap) {
        ivHead.setImageBitmap(bitmap);
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

}
