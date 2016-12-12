package fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.wxb.jianbao.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import adapter.MyPublishedAdapter;
import app.Contant;
import javabeen.CheckPublished;
import utils.OkhttpUtils;

/**
 * Created by Administrator on 2016/11/29.
 */

public class FragmentPublished extends Fragment {

    private RecyclerView recyclerview;
    private View view;
    private Handler mHandler=new Handler();
    private ArrayList<CheckPublished.DataBean.ListBean> list;
    private ImageView iv;
    private String token = "8B169BF5768049F0BB20B1680042FBF7";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fabu,container,false);
        /*SharedPreferences sp = getActivity().getSharedPreferences("TOKEN", Context.MODE_PRIVATE);
        token = sp.getString("token", "");*/
        initView();
        initData();
        //测试
        return view;
    }

    private void initEvent() {
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        MyPublishedAdapter adapter = new MyPublishedAdapter(getActivity(),list);
        recyclerview.setAdapter(adapter);
        adapter.setOnClickListener(new MyPublishedAdapter.OnItemClickListener() {
            @Override
            public void ItemClickListener(View view, int position) {
                /*startActivity(new Intent(getActivity(), SoldActivity.class));*/
                Toast.makeText(getActivity(), "你点击了"+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        String curPage="1";
        String PATH = Contant.CHECKPUBLISHED;
        Map<String,String>map = new HashMap<>();
        map.put("token",token);
        map.put("curPage",curPage);

        OkhttpUtils.setGetEntiydata(new OkhttpUtils.EntiyData() {
            @Override
            public void getEntiy(Object o) {
                if (o==null){
                    Toast.makeText(getActivity(), "网络异常，请检查您的网络", Toast.LENGTH_SHORT).show();
                }
                if (o!=null&&o instanceof CheckPublished){
                    //iv.setVisibility(View.GONE);
                    CheckPublished checkPublished = (CheckPublished)o;
                    list =(ArrayList)checkPublished.getData().getList();
                    if (list.isEmpty()){
                       getActivity().runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               iv.setVisibility(View.VISIBLE);
                               recyclerview.setVisibility(View.GONE);
                           }
                       });
                    }else {
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
        OkhttpUtils.post(map,PATH,getActivity(),CheckPublished.class);
    }

    private void initView() {
        recyclerview = (RecyclerView) view.findViewById(R.id.fragment_fabu_recyclerview);
        iv = (ImageView) view.findViewById(R.id.fragment_fabu_iv);
    }
}