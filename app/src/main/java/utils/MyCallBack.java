package utils;

/**
 * Created by ti on 2016/12/1.
 */

public abstract class MyCallBack {

    public abstract void loading();
    public abstract void onFailure();
    public abstract void onSuccess(Object o);
    public abstract void onError();
}
