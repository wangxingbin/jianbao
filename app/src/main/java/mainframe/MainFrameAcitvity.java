package mainframe;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wxb.jianbao.R;
import com.wxb.jianbao.TakePhotoActivity;

import custom.CustomDialogDemo;
import fragment.MineFragment;
import fragment.ShowFragment;

/**
 * Created by 诺古 on 2016/11/26.
 */
/*
整个程序最底层的框架Activity
 */
public class MainFrameAcitvity extends FragmentActivity {


    private MineFragment mine;//个人主页
    private android.support.v4.app.Fragment contentFragment;
    private FragmentManager fragmentManager;
    private TextView shouye_tv;
    private TextView mine_tv;
    private ImageView shouye;
    private ImageView geren;
    private ImageView dialog_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_frame);
        /*View inflate = LayoutInflater.from(this).inflate(R.layout.main_dialog, null);
        iv_image = (ImageView) inflate.findViewById(R.id.mian_imgs);*/
        initView();
        initDialog();
    }

    // 显示对话框
    private void initDialog() {

        dialog_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });
    }

    private void initData() {
        CustomDialogDemo.Builder builder = new CustomDialogDemo.Builder(this);


        builder.setMessage("我将是图片一");
        builder.setTitle("贱宝:鉴宝");
        builder.setPositiveButton("拍出你的美", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
               Intent intent = new Intent(MainFrameAcitvity.this, TakePhotoActivity.class);
                startActivity(intent);
                dialog.dismiss();
                //设置你的操作事项
            }
        });

        builder.setNegativeButton("淘宝一键转卖",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });



        builder.create().show();
    }

    // 获取相机相册需要从写这个方法



    private void initView() {
        shouye = (ImageView) findViewById(R.id.iv_shouye);
        geren = (ImageView) findViewById(R.id.iv_geren);
        dialog_img = (ImageView) findViewById(R.id.iv_tianjia);
        shouye_tv = (TextView) findViewById(R.id.tv_zhuye);
        mine_tv = (TextView) findViewById(R.id.tv_mine);
        shouye.setOnClickListener(itemClick);
        geren.setOnClickListener(itemClick);
        fragmentManager = getSupportFragmentManager();
    }

    private View.OnClickListener itemClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //管理者
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (view.getId()) {
                case R.id.iv_shouye:
                    initShouye();
                    contentFragment = new ShowFragment();
                    transaction.replace(R.id.main_frame_layout, contentFragment);
                    break;

                case R.id.iv_geren:
                    initMine();
                    contentFragment = new MineFragment();
                    transaction.replace(R.id.main_frame_layout, contentFragment);
                    break;
                default:
                    break;
            }
            transaction.commit();
        }
    };

    private void initShouye() {
        shouye.setBackgroundResource(R.mipmap.home2);
        shouye_tv.setTextColor(Color.parseColor("#000000"));
        geren.setBackgroundResource(R.mipmap.mine);
        mine_tv.setTextColor(Color.parseColor("#a9b7b7"));
    }

    private void initMine() {
        shouye.setBackgroundResource(R.mipmap.home);
        shouye_tv.setTextColor(Color.parseColor("#a9b7b7"));
        geren.setBackgroundResource(R.mipmap.mine2);
        mine_tv.setTextColor(Color.parseColor("#000000"));
    }

}
