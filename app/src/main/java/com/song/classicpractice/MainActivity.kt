package com.song.classicpractice

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.song.baselibrary.BaseActivity
import com.song.classicpractice.databinding.ActivityMainBinding

class MainActivity :
    BaseActivity<ActivityMainBinding, MainViewModel>(ActivityMainBinding::inflate) {


    override fun initView() {
        // 获取导航主机Fragment并获取其控制器
        val navController =
            (supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment).navController

        // 将底部导航栏与导航控制器关联
        NavigationUI.setupWithNavController(binding.BottomNavigationView, navController)
    }

    override fun initData() {

    }

    override fun initEvent() {

    }
}