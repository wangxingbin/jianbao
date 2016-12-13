package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wxb.jianbao.R;

import java.util.ArrayList;

import app.Contant;
import javabeen.CheckPublished;

/*
 * Created by Administrator on 2016/11/30.
 */

public class MyPublishedAdapter extends RecyclerView.Adapter <MyPublishedAdapter.MyViewHolder> {
    Context context;
    ArrayList<CheckPublished.DataBean.ListBean>list;
    private LayoutInflater inflater;
    public OnItemClickListener listener;
    private MyViewHolder holder;

    public MyPublishedAdapter(Context context, ArrayList<CheckPublished.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_fragment_fabu,parent,false);
        holder = new MyViewHolder(view);
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
        // 图片
        String image = Contant.IMGQZ+list.get(position).getImage();
//        // 转换时间
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        long issue_time = list1.get(position).getIssue_time();
//        String str=sdf.format(issue_time);

        // 讲获取到的数据放到相应的控件上
        holder.fabu_money.setText("¥ "+list.get(position).getPrice());
        holder.fabu_tv.setText(list.get(position).getTitle());
        holder.fabu_time.setText(list.get(position).getIssue_time());
        holder.fabu_picture.setImageURI(image);

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
        SimpleDraweeView fabu_picture;
        TextView fabu_tv,fabu_money,fabu_time;

        public MyViewHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.item_fabu_linearlayout);
            fabu_picture = (SimpleDraweeView) itemView.findViewById(R.id.item_fabu_picture);
            fabu_tv = (TextView) itemView.findViewById(R.id.item_fabu_tv);
            fabu_money = (TextView) itemView.findViewById(R.id.item_fabu_money);
            fabu_time = (TextView) itemView.findViewById(R.id.item_fabu_time);
        }
    }
}
