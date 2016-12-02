package mainframe;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wxb.jianbao.R;

/**
 * Created by 诺古 on 2016/11/26.
 */

public class Splash extends AppCompatActivity {
    private CountDownTimer timer;
    private Button button;
    private TextView tv_timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        initView();
        initButton();
        createSnackbar(button);
    }

    private void initView() {
        tv_timer = (TextView) findViewById(R.id.tv_timer);
        button = (Button) findViewById(R.id.button);
    }

    //点击跳转
    public void initButton(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Splash.this, MainFrameAcitvity.class);
                startActivity(intent);
                timer.cancel();
            }
        });
        timer();
    }
    //计时跳转
    public void timer(){
        timer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv_timer.setClickable(false);
                tv_timer.setText(millisUntilFinished /1000+"秒");
            }
            public void onFinish() {
                tv_timer.setText("跳转");
                tv_timer.setClickable(true);
                Intent intent = new Intent(Splash.this, MainFrameAcitvity.class);
                startActivity(intent);
                timer.cancel();
                finish();
            }
        };
        timer.start();
    }
    //Snackbar底部弹窗
    public void createSnackbar(View v){
        Snackbar.make(button,"欢迎使用贱宝",Snackbar.LENGTH_SHORT).show();
    }
}