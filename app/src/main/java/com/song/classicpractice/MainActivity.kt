package com.song.classicpractice

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.song.baselibrary.BaseActivity
import com.song.classicpractice.databinding.ActivityMainBinding

/**
 * @Author      : SongJin Yu
 * @Email       : kinnusou@gmail.com
 * @Date        : on 2024/5/17 21:17.
 * @Description : 代表主活动界面，负责设置底部导航栏和导航控制器。
 */
class MainActivity :
    BaseActivity<ActivityMainBinding, MainViewModel>(ActivityMainBinding::inflate) {

    // 初始化视图
    override fun initView() {
        // 获取导航主机 Fragment，并获取其控制器
        val navController =
            (supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment).navController

        // 将底部导航栏与导航控制器进行关联
        NavigationUI.setupWithNavController(binding.BottomNavigationView, navController)
    }

    // 初始化数据
    override fun initData() {
        // 如果有需要初始化的数据，可以在此方法中实现
    }

    // 初始化事件
    override fun initEvent() {
        // 如果有需要设置的事件监听器，可以在此方法中实现
    }
}
