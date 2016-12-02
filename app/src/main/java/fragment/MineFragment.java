package fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wxb.jianbao.R;

import java.io.File;

import activity.AttentionActivity;
import activity.PublishedActivity;
import activity.SettingsActivity;
import activity.SoldActivity;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import utils.TakePhotoPopWin;


/**
 * Created by Administrator on 2016/11/27.
 */

public class MineFragment extends Fragment {
    @InjectView(R.id.mine_iv_photo)
    SimpleDraweeView mineIvPhoto;
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
    @InjectView(R.id.mine_bt)
    Button mineTv;
    @InjectView(R.id.mine_ll_settings)
    LinearLayout mineLlSettings;
    private Dialog dialog;
    private View inflate;
    private TextView choosePhoto;
    private TextView takePhoto;
    private Button cancel;
    private File imageFile;

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

    @OnClick({R.id.mine_iv_photo, R.id.mine_tv_published, R.id.mine_tv_sold, R.id.mine_tv_attention, R.id.mine_tv_invitationCode, R.id.mine_bt, R.id.mine_ll_settings})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mine_tv_published:
                startActivity(new Intent(getActivity(), PublishedActivity.class));
                break;
            case R.id.mine_tv_sold:
                startActivity(new Intent(getActivity(), SoldActivity.class));
                break;
            case R.id.mine_tv_attention:
                startActivity(new Intent(getActivity(), AttentionActivity.class));
                break;
            case R.id.mine_tv_invitationCode:
                break;
            case R.id.mine_bt:
                break;
            case R.id.mine_ll_settings:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                break;
            case R.id.mine_iv_photo:
                showPop(view);
                break;
        }
    }

    private void showPop(View v) {
        TakePhotoPopWin photoPopWin = new TakePhotoPopWin(getActivity(), onClickListener);
        photoPopWin.showAtLocation(mineIvPhoto, Gravity.CENTER, 0, 0);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.btn_pick_photo:

                            break;
                        case R.id.btn_take_photo:
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, 1);
                            break;
                    }

        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}