package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wxb.jianbao.R;

/**
 * Created by Administrator on 2016/11/29.
 */

public class FragmentXiaJia extends Fragment {

    private View view;
    private TextView barName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_xiajia,container,false);
        return view;
    }
}
