package utils;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ti on 2016/12/1.
 */

public class MyOkhttp {

    private volatile static MyOkhttp myOkhttp;

    private Gson gson;

    private MyOkhttp(){
        super();
        gson=new Gson();
    }

    public static MyOkhttp getInstance(){
        if(myOkhttp==null){
            synchronized (MyOkhttp.class){
                if(myOkhttp==null){
                    myOkhttp=new MyOkhttp();
                }
            }
        }
        return myOkhttp;
    }


    public void doRequest(String path, RequestType requestType,Map<String,String> map,
                          final MyCallBack mycallback,Type type){

            Request.Builder builder = new Request.Builder();
            builder.url(path);
            if (requestType==RequestType.GET){
                builder.get();
            }else if (requestType==RequestType.POST)
            {
                FormBody.Builder fBuilder = new FormBody.Builder();
                Set<Map.Entry<String, String>> entries = map.entrySet();
                for (Map.Entry<String, String> e:entries){
                    fBuilder.add(e.getKey(),e.getValue());
                }
                FormBody fb = fBuilder.build();
                builder.post(fb);
            }
            Request request = builder.build();

            executeCall(request,mycallback,type);


    }


    private void executeCall(Request request, final MyCallBack mycallback, final Type type) {
        OkHttpClient okHttpClient=new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        mycallback.loading();
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mycallback.onFailure();
            }
            //200-300
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String string = response.body().string();
                    if (type==null)
                    {
                        mycallback.onSuccess(string);
                    }
                    else
                    {
                        Object o = gson.fromJson(string, type);
                        mycallback.onSuccess(o);
                    }
                    //success
                }
                else
                {
                    //error
                    mycallback.onError();
                    Log.e("错误的code",""+response.code());

                }

            }
        });

    }

    public  enum RequestType{
        GET,
        POST
    }

}
