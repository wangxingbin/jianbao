package utils;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

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
 * Created by Administrator on 2016/12/6.
 */

public class OkhttpUtils {
    private static final String TAG = "OkhttpUtils";

    private static Gson gson = new Gson();//创建gson对象
    private static String string;
    private static Call call;
    private static Handler mHandler=new Handler();

    public interface EntiyData {//创建接口

        void getEntiy(Object o);
    }

    private static EntiyData data;

    public static void setGetEntiydata(EntiyData data1) {
        data = data1;
    }

    //网络post请求
    //Type是所有类的类型包括Javabeen
    public static void post(Map<String, String> map, String path, final Context context, final Type cla) {
        OkHttpClient client = new OkHttpClient(); //okhttp对象
        FormBody.Builder builder = new FormBody.Builder();
        Set<Map.Entry<String, String>> entries = map.entrySet();//用entryset的方法遍历map集合
        for (Map.Entry<String, String> entry : entries) {
            builder.add(entry.getKey(), entry.getValue());
        }
        FormBody body = builder.build();
        Request request = new Request.Builder()
                .url(path)
                .post(body)
                .build();
        call = client.newCall(request);
        //用这种方法不用创建子线程
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Response execute = call.execute();
//                    String string = execute.body().string();
//                    Object o = gson.fromJson(string, cla);
////                    //回调结果
//                    data.getEntiy(o);
//                    Log.e(TAG, "run: "+string );
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }).start();



         call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                System.out.println("下载数据失败");
                /*mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "网络异常，请检查您的网络！", Toast.LENGTH_SHORT).show();
                    }
                });
                data.getEntiy(null);*/
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    string = response.body().string();
                    Log.i(TAG, "获取的数据：" + string);

                    Object o = gson.fromJson(string, cla);
                    //回调结果
                    data.getEntiy(o);
                } else {
                    Log.e(TAG, "失败");
                }
            }
        });
    }

    //网络get请求
    public static void get(final Context context, String url, final Type clas) {
        OkHttpClient client2 = new OkHttpClient();
        final Request request1 = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call1 = client2.newCall(request1);
        call1.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(context, "获取数据失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String string1 = response.body().string();
                    Object o1 = gson.fromJson(string1, clas);
                    data.getEntiy(o1);
                }
            }
        });
    }
}
