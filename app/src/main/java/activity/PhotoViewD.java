package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.wxb.jianbao.R;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by ti on 2016/11/30.
 */

public class PhotoViewD extends Activity {

    private PhotoView iv_photo2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);

        Intent intent=getIntent();
        String photo=intent.getStringExtra("photo");

        iv_photo2 = (PhotoView) findViewById(R.id.iv_photo2);
        Picasso.with(this).load("http://192.168.4.188/Goods/uploads/"+ photo).into(iv_photo2);


        iv_photo2.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                finish();
            }

            @Override
            public void onOutsidePhotoTap() {

                finish();

            }
        });

    }
}
