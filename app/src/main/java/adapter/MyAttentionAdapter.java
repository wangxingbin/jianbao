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

import contants.Contants;
import javabeen.ShowBean;

/**
 * Created by Administrator on 2016/12/8.
 */

public class MyAttentionAdapter extends RecyclerView.Adapter <MyAttentionAdapter.MyViewHolder>{
    Context context;
    ArrayList<ShowBean.DataBean.ListBean> list;
    private LayoutInflater inflater;
    public MyAttentionAdapter.OnItemClickListener listener;
    private MyAttentionAdapter.MyViewHolder holder;

    public MyAttentionAdapter(Context context, ArrayList<ShowBean.DataBean.ListBean> list) {
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
    public void setOnClickListener(MyAttentionAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(final MyAttentionAdapter.MyViewHolder holder, int position) {
        // 图片
        String image = Contants.IMGQZ+list.get(position).getImage();

        // 讲获取到的数据放到相应的控件上
        holder.fabu_money.setText(list.get(position).getPrice());
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
        private final View view;

        public MyViewHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.item_fabu_linearlayout);
            fabu_picture = (SimpleDraweeView) itemView.findViewById(R.id.item_fabu_picture);
            fabu_tv = (TextView) itemView.findViewById(R.id.item_fabu_tv);
            fabu_money = (TextView) itemView.findViewById(R.id.item_fabu_money);
            fabu_time = (TextView) itemView.findViewById(R.id.item_fabu_time);
            view = itemView.findViewById(R.id.view);
            view.setBackgroundColor(context.getResources().getColor(R.color.hh));
        }
    }
}
