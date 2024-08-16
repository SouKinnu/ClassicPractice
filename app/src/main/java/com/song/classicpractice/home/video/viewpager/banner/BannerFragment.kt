package com.song.classicpractice.home.video.viewpager.banner

import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.view.children
import androidx.viewpager2.widget.ViewPager2
import com.song.baselibrary.BaseFragment
import com.song.classicpractice.databinding.FragmentBannerBinding
import com.song.classicpractice.home.video.BannerAdapter
import com.song.classicpractice.home.video.viewpager.banner.commentsdialog.CommentsDialogFragment
import com.song.httplibrary.data.json.banners

/**
 * @Author      : SongJin Yu
 * @Email       : kinnusou@gmail.com
 * @Date        : on 2024/5/17 21:17.
 * @Description : 代表一个视频的评论数据，包括视频 ID、评论列表和总评论数。
 */
class BannerFragment :
    BaseFragment<FragmentBannerBinding, BannerViewModel>(FragmentBannerBinding::inflate) {

    // 使用 lazy 关键字延迟初始化 BannerAdapter，直到第一次使用时才会创建
    private val bannerAdapter: BannerAdapter by lazy {
        BannerAdapter()
    }

    /**
     * 初始化视图
     */
    override fun initView() {
        // 设置 ViewPager2 的适配器
        binding.viewPager.adapter = bannerAdapter

        // 提交数据列表给适配器
        bannerAdapter.submitList(banners)

        // 根据 banners 的数量动态创建 RadioButton 作为指示器
        banners.forEachIndexed { index, _ ->
            val radioButton = RadioButton(context).apply {
                layoutParams = ViewGroup.LayoutParams(40.dpToPx(), 40.dpToPx()) // 设置 RadioButton 大小
                isChecked = false
            }
            binding.radioGroup.addView(radioButton)
        }

        // 监听 ViewPager2 页面选择变化
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                // 更新 RadioButton 的选中状态
                (binding.radioGroup.getChildAt(position) as RadioButton).isChecked = true
            }
        })

        // 监听 RadioGroup 选中的变化
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val checkedIndex = group.children.indexOfFirst { it.id == checkedId }
            if (checkedIndex != -1) {
                // 根据选中的 RadioButton 更新 ViewPager2 当前页面
                binding.viewPager.currentItem = checkedIndex
            }
        }

        // 设置点击评论按钮的事件监听器
        binding.comments.setOnClickListener {
            val myBottomSheetFragment = CommentsDialogFragment()
            // 显示 CommentsDialogFragment
            activity?.let { myBottomSheetFragment.show(it.supportFragmentManager, "MyBottomSheet") }
        }
    }

    /**
     * 初始化数据
     */
    override fun initData() {
        // 数据初始化逻辑，如果需要可以在此实现
    }

    /**
     * 初始化事件
     */
    override fun initEvent() {
        // 事件初始化逻辑，如果需要可以在此实现
    }

    // 扩展函数将 dp 转换为 px
    private fun Int.dpToPx(): Int {
        val density = resources.displayMetrics.density
        return (this * density).toInt()
    }
}
