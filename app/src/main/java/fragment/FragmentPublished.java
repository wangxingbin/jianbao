package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wxb.jianbao.R;

import java.util.ArrayList;

import adapter.MyPublishedAdapter;

/**
 * Created by Administrator on 2016/11/29.
 */

public class FragmentPublished extends Fragment {

    private RecyclerView recyclerview;
    private View view;
    private LinearLayout ll;
    private ArrayList<Object> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fabu,container,false);
        initData();
        initView();
        initEvent();

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
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            list.add(""+i);
        }

    }

    private void initView() {
        recyclerview = (RecyclerView) view.findViewById(R.id.fragment_fabu_recyclerview);
        ll = (LinearLayout) view.findViewById(R.id.linearlayout);


    }
}