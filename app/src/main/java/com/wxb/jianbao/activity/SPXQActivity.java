package com.wxb.jianbao.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.reflect.TypeToken;
import com.wxb.jianbao.R;
import com.wxb.jianbao.adapter.DGSPTPAdapter;
import com.wxb.jianbao.adapter.MyAdapter;
import com.wxb.jianbao.adapter.MyVPAdapter;
import com.wxb.jianbao.app.Constant;
import com.wxb.jianbao.bean.GuanZhu;
import com.wxb.jianbao.bean.SPXQ;

import java.lang.reflect.Type;
import java.util.ArrayList;

import utils.MyCallBack;
import utils.MyOkhttp;
import view.FullyLinearLayoutManager;


/**
 * Created by ti on 2016/11/28.
 */

public class SPXQActivity extends Activity {

    private ViewPager vp;
    private ImageView iv;
    private ImageView back;
    private LinearLayout ll_qiu;
    private LinearLayout ll_dingbu;
    private ScrollView scrollView;
    private int vpheight;
    private ViewGroup.LayoutParams vlp;
    private ImageView wpgzFollow;

    private boolean SCFLAG = true;

    private String TOKEN = "05CC46BD79194838B98A35F258780B7B";
    private String id;
    private TextView wpfbTime;
    private TextView wpPrice;
    private TextView wpTitle;
    private TextView wpDescription;
    private TextView wpMobile;
    private TextView wpQQ;
    private TextView wpWechat;
    private TextView wpEmail;
    private SimpleDraweeView draweeView;
    private ArrayList<ImageView> dian;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;
    private boolean aBoolean;
    private int act;
    private RecyclerView dgsptp_rv;
    private MyOkhttp myOkhttp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spxiangqing);
        initView();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        Log.e("ididiid", id);

        initData();

        sharedPreferences = getSharedPreferences("guanzhu" + id, MODE_PRIVATE);
        edit = sharedPreferences.edit();

        String stract = sharedPreferences.getString("act", "");
        String strid = sharedPreferences.getString("id", "");

        if (stract.equals("1") && strid.equals(id)) {
            wpgzFollow.setBackgroundResource(R.mipmap.scm);

        } else if (stract.equals("0") && strid.equals(id)) {
            wpgzFollow.setBackgroundResource(R.mipmap.scd);

        }


        initEvent();


    }


    private void initEvent() {

        //vp的选择事件
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i("Glasses", "1111111");
                setImageBackground(position % asimple.size());

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //设置viewPager的起始位置
        vp.setCurrentItem(0);

        //返回按钮
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //收藏按钮
        wpgzFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SCFLAG) {
                    act = 0;
                    wpgzFollow.setBackgroundResource(R.mipmap.scd);
//                    soucang(act);
//                    edit.putString("act", "" + act);
//                    edit.putString("id", "" + id);
//                    edit.commit();
                    SCFLAG = false;

                } else {
                    act = 1;
                    wpgzFollow.setBackgroundResource(R.mipmap.scm);
//                    soucang(act);
//                    edit.putString("act", "" + act);
//                    edit.putString("id", "" + id);
//                    edit.commit();
                    SCFLAG = true;
                }

                soucang(act);
                edit.putString("act", "" + act);
                edit.putString("id", "" + id);
                edit.commit();


            }
        });

    }

    private void soucang(final int aa) {

        myOkhttp = MyOkhttp.getInstance();
        Log.e("商品详情网址", Constant.GUANZHU + "?id=" + id + "&act=" + aa + "&token=" + TOKEN);
        Type type = new TypeToken<GuanZhu>() {
        }.getType();
        myOkhttp.doRequest(Constant.GUANZHU + "?id=" + id + "&token=" + TOKEN,
                MyOkhttp.RequestType.GET,
                null,
                new MyCallBack() {
                    @Override
                    public void loading() {

                    }

                    @Override
                    public void onFailure() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(SPXQActivity.this,"Failure", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                    @Override
                    public void onSuccess(Object o) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(SPXQActivity.this,"Success", Toast.LENGTH_SHORT).show();

                            }
                        });


                    }

                    @Override
                    public void onError() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(SPXQActivity.this,"Error", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                },
                type);

    }

    private void initData() {

        myOkhttp = MyOkhttp.getInstance();
        Log.e("商品详情网址", Constant.XIANGQING + "?id=" + id + "&token=" + TOKEN);
        Type type = new TypeToken<SPXQ>() {
        }.getType();
        myOkhttp.doRequest(Constant.XIANGQING + "?id=" + id + "&token=" + TOKEN,
                MyOkhttp.RequestType.GET,
                null,
                new MyCallBack11(),
                type);


    }


    private void initView() {

        scrollView = (ScrollView) findViewById(R.id.scrollview);
        ll_dingbu = (LinearLayout) findViewById(R.id.ll_dingbu);

        ll_qiu = (LinearLayout) findViewById(R.id.ll_qiu);
        vp = (ViewPager) findViewById(R.id.vp);
        wpfbTime = (TextView) findViewById(R.id.wuping_time);
        wpPrice = (TextView) findViewById(R.id.wuping_jiaqian);
        back = (ImageView) findViewById(R.id.iv_back);
        wpgzFollow = (ImageView) findViewById(R.id.wuping_shoucang);
        wpTitle = (TextView) findViewById(R.id.wuping_title);
        wpDescription = (TextView) findViewById(R.id.wuping_xiangqing);
        wpMobile = (TextView) findViewById(R.id.wuping_phone);
        wpQQ = (TextView) findViewById(R.id.wuping_qq);
        wpWechat = (TextView) findViewById(R.id.wuping_weixin);
        wpEmail = (TextView) findViewById(R.id.wuping_email);

        dgsptp_rv = (RecyclerView) findViewById(R.id.dgsptp_rv);
        FullyLinearLayoutManager manager = new FullyLinearLayoutManager(SPXQActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        manager.setSmoothScrollbarEnabled(true);
        dgsptp_rv.setLayoutManager(manager);


    }

    public class MyCallBack11 extends MyCallBack {


        public void loading() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    if (!progressDialog.isShowing()) {
//                          progressDialog.show();
//                    }
                }
            });
        }

        @Override
        public void onFailure() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    progressDialog.dismiss();
                    Toast.makeText(SPXQActivity.this, "network failure", Toast.LENGTH_SHORT).show();

                }
            });
        }

        @Override
        public void onSuccess(Object o) {

            displayData((SPXQ) o);

        }

        @Override
        public void onError() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(SPXQActivity.this, "request params error", Toast.LENGTH_SHORT).show();
