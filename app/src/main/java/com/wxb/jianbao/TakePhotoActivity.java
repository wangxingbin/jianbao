package com.wxb.jianbao;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import utils.ImageTools;

/**
 * Created by 赵自强 on 2016/11/29.
 */

public class TakePhotoActivity extends Activity {

    private static final int TAKE_PICTURE = 0; // 相机
    private static final int CHOOSE_PICTURE = 1; // 图片
    private static final int SCALE = 5;//照片缩小比例
    private ImageView takePhoto_picture, takePhotot_photo, takePhoto_picture2,
            takePhoto_picture3, takePhoto_picture4, takePhoto_picture5;
    private TextView takePhotot_text;
    private TextView takePhoto_jump;
    private ImageView[] imageViews;
    // 存放图片路径
    private ArrayList<String> mList = new ArrayList<String>();
    private HashMap<ImageView, PictureType> map = new HashMap<ImageView, PictureType>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.takephoto_item);
        initView();
        initButton_photo();
        initButoon_jump();
    }

    private void initView() {

        takePhotot_photo = (ImageView) findViewById(R.id.takephoto_photo);// 相机
        takePhotot_text = (TextView) findViewById(R.id.takephoto_text);  // 相册
        takePhoto_picture = (ImageView) findViewById(R.id.takephoto_picture); // 图片
        takePhoto_picture2 = (ImageView) findViewById(R.id.takephoto_picture2);   // 图片
        takePhoto_picture3 = (ImageView) findViewById(R.id.takephoto_picture3); // 图片
        takePhoto_picture4 = (ImageView) findViewById(R.id.takephoto_picture4); // 图片
        takePhoto_picture5 = (ImageView) findViewById(R.id.takephoto_picture5); // 图片
        /*
        imageViews = new ImageView[]
                {takePhoto_picture, takePhoto_picture2, takePhoto_picture3, takePhoto_picture4, takePhoto_picture5};*/
        takePhoto_jump = (TextView) findViewById(R.id.takephtot_jump); //发表

    }

    // 跳转发布界面
    private void initButoon_jump() {
        takePhoto_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            // ISLOGIN
            public void onClick(View view) {
              /*  if ("ISLOGIN".equals(bean.getStatus())){
                    // 跳转到发布界面
                }else{
                    //跳转到登录界面
                }*/
                Intent intent2 = new Intent(TakePhotoActivity.this, PublishActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("imgs",mList);
                intent2.putExtras(bundle);
                startActivity(intent2);

            }
        });
    }

    // 相机相册点击事件
    private void initButton_photo() {
        takePhotot_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "image.jpg"));
                //指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(openCameraIntent, TAKE_PICTURE);
            }
        });
        takePhotot_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                openAlbumIntent.setType("image/*");
                startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case TAKE_PICTURE:
                    takePhoto();
                    break;

                case CHOOSE_PICTURE:
                    takePicture(data);
                    break;

                default:
                    break;
            }
        }
    }

    private void takePicture(Intent data) {
        ContentResolver resolver = getContentResolver();
        //照片的原始资源地址
        Uri originalUri = data.getData();
        try {
            //使用ContentProvider通过URI获取原始图片
            Bitmap photo = MediaStore.Images.Media.getBitmap(resolver, originalUri);
            if (photo != null) {
                //为防止原始图片过大导致内存溢出，这里先缩小原图显示，然后释放原始Bitmap占用的内存
                Bitmap smallBitmap = ImageTools.zoomBitmap(photo, photo.getWidth() / SCALE,
                        photo.getHeight() / SCALE);
                //释放原始图片占用的内存，防止out of memory异常发生
                photo.recycle();
                switch (map.size()) {
                    case 0:
                        takePhoto_picture.setImageBitmap(smallBitmap);
                        map.put(takePhoto_picture, PictureType.PICTURE);
                        String s = originalUri.toString();
                        mList.add(s);
                        break;
                    case 1:
                        takePhoto_picture2.setImageBitmap(smallBitmap);
                        map.put(takePhoto_picture2, PictureType.PICTURE);
                        Uri picture_uri2 = data.getData();
                        // 把Uri转化成String类型
                        String s2 = originalUri.toString();
                        mList.add(s2);
                        break;
                    case 2:
                        takePhoto_picture3.setImageBitmap(smallBitmap);
                        map.put(takePhoto_picture3, PictureType.PICTURE);
                        Uri picture_uri3 = data.getData();
                        String s3 = originalUri.toString();
                        mList.add(s3);
                        break;
                    case 3:
                        takePhoto_picture4.setImageBitmap(smallBitmap);
                        map.put(takePhoto_picture4, PictureType.PICTURE);
                        Uri picture_uri4 = data.getData();
                        String s4 = originalUri.toString();
                        mList.add(s4);
                        break;
                    case 4:
                        takePhoto_picture5.setImageBitmap(smallBitmap);
                        map.put(takePhoto_picture5, PictureType.PICTURE);
                        Uri picture_uri5 = data.getData();
                        String s5 = originalUri.toString();
                        mList.add(s5);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private void hideImageView() {
        //隐藏空间但是还占用位置
        //takePhoto_picture.setVisibility(View.INVISIBLE);
        takePhoto_picture2.setVisibility(View.INVISIBLE);
        takePhoto_picture3.setVisibility(View.INVISIBLE);
        takePhoto_picture4.setVisibility(View.INVISIBLE);
        takePhoto_picture5.setVisibility(View.INVISIBLE);

    }


    private void takePhoto() {
        //将保存在本地的图片取出并缩小后显示在界面上
        Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/image.jpg");
        Bitmap newBitmap = ImageTools.zoomBitmap(bitmap, bitmap.getWidth() / SCALE, bitmap.getHeight() / SCALE);
        //由于Bitmap内存占用较大，这里需要回收内存，否则会报out of memory异常
        bitmap.recycle();
        //将处理过的图片显示在界面上，
        switch (map.size()) {
            // 如果位置等于0的时候我们往HashMap中添加第一个图片，以后就以此类推
            case 0:
                takePhoto_picture.setImageBitmap(newBitmap);
                map.put(takePhoto_picture, PictureType.PHOTO);
                break;
            case 1:
                takePhoto_picture2.setImageBitmap(newBitmap);
                map.put(takePhoto_picture2, PictureType.PHOTO);
                break;
            case 2:
                takePhoto_picture3.setImageBitmap(newBitmap);
                map.put(takePhoto_picture3, PictureType.PHOTO);
                break;
            case 3:
                takePhoto_picture4.setImageBitmap(newBitmap);
                map.put(takePhoto_picture4, PictureType.PHOTO);
                break;
            case 4:
                takePhoto_picture5.setImageBitmap(newBitmap);
                map.put(takePhoto_picture5, PictureType.PHOTO);
                break;
        }

        // 并保存到本地

        ImageTools.savePhotoToSDCard(newBitmap, Environment.getExternalStorageDirectory().getAbsolutePath(),
                String.valueOf(System.currentTimeMillis()));
    }

    // 第一个枚举
    enum PictureType {
        PHOTO, PICTURE
    }

}
