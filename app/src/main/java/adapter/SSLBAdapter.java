package adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wxb.jianbao.R;

import java.util.ArrayList;

import javabeen.LBZSbean;


/**
 * Created by ti on 2016/12/6.
 */

public class SSLBAdapter extends RecyclerView.Adapter<SSLBAdapter.MyViewHolder> {
    Context context;
    ArrayList<LBZSbean.DataBean.ListBean> list;
    LayoutInflater inflater;

    public SSLBAdapter(Context context, ArrayList<LBZSbean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.sslb_rv_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        LBZSbean.DataBean.ListBean lb=list.get(position);


        Uri uri = Uri.parse("http://192.168.4.188/Goods/uploads/"+lb.getImage());

        holder.id_title.setText(lb.getTitle());
        holder.id_sdv.setImageURI(uri);
        holder.id_price.setText(lb.getPrice()+"");
        holder.id_time.setText(lb.getIssue_time());



        if(mListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos=holder.getLayoutPosition();//得到当前点击item的位置pos
                    mListener.ItemClickListener(view,pos);

                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView id_sdv;
        private final TextView id_price,id_time,id_title;

        public MyViewHolder(View itemView) {
            super(itemView);
            id_sdv = (SimpleDraweeView) itemView.findViewById(R.id.id_sdv);
            id_price = (TextView) itemView.findViewById(R.id.id_price);
            id_time = (TextView) itemView.findViewById(R.id.id_time);
            id_title = (TextView) itemView.findViewById(R.id.id_title);
        }
    }


    public MyAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void ItemClickListener(View view, int postion);

    }

    public void setOnClickListener(MyAdapter.OnItemClickListener listener) {
        this.mListener = listener;
    }

}
