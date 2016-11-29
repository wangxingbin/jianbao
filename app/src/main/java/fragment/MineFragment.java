package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wxb.jianbao.R;
import com.wxb.jianbao.activity.PublishedActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * Created by Administrator on 2016/11/27.
 */

public class MineFragment extends Fragment {
    @InjectView(R.id.mine_iv_photo)
    ImageView mineIvPhoto;
    @InjectView(R.id.mine_tv_name)
    TextView mineTvName;
    @InjectView(R.id.mine_tv_phoneNum)
    TextView mineTvPhoneNum;
    @InjectView(R.id.mine_tv_published)
    TextView mineTvPublished;
    @InjectView(R.id.mine_tv_sold)
    TextView mineTvSold;
    @InjectView(R.id.mine_tv_attention)
    TextView mineTvAttention;
    @InjectView(R.id.mine_tv_invitationCode)
    TextView mineTvInvitationCode;
    @InjectView(R.id.mine_tv)
    Button mineTv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.mine_tv_published, R.id.mine_tv_sold, R.id.mine_tv_attention, R.id.mine_tv_invitationCode, R.id.mine_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mine_tv_published:
                startActivity(new Intent(getActivity(), PublishedActivity.class));
                break;
            case R.id.mine_tv_sold:
                startActivity(new Intent(getActivity(), PublishedActivity.class));
                break;
            case R.id.mine_tv_attention:
                break;
            case R.id.mine_tv_invitationCode:
                break;
            case R.id.mine_tv:
                break;
        }
    }
}
