package view;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 孙贝贝 on 2016/11/29.
 */

public class ShowToastUtils {

    public static void showToast( Context context,String str){
      Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
    }
}
