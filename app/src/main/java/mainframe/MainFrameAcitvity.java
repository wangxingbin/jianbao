package mainframe;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wxb.jianbao.R;

import fragment.AddFragment;
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
    private AddFragment add;//发布
    /**
     * 具体内容
     */
    private android.support.v4.app.Fragment contentFragment;
    /**
     * 管理fragment
     */
    private FragmentManager fragmentManager;
    private TextView shouye_tv;
    private TextView mine_tv;
    private TextView add_tv;
    private ImageView shouye;
    private ImageView tianjia;
    private ImageView geren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_frame);
        initView();
    }

    private void initView() {
        shouye = (ImageView) findViewById(R.id.iv_shouye);
        tianjia = (ImageView) findViewById(R.id.iv_tianjia);
        geren = (ImageView) findViewById(R.id.iv_geren);

        shouye_tv = (TextView) findViewById(R.id.tv_zhuye);
        add_tv = (TextView) findViewById(R.id.tv_add);
        mine_tv = (TextView) findViewById(R.id.tv_mine);

        shouye.setOnClickListener(itemClick);
        tianjia.setOnClickListener(itemClick);
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
                case R.id.iv_tianjia:
                    initAdd();
                    contentFragment = new AddFragment();
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
        tianjia.setBackgroundResource(R.mipmap.add);
        add_tv.setTextColor(Color.parseColor("#a9b7b7"));
        geren.setBackgroundResource(R.mipmap.mine);
        mine_tv.setTextColor(Color.parseColor("#a9b7b7"));
    }

    private void initAdd() {
        shouye.setBackgroundResource(R.mipmap.home);
        shouye_tv.setTextColor(Color.parseColor("#a9b7b7"));
        tianjia.setBackgroundResource(R.mipmap.add2);
        add_tv.setTextColor(Color.parseColor("#000000"));
        geren.setBackgroundResource(R.mipmap.mine);
        mine_tv.setTextColor(Color.parseColor("#a9b7b7"));
    }

    private void initMine() {
        shouye.setBackgroundResource(R.mipmap.home);
        shouye_tv.setTextColor(Color.parseColor("#a9b7b7"));
        tianjia.setBackgroundResource(R.mipmap.add);
        add_tv.setTextColor(Color.parseColor("#a9b7b7"));
        geren.setBackgroundResource(R.mipmap.mine2);
        mine_tv.setTextColor(Color.parseColor("#000000"));
    }

}
