package fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.wxb.jianbao.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import activity.AttentionActivity;
import activity.PublishedActivity;
import activity.SettingsActivity;
import activity.SoldActivity;
import app.Contant;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import javabeen.CodeBeen;
import javabeen.GeRenXinxi;
import javabeen.Uphoto;
import utils.OkhttpUtils;
import utils.PhotoPostUtils;
import utils.TakePhotoPopWin;

import static com.wxb.jianbao.R.id.mine_iv_photo;
import static com.wxb.jianbao.R.id.mine_tv_invitationCode;


/**
 * Created by Administrator on 2016/11/27.
 */

public class MineFragment extends Fragment {
    /*ImageView mineIvPhoto;*/
    @InjectView(mine_iv_photo)
    SimpleDraweeView mineIvPhoto;
    @InjectView(R.id.mine_tv_name)
    TextView mineTvName;
    @InjectView(R.id.mine_tv_phoneNum)
    TextView mineTvPhoneNum;
    @InjectView(R.id.mine_tv_published)
    TextView mineTvPublished;
    @InjectView(R.id.mine_tv_sold)
    TextView mineTvSold;
    @InjectView(R.id.mine_tv_attention)
    TextView mineTvAttention;
    @InjectView(mine_tv_invitationCode)
    TextView mineTvInvitationCode;
    @InjectView(R.id.mine_bt)
    Button mineTv;
    @InjectView(R.id.mine_ll_settings)
    LinearLayout mineLlSettings;
    private File imageFile;
    private Uri uri;
    private SharedPreferences.Editor edit;

    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果

    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private TakePhotoPopWin photoPopWin;
    private int state;

    private String code;
    private String token;
    private Uri faceUri;
    private String facePath;
    private Handler mHandler = new Handler();
    private File file;
    private String TouXPath = Contant.TouXiang;
    private HashMap<String, String> map;
    private String path;
    private String selectImaPath;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.inject(this, view);
        SharedPreferences sp = getActivity().getSharedPreferences("TOKEN", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        initMine();
        facePath = Environment.getExternalStorageDirectory() + "/face.jpg";
        return view;
    }

