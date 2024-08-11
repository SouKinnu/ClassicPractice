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
 * @Date : on 2024/5/16 14:38.
 * @Description : 基础 Activity 类，提供视图绑定和 ViewModel 绑定功能
 */
abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel>(
    private val inflate: (LayoutInflater) -> VB
) : AppCompatActivity(), InitListener {

    lateinit var viewModel: VM
    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[getViewModelClass()]
        lifecycle.addObserver(viewModel)
        initView()
        initData()
        initEvent()
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
    }

    /**
     * 获取 ViewModel 的 Class 对象
     * @return ViewModel 的 Class 对象
     */
    @Keep
    private fun getViewModelClass(): Class<VM> {
        return (javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[1] as Class<VM>
    }
}