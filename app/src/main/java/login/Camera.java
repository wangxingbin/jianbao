package login;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wxb.jianbao.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import view.ShowToastUtils;
import view.TakePhotoPopWin;

/**
 * Created by 孙贝贝 on 2016/11/29.
 */

public class Camera extends Activity implements View.OnClickListener {


    private static final String TAG = "Camera";
    private File imageFile;
    private Uri uri;
    private String chosepath;
    private SharedPreferences.Editor edit;
    private TakePhotoPopWin takePhotoPopWin;
    private Button btn;
    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果

    private ImageView iv_image;

    /* 头像名称 */
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private ProgressBar bar;
    private Button btn2;
    private LinearLayout layout;
    private String fileName;
    private File file;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_view);
        initview();
        SharedPreferences sharepath= getSharedPreferences("PATH",MODE_PRIVATE);
        edit = sharepath.edit();

    }

    private void initview() {
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
        this.iv_image = (ImageView) this.findViewById(R.id.image);
        bar = (ProgressBar) findViewById(R.id.progress);
        bar.setMax(100);
        bar.setProgress(0);
        bar.setScrollBarFadeDuration(3000);
        layout = (LinearLayout) findViewById(R.id.liner);

        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                showpopup(view);
                break;
            case R.id.btn2:
                layout.setVisibility(View.GONE);
                bar.setVisibility(View.VISIBLE);
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ShowToastUtils.showToast(Camera.this, "上传成功");
                            }
                        });
                        finish();


                    }
                }, 3000);

        }
    }

    private void showpopup(View view) {
        takePhotoPopWin = new TakePhotoPopWin(this, onClickListener);
        //showAtLocation(View parent, int gravity, int x, int y)
        takePhotoPopWin.showAtLocation(findViewById(R.id.btn), Gravity.CENTER, 0, 0);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btn_take_photo:
                    if (initImageFile()) {
                        photo();
                    }
                    takePhotoPopWin.dismiss();
                    break;
                case R.id.btn_pick_photo:
                    pictureschose();
                    takePhotoPopWin.dismiss();
                    break;
            }
        }
    };

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
        // 启动系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 设置拍照后保存的图片存储在文件中
        uri = Uri.fromFile(imageFile);
        crop(uri);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        // 启动activity并获取返回数据
        startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
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
   * 判断sdcard是否被挂载
   */
    private boolean  hasSdcard() {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ContentResolver resolver = getContentResolver();
        // 拍照后获取返回值，这里获取到的是原始图片
        if (requestCode == PHOTO_REQUEST_CAREMA
                && resultCode == Activity.RESULT_OK) {
            // 获取到了拍照后的图片文件，从文件解码出Bitmap对象
            if (imageFile.exists()) {
//                // 这里直接decode了图片，没有判断图片大小，没有对可能出现的OOM做处理

                edit.putString("path",imageFile.getAbsolutePath());
                edit.commit();
                Bitmap bm = BitmapFactory.decodeFile(imageFile.getAbsolutePath());

                // 显示图片
                this.iv_image.setImageBitmap(bm);
            } else {
                Toast.makeText(this, "图片文件不存在", Toast.LENGTH_SHORT).show();
            }


        } else if (requestCode == PHOTO_REQUEST_GALLERY) {
            if (data != null) {
                try {
                    // 获得图片的uri
                    Uri originalUri = data.getData();
                    String[] proj = {MediaStore.Images.Media.DATA};
                    Cursor cursor = managedQuery(originalUri, proj, null, null, null);
                    //按我个人理解 这个是获得用户选择的图片的索引值
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    //将光标移至开头 ，这个很重要，不小心很容易引起越界
                    cursor.moveToFirst();
                    //最后根据索引值获取图片路径
                    String path = cursor.getString(column_index);

                    crop(originalUri);
                    chosepath = originalUri.toString();

                    edit.putString("path",path);
                    edit.commit();

                    // 将图片内容解析成字节数组
                    byte[] mContent = readStream(resolver.openInputStream(Uri
                            .parse(chosepath)));
                    // 将字节数组转换为ImageView可调用的Bitmap对象
                    Bitmap myBitmap = getPicFromBytes(mContent, null);
                    // //把得到的图片绑定在控件上显示
                    this.iv_image.setImageBitmap(myBitmap);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }


            }

        }
        super.onActivityResult(requestCode, resultCode, data);


    }

}

