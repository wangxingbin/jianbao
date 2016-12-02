package fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by 赵自强 on 2016/12/1.
 */

public class bukaijia_Fragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//		RelativeLayout rl = new RelativeLayout(getActivity());
//		rl.setBackgroundResource(Color.parseColor("#EA7E1F"));
        TextView tv = new TextView(getActivity());
        tv.setText("我的");
        tv.setTextSize(40);
        tv.setTextColor(Color.parseColor("#24EA1F"));
//		rl.addView(tv, RelativeLayout.CENTER_IN_PARENT);

        return tv;
    }}