//                   progressDialog.dismiss();

                }
            });
        }


    }

    private void displayData(SPXQ o) {
        final SPXQ sp = o;
        Log.e("", sp.toString());
        Log.e("", sp.getData().toString());

        final String title = sp.getData().getTitle();
        final String time = sp.getData().getIssue_time();
        final String description = sp.getData().getDescription();
        final String price = sp.getData().getPrice();
        final String mobile = sp.getData().getMobile();
        final String qq = sp.getData().getQq();
        final String email = sp.getData().getEmail();
        final String wecat = sp.getData().getWechat();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                wpfbTime.setText("发布时间" + time);
                wpTitle.setText(title);
                wpDescription.setText(description);
                wpPrice.setText(price);

                if (TextUtils.isEmpty(mobile)) {
                    wpMobile.setText("");
                    wpMobile.setVisibility(View.GONE);
                } else {
                    wpMobile.setVisibility(View.VISIBLE);
                    wpMobile.setText("联系电话：" + sp.getData().getMobile());
                }

                if (TextUtils.isEmpty(qq)) {
                    wpQQ.setText("");
                    wpQQ.setVisibility(View.GONE);
                } else {
                    wpQQ.setVisibility(View.VISIBLE);
                    wpQQ.setText("Q Q：" + sp.getData().getMobile());
                }
                if (TextUtils.isEmpty(email)) {
                    wpEmail.setText("");
                    wpEmail.setVisibility(View.GONE);
                } else {
                    wpEmail.setVisibility(View.GONE);
                    wpEmail.setText("Email：" + sp.getData().getMobile());
                }
                if (TextUtils.isEmpty(wecat)) {
                    wpWechat.setText("");
                    wpWechat.setVisibility(View.GONE);
                } else {
                    wpWechat.setVisibility(View.VISIBLE);
                    wpWechat.setText("微 信：" + sp.getData().getMobile());
                }

                tianjiatupian(sp);
                lunbo(sp);


            }
        });


    }

    private void tianjiatupian(SPXQ sp) {

        final ArrayList<String> alist = (ArrayList<String>) sp.getData().getPhotos();

        if (alist.size() > 0) {

            DGSPTPAdapter dpAdapter = new DGSPTPAdapter(alist, SPXQActivity.this);
            dgsptp_rv.setAdapter(dpAdapter);
            dpAdapter.setOnClickListener(new MyAdapter.OnItemClickListener() {
                @Override
                public void ItemClickListener(View view, int postion) {

//                    Intent intent = new Intent(SPXQActivity.this, PhotoViewD.class);
//                    intent.putExtra("photo", alist.get(postion));
//                    startActivity(intent);


                }
            });

        } else {
            dgsptp_rv.setVisibility(View.GONE);

        }

    }

    //存放图片的集合
    ArrayList<SimpleDraweeView> asimple = new ArrayList<SimpleDraweeView>();

    private void lunbo(SPXQ sp) {

        ArrayList<String> arrayList = (ArrayList<String>) sp.getData().getPhotos();
        Log.e("哈哈哈哈哈哈哈", "" + arrayList.size());


        if (arrayList.size() > 0) {

            for (int i = 0; i < arrayList.size(); i++) {
                draweeView = new SimpleDraweeView(SPXQActivity.this);
                Uri uri = Uri.parse("http://192.168.4.188/Goods/uploads/" + arrayList.get(i));
                draweeView.setImageURI(uri);

                asimple.add(draweeView);

            }

            // 将白点加入到LinearLayout中
            int ss = arrayList.size();
            dian = new ArrayList<ImageView>();
            for (int i = 0; i < arrayList.size(); i++) {
                ImageView dianiv = new ImageView(this);
//                tips[i] = dianiv;
                dian.add(dianiv);

                if (i == 0) {
                    dian.get(i).setBackgroundResource(R.mipmap.ydred);
                } else {
                    dian.get(i).setBackgroundResource(R.mipmap.ydb);
                }

                ll_qiu.addView(dianiv);

            }

            vp.setAdapter(new MyVPAdapter(asimple, SPXQActivity.this));

        } else {
            ArrayList<SimpleDraweeView> list=new ArrayList<SimpleDraweeView>();

            SimpleDraweeView d= new SimpleDraweeView(SPXQActivity.this);
            Uri uri = Uri.parse("http://img3.imgtn.bdimg.com/it/u=4140767364,4012792603&fm=21&gp=0.jpg");
            d.setImageURI(uri);
            list.add(d);
            vp.setAdapter(new MyVPAdapter(list, SPXQActivity.this));

        }

    }

    /**
     * 设置选中的白点的背景变色
     *
     * @param selectItems
     */

    private void setImageBackground(int selectItems) {

        for (int i = 0; i < dian.size(); i++) {

            if (i == selectItems) {

                dian.get(i).setBackgroundResource(R.mipmap.ydred);

            } else {

                dian.get(i).setBackgroundResource(R.mipmap.ydb);

            }

        }

    }


}




























































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































