package adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wxb.jianbao.R;

import java.util.ArrayList;

import javabeen.LBZSbean;


/**
 * Created by ti on 2016/11/27.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public Context context;
    public LayoutInflater inflater;
    public ArrayList<LBZSbean.DataBean.ListBean> list;
    private View v;
    private MyViewHolder myViewHolder;


    public MyAdapter(Context context, ArrayList<LBZSbean.DataBean.ListBean> list){

        this.context=context;
        this.list=list;

        inflater=LayoutInflater.from(context);

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        v = inflater.inflate(R.layout.rv_item,parent,false);
        myViewHolder = new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        LBZSbean.DataBean.ListBean lb=list.get(position);


        Uri uri = Uri.parse("http://192.168.4.188/Goods/uploads/"+lb.getImage());

        holder.tv_biaoti.setText(lb.getTitle());
        holder.id_sdv.setImageURI(uri);
        holder.tv_jiaqian.setText(lb.getPrice()+"");

        int state=lb.getState();
        if(state==0){
            holder.iv_yixiajia.setVisibility(View.GONE);
        }
        else if (state==1||state==2){
            holder.iv_yixiajia.setVisibility(View.VISIBLE);

        }

        if(mListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos=holder.getLayoutPosition();//得到当前点击item的位置pos
                    mListener.ItemClickListener(v,pos);

                }
            });
        }



    }

    @Override
    public int getItemCount() {
//        Log.e("hskdlfjskdl",""+list.size());
        return list.size();
    }

    public OnItemClickListener mListener;

    public interface OnItemClickListener {
        void ItemClickListener(View view, int postion);

    }

    public void setOnClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_yixiajia;
        SimpleDraweeView id_sdv;
        TextView tv_biaoti,tv_jiaqian;

        public MyViewHolder(View v) {
            super(v);

            id_sdv= (SimpleDraweeView) v.findViewById(R.id.id_sdv);
            tv_biaoti= (TextView) v.findViewById(R.id.tv_biaoti);
            tv_jiaqian= (TextView) v.findViewById(R.id.tv_jiaqian);
            iv_yixiajia= (ImageView) v.findViewById(R.id.iv_yixiajia);

        }
    }




}
