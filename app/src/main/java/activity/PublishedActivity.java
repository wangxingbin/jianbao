package activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.viewpagerindicator.TabPageIndicator;
import com.wxb.jianbao.R;

import java.util.ArrayList;

import fragment.FragmentPublished;
import fragment.FragmentXiaJia;

/**
 * Created by Administrator on 2016/11/28.
 */

public class PublishedActivity extends FragmentActivity {

    private String[] CONTENT = new String[]{"已发布的", "已下架的"};
    ArrayList<Fragment>list = new ArrayList<>();
    private ImageView barImage;
    private TextView barName;
    private FragmentPublished fp;
    private FragmentXiaJia fx;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_published);

        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        barImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        list.add(fp);
        list.add(fx);
        FragmentPagerAdapter adapter = new GoogleMusicAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
        TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(pager);
    }

    private void initView() {
        fp = new FragmentPublished();
        fx = new FragmentXiaJia();
        barImage = (ImageView) findViewById(R.id.bar_iv_back);
        barName = (TextView) findViewById(R.id.bar_tv_name);
        barName.setText("我发布的");
    }

    class GoogleMusicAdapter extends FragmentPagerAdapter {
        public GoogleMusicAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position%CONTENT.length);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length].toUpperCase();
        }

        @Override
        public int getCount() {
            return CONTENT.length;
        }
    }
}
