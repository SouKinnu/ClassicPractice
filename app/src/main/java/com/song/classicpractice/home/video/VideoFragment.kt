package com.song.classicpractice.home.video

import com.google.android.material.tabs.TabLayoutMediator
import com.song.baselibrary.BaseFragment
import com.song.classicpractice.databinding.FragmentVideoBinding
import com.song.classicpractice.home.video.viewpager.banner.BannerFragment
import com.song.classicpractice.home.video.viewpager.player.PlayerFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.song.httplibrary.data.json.tapTabData

/**
 * @Author      : SongJin Yu
 * @Email       : kinnusou@gmail.com
 * @Date        : on 2024/5/17 21:17.
 * @Description : 代表视频界面的 Fragment，包含用于显示视频播放器和横幅的 ViewPager。
 *                 通过 TabLayoutMediator 实现 TabLayout 和 ViewPager 的联动。
 */
class VideoFragment :
    BaseFragment<FragmentVideoBinding, VideoViewModel>(FragmentVideoBinding::inflate) {

    // 初始化视图
    override fun initView() {
        // 创建 ViewPager 显示的 Fragment 列表
        val fragments = listOf(PlayerFragment(), BannerFragment())

        // 初始化 ViewPagerAdapter，将当前 Fragment 作为 FragmentActivity 的上下文
        val adapter = ViewPagerAdapter(requireActivity(), fragments)

        // 设置 ViewPager 的适配器
        binding.viewPager.adapter = adapter
        binding.viewPager.setUserInputEnabled(false) // 禁用用户滑动 ViewPager

        // 使用 TabLayoutMediator 将 TabLayout 和 ViewPager 进行联动
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            // 根据位置设置 Tab 的标题
            tab.text = tapTabData[position].title
        }.attach() // 启动 TabLayout 和 ViewPager 的联动机制
    }

    // 初始化数据
    override fun initData() {
        // 如果有需要初始化的数据，可以在此方法中实现
    }

    // 初始化事件
    override fun initEvent() {
        // 如果有需要设置的事件监听器，可以在此方法中实现
    }

    // 内部类 ViewPagerAdapter 用于为 ViewPager 提供页面
    class ViewPagerAdapter(
        fragmentActivity: FragmentActivity, // 传入 FragmentActivity 作为上下文
        private val fragments: List<Fragment> // 要显示的 Fragment 列表
    ) : FragmentStateAdapter(fragmentActivity) {

        // 返回要显示的页面数量
        override fun getItemCount(): Int = fragments.size

        // 根据位置创建并返回对应的 Fragment
        override fun createFragment(position: Int): Fragment = fragments[position]
    }
}
