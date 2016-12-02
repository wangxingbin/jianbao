package com.wxb.jianbao;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 赵自强 on 2016/11/30.
 */

public class PublishActivity extends Activity {
    private static final String TAG = "PublishActivity";
    private ImageView publish_imgs;
    private ImageView publish_imgs2;
    private ImageView publish_imgs3;
    private ImageView publish_imgs4;
    private ImageView publish_imgs5;
    private ImageView[] imageViews = new ImageView[5];
    private List<Fragment> list = new ArrayList<Fragment>();
    private int mCurrentPageIndex;
    private int mScreen1_3;
    private EditText et_jianbao_biaoti;
    private EditText et_jianbao_miaoshu;

    //  private ScrollableLayout mScrollLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jianbao_publish_item);
        initView();
        initData();

    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        // String img = intent.getStringExtra("img");
        ArrayList<String> imgs = (ArrayList<String>) bundle.getSerializable("imgs");
        if (imgs != null && !imgs.isEmpty()) {
            for (int i = 0; i < imgs.size(); i++) {
                String photo_one = imgs.get(i);
                String new_photo_one = photo_one.replace("file://", "");
                Bitmap bitmap_new = BitmapFactory.decodeFile(new_photo_one);
                imageViews[i].setImageBitmap(bitmap_new);
            }
        } else {
            Toast.makeText(this, "添加图片效果会更加哟", Toast.LENGTH_SHORT).show();
        }

    }

    //查找控件
    private void initView() {
        publish_imgs = (ImageView) findViewById(R.id.iv_publish_picture); // 存放图片的Imgs
        publish_imgs2 = (ImageView) findViewById(R.id.iv_publish_picture2);
        publish_imgs3 = (ImageView) findViewById(R.id.iv_publish_picture3);
        publish_imgs4 = (ImageView) findViewById(R.id.iv_publish_picture4);
        publish_imgs5 = (ImageView) findViewById(R.id.iv_publish_picture5);
        imageViews[0] = publish_imgs;
        imageViews[1] = publish_imgs2;
        imageViews[2] = publish_imgs3;
        imageViews[3] = publish_imgs4;
        imageViews[4] = publish_imgs5;

        et_jianbao_biaoti = (EditText) findViewById(R.id.et_jiianbao_biaoti); //标题
        et_jianbao_miaoshu = (EditText) findViewById(R.id.et_jianbao_miaoshu); //描述
        EditText et_jianbao_number = (EditText) findViewById(R.id.et_jianbao_number); //手机号
        EditText et_jianbao_price = (EditText) findViewById(R.id.et_jianbao_price);// 价格
        et_jianbao_biaoti.addTextChangedListener(textWatcher);
        et_jianbao_miaoshu.addTextChangedListener(textWatcher);
        initUploading();
    }
    // 上传数据
    private void initUploading() {

    }

    // 判断Edittext最多输入多少字符
    TextWatcher textWatcher = new TextWatcher() {

        private Toast toast;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            //TODO
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            //TODO
        }

        @Override
        public void afterTextChanged(Editable s) {

            Log.d("TAG", "afterTextChanged    " + "str=" + s.toString());
            int len = et_jianbao_biaoti.getText().toString().length();
            int len2 = et_jianbao_miaoshu.getText().toString().length();
            if (len >= 9) {
                Toast.makeText(PublishActivity.this, "亲,最多输入10个字呦", Toast.LENGTH_SHORT).show();
                et_jianbao_biaoti.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)}); //最多输入10的字符
            }
            if (len >= 29) {
                Toast.makeText(PublishActivity.this, "亲,最多输入10个字呦", Toast.LENGTH_SHORT).show();
                et_jianbao_miaoshu.setFilters(new InputFilter[]{new InputFilter.LengthFilter(30)}); //最多输入30的字符
            }
        }
    };
}


