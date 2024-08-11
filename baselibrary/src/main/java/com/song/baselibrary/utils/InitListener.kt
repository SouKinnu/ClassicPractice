package com.song.baselibrary.utils

/**
 * @Author : SongJin yu
 * @Email : kinnusou@gmail.com
 * @Date : on 2024/5/16 14:38.
 * @Description : 定义初始化视图、数据和事件的方法
 */
interface InitListener {
    /**
     * 初始化视图组件
     */
    fun initView()

    /**
     * 初始化数据
     */
    fun initData()

    /**
     * 初始化事件监听
     */
    fun initEvent()
}