package com.thekhaeng.library.uiadjustment.core;

/**
 * Created by The Khaeng on 16 Feb 2018 :)
 */

public interface UIAdjustmentLocalStorage{
    void save( String key, Object object );

    <T> Object load( String key, Class<T> clazz );
}
