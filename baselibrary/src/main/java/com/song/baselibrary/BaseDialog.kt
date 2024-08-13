package com.song.baselibrary

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.viewbinding.ViewBinding

/**
 * @Author      : SongJin Yu
 * @Email       : kinnusou@gmail.com
 * @Date        : on 2024/5/19 22:11.
 * @Description : 一个通用的基础对话框类，支持 ViewBinding 和可配置的对话框属性。
 *
 * @param VB                     ViewBinding 类型，用于绑定布局。
 * @param context                上下文环境。
 * @param themeResId             主题资源 ID，默认为 BaseDialog 样式。
 * @param inflate                布局绑定的函数，用于初始化 ViewBinding 实例。
 * @param canceledOnTouchOutside 点击对话框外部是否取消对话框，默认为 false。
 * @param gravity                对话框在屏幕中的位置，默认为居中显示。
 * @param isCancelable           对话框是否可以通过返回键取消，默认为 true。
 * @param windowWidth            对话框的宽度，默认为包裹内容 (WRAP_CONTENT)。
 * @param windowHeight           对话框的高度，默认为包裹内容 (WRAP_CONTENT)。
 */
open class BaseDialog<VB : ViewBinding>(
    context: Context,
    themeResId: Int = R.style.BaseDialog,
    inflate: (LayoutInflater) -> VB,
    private val canceledOnTouchOutside: Boolean = false,
    private val gravity: Int = Gravity.CENTER,
    private val isCancelable: Boolean = true,
    private val windowWidth: Int = WindowManager.LayoutParams.WRAP_CONTENT,
    private val windowHeight: Int = WindowManager.LayoutParams.WRAP_CONTENT
) : Dialog(context, themeResId) {

    // ViewBinding 实例，用于访问和操作布局中的视图
    var binding: VB = inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupWindowAttributes()
        setCanceledOnTouchOutside(canceledOnTouchOutside)
        setCancelable(isCancelable)
    }

    /**
     * 设置对话框窗口的布局参数，包括宽高、位置等属性。
     */
    private fun setupWindowAttributes() {
        window?.let { window ->
            val layoutParams = window.attributes
            layoutParams.width = windowWidth
            layoutParams.height = windowHeight
            layoutParams.gravity = gravity
            window.attributes = layoutParams
        }
    }
}