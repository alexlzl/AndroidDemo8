package com.example.myapplication;

import android.net.Uri;

/**
 * Created by liuzhouliang on 2018/1/16.
 */

public interface FrescoBitmapCallback<T> {

    void onSuccess(Uri uri, T result);

    void onFailure(Uri uri, Throwable throwable);

    void onCancel(Uri uri);
}



