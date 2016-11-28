package mainframe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import com.wxb.jianbao.R;

/**
 * Created by 诺古 on 2016/11/26.
 */

public class LoginShow extends Activity {
    private CountDownTimer timer;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_show);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginShow.this, MainFrameAcitvity.class);
                startActivity(intent);
                timer.cancel();
            }
        });
        timer();
    }
    public void timer(){
        timer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
                Intent intent = new Intent(LoginShow.this, MainFrameAcitvity.class);
                startActivity(intent);
                timer.cancel();
                finish();
            }
        };
        timer.start();
    }
}
