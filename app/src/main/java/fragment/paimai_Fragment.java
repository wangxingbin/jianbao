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

public class paimai_Fragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        TextView tv = new TextView(getActivity());
        tv.setText("首页");
        tv.setTextSize(40);
        tv.setTextColor(Color.parseColor("#EA1F34"));

        return tv;
    }
}
