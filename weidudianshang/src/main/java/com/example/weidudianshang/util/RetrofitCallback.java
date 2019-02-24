package com.example.weidudianshang.util;

public interface RetrofitCallback<T> {
    void success(T result);
    void defeated(T error);
}
