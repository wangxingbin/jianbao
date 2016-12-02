package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wxb.jianbao.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/30.
 */

public class MyPublishedAdapter extends RecyclerView.Adapter <MyPublishedAdapter.MyViewHolder> {
    Context context;
    ArrayList<Object>list;
    private LayoutInflater inflater;
    public OnItemClickListener listener;

    public MyPublishedAdapter(Context context, ArrayList<Object> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_fragment_fabu,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }
    public interface OnItemClickListener{
        void ItemClickListener(View view,int position);
    }
    public void setOnClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        //

        if (listener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 得到当前item的位置
                    int pos = holder.getLayoutPosition();
                    // 把事件交给我们实现的接口那里处理
                    listener.ItemClickListener(holder.itemView, pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        ImageView fabu_picture;
        TextView fabu_tv;
        TextView fabu_money;
        public MyViewHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.item_fabu_linearlayout);
            fabu_picture = (ImageView) itemView.findViewById(R.id.item_fabu_picture);
            fabu_tv = (TextView) itemView.findViewById(R.id.item_fabu_tv);
            fabu_money = (TextView) itemView.findViewById(R.id.item_fabu_money);
        }
    }
}
