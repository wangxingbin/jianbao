package activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wxb.jianbao.R;

/**
 * Created by Administrator on 2016/11/30.
 */

public class SoldActivity extends Activity {

    private TextView barName;
    private ImageView backImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sold);
        initView();
        initEvent();
    }

    private void initEvent() {
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        barName = (TextView) findViewById(R.id.bar_tv_name);
        backImage = (ImageView) findViewById(R.id.bar_iv_back);
        barName.setText("我卖出的");
    }
}
