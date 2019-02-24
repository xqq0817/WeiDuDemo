package com.example.weidudianshang.view;

public interface DataView<T> {
    void onsuccess(T data);
    void ondefeated(T error);
}
