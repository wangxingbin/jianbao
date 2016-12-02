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
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

    RadioGroup rg;
    RadioButton add_tv;
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
        initEvent();
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



    private void initEvent() {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction= fragmentManager.beginTransaction();
                switch (checkedId) {
                    case R.id.tv_zhuye:
                        initShouye();
                        contentFragment = new ShowFragment();
                        transaction.replace(R.id.main_frame_layout, contentFragment);
                        break;
                    case R.id.tv_mine:
                        initMine();
                        contentFragment = new MineFragment();
                        transaction.replace(R.id.main_frame_layout, contentFragment);
                        break;
                    default:
                        break;
                }
                transaction.commit();
            }
        });
    }

    private void initView() {
        rg = (RadioGroup) findViewById(R.id.id_rg);
        shouye_tv = (RadioButton) findViewById(R.id.tv_zhuye);
        mine_tv = (RadioButton) findViewById(R.id.tv_mine);
        add_tv = (RadioButton) findViewById(R.id.tv_add);
        fragmentManager = getSupportFragmentManager();
    }

    private void initShouye() {
        shouye_tv.setTextColor(Color.parseColor("#000000"));
        mine_tv.setTextColor(Color.parseColor("#a9b7b7"));
    }

    private void initMine() {
        shouye_tv.setTextColor(Color.parseColor("#a9b7b7"));
        mine_tv.setTextColor(Color.parseColor("#000000"));
    }

}
