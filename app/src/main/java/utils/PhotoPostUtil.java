package utils;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import view.ShowToastUtils;

import static android.content.ContentValues.TAG;


/**
 * Created by 孙贝贝 on 2016/12/1.
 */

public class PhotoPostUtil {
    public static   GetRegisterData data;
    public interface GetRegisterData{
        void setRegisterData(Object o);
    }
    public static  void  getData(GetRegisterData data1){
         data= data1;
    }
    private static  final Gson gson= new Gson();
    private static  final OkHttpClient client= new OkHttpClient();
    private static MultipartBody.Builder requestBody;

    public static void upLoad(File file, final Context context, String  path, HashMap<String ,String>map, final Type clas){
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpg"), file);
        requestBody = new MultipartBody.Builder();
        requestBody.setType(MultipartBody.FORM);
        requestBody.addFormDataPart("card",file.getName(),fileBody);

        Set<Map.Entry<String ,String>>entries= map.entrySet();
        for (Map.Entry<String,String>entry:entries){
            requestBody.addFormDataPart(entry.getKey(),entry.getValue());
        }
        MultipartBody build = requestBody.build();
        Request request = new Request.Builder()
                .url(path)
                .post(build)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ShowToastUtils.showToast(context,"上传数据失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()){
                        String string = response.body().string();
                        Object o = gson.fromJson(string, clas);
                        data.setRegisterData(o);

                    }
                else
                    {
                        Log.e(TAG, "onResponse: "+response.code());
                    }
            }
        });


    }
}
