package activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wxb.jianbao.R;

/**
 * Created by Administrator on 2016/11/30.
 */

public class AttentionActivity extends Base_Activity {
    private TextView barName;
    private ImageView backImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention);
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
        barName.setText("我收藏的");
    }
}
