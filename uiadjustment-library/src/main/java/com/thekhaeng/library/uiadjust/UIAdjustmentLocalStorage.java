package com.thekhaeng.library.uiadjust;

/**
 * Created by「 The Khaeng 」on 16 Feb 2018 :)
 */

interface UIAdjustmentLocalStorage{
    void save( String key, Object object );

    <T> T load( String key, Class<T> clazz );
}
