package com.song.baselibrary

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.Keep
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.song.baselibrary.utils.InitListener
import java.lang.reflect.ParameterizedType

/**
 * @Author : SongJin yu
 * @Email : kinnusou@gmail.com
 * @Date : 2024/5/16 14:38
 * @Description : 基础 Activity 类，提供视图绑定和 ViewModel 绑定功能
 *
 * @param VB : ViewBinding 子类，用于绑定布局视图
 * @param VM : BaseViewModel 子类，用于绑定 ViewModel 实例
 * @param inflate : 用于初始化视图绑定的函数，接收 LayoutInflater 作为参数
 */
abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel>(
    private val inflate: (LayoutInflater) -> VB
) : AppCompatActivity(), InitListener {

    // ViewModel 实例，用于管理界面数据和状态
    lateinit var viewModel: VM

    // ViewBinding 实例，用于绑定布局视图
    lateinit var binding: VB

    // 生命周期方法：Activity 创建时调用
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 使用传入的函数进行视图绑定
        binding = inflate(layoutInflater)

        // 设置 Activity 的内容视图为绑定的根视图
        setContentView(binding.root)

        // 获取 ViewModel 实例并绑定到该 Activity 上
        viewModel = ViewModelProvider(this)[getViewModelClass()]

        // 将 ViewModel 添加到生命周期观察者中
        lifecycle.addObserver(viewModel)

        // 调用抽象方法，初始化视图
        initView()

        // 调用抽象方法，初始化数据
        initData()

        // 调用抽象方法，初始化事件处理
        initEvent()
    }

    // 生命周期方法：用于附加基础上下文
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
    }

    /**
     * 获取 ViewModel 的 Class 对象
     * 通过反射机制获取当前类的泛型参数类型，并返回对应的 ViewModel Class 对象
     *
     * @return VM 的 Class 对象
     */
    @Keep
    private fun getViewModelClass(): Class<VM> {
        return (javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[1] as Class<VM>
    }
}