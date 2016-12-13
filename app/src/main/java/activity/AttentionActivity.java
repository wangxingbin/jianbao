package activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wxb.jianbao.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import adapter.MyAttentionAdapter;
import app.Contant;
import javabeen.ShowBean;
import utils.OkhttpUtils;

/**
 * Created by Administrator on 2016/11/30.
 */

public class AttentionActivity extends Activity {
    private TextView barName;
    private ImageView backImage;
    private RecyclerView recyclerview;
    private ImageView iv;
    private ArrayList<ShowBean.DataBean.ListBean> list;
    private Handler mHandler = new Handler();
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention);
        SharedPreferences sp = getSharedPreferences("TOKEN", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        initView();
        initData();
        initBack();
        //测试
    }

    private void initBack() {
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        String curPage = "1";
        String PATH = Contant.GuznZhu;
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("curPage", curPage);

        OkhttpUtils.setGetEntiydata(new OkhttpUtils.EntiyData() {
            @Override
            public void getEntiy(Object o) {
                if (o == null) {
                    Toast.makeText(AttentionActivity.this, "网络异常，请检查您的网络", Toast.LENGTH_SHORT).show();
                }
                if (o != null && o instanceof ShowBean) {
                    //iv.setVisibility(View.GONE);
                    ShowBean showBean = (ShowBean) o;
                    list = (ArrayList) showBean.getData().getList();
                    if (list.isEmpty()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                iv.setImageResource(R.mipmap.shoucang);
                                iv.setVisibility(View.VISIBLE);
                                recyclerview.setVisibility(View.GONE);
                            }
                        });
                    } else {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                initEvent();
                                iv.setVisibility(View.GONE);
                                recyclerview.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                }
            }
        });
        OkhttpUtils.post(map, PATH, this, ShowBean.class);
    }

    private void initEvent() {
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        MyAttentionAdapter adapter = new MyAttentionAdapter(AttentionActivity.this, list);
        recyclerview.setAdapter(adapter);
        adapter.setOnClickListener(new MyAttentionAdapter.OnItemClickListener() {
            @Override
            public void ItemClickListener(View view, int position) {
                /*startActivity(new Intent(getActivity(), SoldActivity.class));*/
                Toast.makeText(AttentionActivity.this, "你点击了" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AttentionActivity.this, SPXQActivity.class);
                intent.putExtra("id",list.get(position).getId()+"");
                startActivity(intent);
            }
        });

    }

    private void initView() {
        barName = (TextView) findViewById(R.id.bar_tv_name);
        backImage = (ImageView) findViewById(R.id.bar_iv_back);
        barName.setText("我收藏的");
        recyclerview = (RecyclerView) findViewById(R.id.fragment_fabu_recyclerview);
        iv = (ImageView) findViewById(R.id.fragment_fabu_iv);
    }
}