    //个人信息
    private void initMine() {
//        Uri imgurl=Uri.parse(item.getImg());
//        // 清除Fresco对这条验证码的缓存
//        ImagePipeline imagePipeline = Fresco.getImagePipeline();
//        imagePipeline.evictFromMemoryCache(imgurl);
//        imagePipeline.evictFromDiskCache(imgurl);
//        // combines above two lines
//        imagePipeline.evictFromCache(imgurl);
        final String path = Contant.GeRenXinXi;
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        OkhttpUtils.setGetEntiydata(new OkhttpUtils.EntiyData() {

            private String photo;
            private String name;
            private String mobile;

            @Override
            public void getEntiy(Object o) {
                if (o == null || !(o instanceof GeRenXinxi)) {
                    return;
                }
                GeRenXinxi geRenXinxi = (GeRenXinxi) o;
                mobile = geRenXinxi.getData().getMobile();
                name = geRenXinxi.getData().getName();
                photo = geRenXinxi.getData().getPhoto();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (photo ==null || photo.isEmpty()){
                            mineIvPhoto.setImageResource(R.mipmap.moren);
                        }else if (!photo.isEmpty() || photo != null) {
                            String path = Contant.IMGQZ + photo;
                            Uri imgurl = Uri.parse(path);
                            // 清除Fresco对这条验证码的缓存
                            ImagePipeline imagePipeline = Fresco.getImagePipeline();
                            imagePipeline.evictFromMemoryCache(imgurl);
                            imagePipeline.evictFromDiskCache(imgurl);
                            // combines above two lines
                            imagePipeline.evictFromCache(imgurl);

                            imagePipeline.clearCaches();
                            mineIvPhoto.setImageURI(imgurl);

                        }
                        mineTvName.setText(name);
                        mineTvPhoneNum.setText(mobile);
                    }
                });
            }
        });
        OkhttpUtils.post(map, path, getActivity(), GeRenXinxi.class);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({mine_iv_photo, R.id.mine_tv_published, R.id.mine_tv_sold, R.id.mine_tv_attention, mine_tv_invitationCode, R.id.mine_bt, R.id.mine_ll_settings})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mine_tv_published:
                startActivity(new Intent(getActivity(), PublishedActivity.class));
                break;
            case R.id.mine_tv_sold:
                startActivity(new Intent(getActivity(), SoldActivity.class));
                break;
            case R.id.mine_tv_attention:
                startActivity(new Intent(getActivity(), AttentionActivity.class));
                break;
            case mine_tv_invitationCode:
                initData();
                break;
            case R.id.mine_bt:
                break;
            case R.id.mine_ll_settings:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                break;
            case mine_iv_photo:
                showPop(view);
                break;
        }
    }

    private void showPop(View v) {
        photoPopWin = new TakePhotoPopWin(getActivity(), onClickListener);
        photoPopWin.showAtLocation(mineIvPhoto, Gravity.CENTER, 0, 0);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_pick_photo:
                    pictureschose();
                    break;
                case R.id.btn_take_photo:
                    photo();
                    photoPopWin.dismiss();
                    break;
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        map = new HashMap<>();
        map.put("token", token);

//        SharedPreferences share = getActivity().getSharedPreferences("PATH.", Context.MODE_PRIVATE);
//        SharedPreferences.Editor edit = share.edit();

        // 拍照后获取返回值，这里获取到的是原始图片
        if (requestCode == PHOTO_REQUEST_CAREMA && resultCode == Activity.RESULT_OK) {

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    File file = new File(facePath);
                    Uri uri = Uri.fromFile(file);
                    mineIvPhoto.setImageURI(uri);
                    upLoadFaceIcon(file);
                }
            }, 500);

        } else if (requestCode == PHOTO_REQUEST_GALLERY && resultCode == Activity.RESULT_OK) {
            if (photoPopWin.isShowing()) {
                photoPopWin.dismiss();
            }

            if (data != null) {
                try {
                    // 获得图片的uri
                    Uri originalUri = data.getData();
                    selectImaPath = originalUri.toString();
                    if (selectImaPath.startsWith("file:///s")) {

                    } else {
                        String[] proj = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getActivity().managedQuery(originalUri, proj, null, null, null);
                        if (cursor != null && cursor.moveToFirst()) {
                            String string = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                            //按我个人理解 这个是获得用户选择的图片的索引值
                            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                            //将光标移至开头 ，这个很重要，不小心很容易引起越界
                            cursor.moveToFirst();
                            //最后根据索引值获取图片路径
                            selectImaPath = cursor.getString(column_index);
                        }
                    }

                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
//                            File file = new File(selectImaPath);
//                            Uri parse = Uri.fromFile(file);

                            ImagePipeline imagePipeline = Fresco.getImagePipeline();
                            imagePipeline.evictFromMemoryCache(Uri.parse(selectImaPath));
                            imagePipeline.evictFromDiskCache(Uri.parse(selectImaPath));
                            // combines above two lines
                            imagePipeline.evictFromCache(Uri.parse(selectImaPath));
                            imagePipeline.clearCaches();
                            requestImage(selectImaPath);
                            String replace = selectImaPath.replace("file://", "");
                            upLoadFaceIcon(new File(replace));
                        }
                    }, 500);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } else if (requestCode == PHOTO_REQUEST_CUT) {
            mineIvPhoto.setImageURI(imageFile.getAbsolutePath());
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void crop(Uri uri) {

        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }


    private void pictureschose() {
        Intent intent = new Intent();
        /* 开启Pictures画面Type设定为image */
        intent.setType("image/*");
        /* 使用Intent.ACTION_GET_CONTENT这个Action */
        intent.setAction(Intent.ACTION_GET_CONTENT);

        /* 取得相片后返回本画面 */
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }


    public void photo() {
        imageFile = new File(facePath);
        // 启动系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 设置拍照后保存的图片存储在文件中
        uri = Uri.fromFile(imageFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        // 启动activity并获取返回数据
        startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
    }

    //上传图片
    private void upLoadFaceIcon(File file) {
        PhotoPostUtils.getData(new PhotoPostUtils.GetRegisterData() {
            @Override
            public void setRegisterData(Object o) {
                if (o != null && o instanceof Uphoto) {
                    Uphoto o1 = (Uphoto) o;
                    String status = o1.getStatus();
                    if ("200".equals(status)) {
                        //Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                        System.out.println("上传成功");
                    }
                }
            }
        });

        PhotoPostUtils.upLoad(file, getActivity(), TouXPath, map, Uphoto.class);
    }

    private boolean initImageFile() {
        // 有SD卡时才初始化文件
        if (hasSdcard()) {
//            // 构造存储图片的文件的路径，文件名为当前时间
            String filePath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath()
                    + "/"
                    + System.currentTimeMillis()
                    + ".jpg";

            imageFile = new File(filePath);
            if (!imageFile.exists()) {// 如果文件不存在，就创建文件
                try {
                    imageFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
        return false;
    }

    /*
   * 判断sdcard是否存在
   */
    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    public static Bitmap getPicFromBytes(byte[] bytes, BitmapFactory.Options opts) {
        if (bytes != null) {
            if (opts != null) {
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
            } else {
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            }
        }
        return null;
    }

    public static byte[] readStream(InputStream inStream) throws Exception {
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;
    }

    private void initData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        String path = Contant.InvitationCode;
        OkhttpUtils.setGetEntiydata(new OkhttpUtils.EntiyData() {
            @Override
            public void getEntiy(Object o) {
                if (o == null || !(o instanceof CodeBeen)) {
                    return;
                }
                CodeBeen codeBeen = (CodeBeen) o;
                String status = codeBeen.getStatus();
                if (status.equals("200")) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(getActivity(), "获取邀请码成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "获取邀请码失败", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                CodeBeen.DataBean data = codeBeen.getData();
                if (data == null) {
                    return;
                }

                code = data.getCode();

                state = data.getState();

                if (state == 0) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mineTvInvitationCode.setText(code);
                            mineTvInvitationCode.setTextColor(getResources().getColor(R.color.black));
                            mineTvInvitationCode.setTextIsSelectable(true);
                        }
                    });
                } else if (state == 1) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mineTvInvitationCode.setText(code + " 已失效");
                            mineTvInvitationCode.setTextColor(getResources().getColor(R.color.red));
                            mineTvInvitationCode.setTextIsSelectable(false);
                        }
                    });
                }
            }
        });

        OkhttpUtils.post(map, path, getActivity(), CodeBeen.class);
    }

    private void requestImage(String url) {
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                .setAutoRotateEnabled(true)
                .build();

        PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .build();

        mineIvPhoto.setController(controller);
    }

}