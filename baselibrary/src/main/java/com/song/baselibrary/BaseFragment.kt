package com.song.baselibrary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Keep
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.song.baselibrary.utils.InitListener
import java.lang.reflect.ParameterizedType

/**
 * @Author : SongJin yu
 * @Email : kinnusou@gmail.com
 * @Date : on 2024/5/16 14:39.
 * @Description : 基础 Fragment 类，提供视图绑定和 ViewModel 绑定功能
 *
 * @param VB ViewBinding 类型，用于绑定布局。
 * @param VM ViewModel 类型，用于数据处理和业务逻辑。
 * @param inflate 布局绑定函数，用于初始化 ViewBinding 实例。
 */
abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(
    private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment(), InitListener {

    // 私有的 ViewBinding 实例，用于存储视图绑定对象，避免内存泄漏
    private var _binding: VB? = null

    // 公开的 ViewBinding 实例，只在视图存在期间有效
    val binding: VB get() = _binding!!

    // 公开的 ViewModel 实例，用于处理数据和业务逻辑
    protected lateinit var viewModel: VM

    /**
     * Fragment 的生命周期方法：创建视图
     * 初始化 ViewBinding 实例并返回根视图
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Fragment 的生命周期方法：视图创建完成
     * 绑定 ViewModel，添加生命周期观察者，并调用初始化方法
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[getViewModelClass()]
        lifecycle.addObserver(viewModel)
        initView()
        initData()
        initEvent()
    }

    /**
     * Fragment 的生命周期方法：销毁视图
     * 清理 ViewBinding 实例，防止内存泄漏
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * 获取 ViewModel 的 Class 对象
     * 利用反射机制获取泛型参数中的 ViewModel 类型
     *
     * @return ViewModel 的 Class 对象
     */
    @Keep
    private fun getViewModelClass(): Class<VM> {
        return (javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[1] as Class<VM>
    }
}