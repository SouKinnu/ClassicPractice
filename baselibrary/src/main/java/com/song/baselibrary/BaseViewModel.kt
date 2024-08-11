package com.song.baselibrary

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * @Author : SongJin Yu
 * @Email : kinnusou@gmail.com
 * @Date : on 2024/5/16 14:38.
 * @Description : ViewModel 基类，负责管理协程和生命周期，同时提供便捷的协程启动函数。
 */
open class BaseViewModel : ViewModel(), DefaultLifecycleObserver, CoroutineScope {

    // 用于管理协程的 SupervisorJob，确保当一个子协程失败时，不会影响其他子协程。
    private val job = SupervisorJob()

    // 定义协程上下文，使用主线程调度器和 SupervisorJob。
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    /**
     * 启动协程并在主线程中运行
     * @param T  返回值的类型
     * @param block 执行的挂起函数
     */
    fun <T> launchUi(block: suspend () -> T) {
        viewModelScope.launch(Dispatchers.Main) {
            runCatching {
                block()
            }.onFailure {
                handleCoroutineException(it)
            }
        }
    }

    /**
     * 启动协程并在 IO 线程中运行
     * @param T 返回值的类型
     * @param block 执行的挂起函数
     */
    fun <T> launchIo(block: suspend () -> T) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                block()
            }.onFailure {
                handleCoroutineException(it)
            }
        }
    }

    /**
     * 全局处理协程异常的辅助函数
     * @param throwable 捕获的异常
     */
    private fun handleCoroutineException(throwable: Throwable) {
        // 这里可以添加全局异常处理逻辑，例如日志记录或错误报告
        Log.e("BaseViewModel", "Coroutine Exception: ${throwable.message}", throwable)
    }

    // 以下为生命周期事件的处理方法，继承自 DefaultLifecycleObserver。
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        Log.d("BaseViewModel", "onCreate")
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        Log.d("BaseViewModel", "onStart")
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        Log.d("BaseViewModel", "onResume")
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        Log.d("BaseViewModel", "onPause")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Log.d("BaseViewModel", "onStop")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        Log.d("BaseViewModel", "onDestroy")
    }

    /**
     * ViewModel 销毁时取消所有协程
     */
    override fun onCleared() {
        super.onCleared()
        job.cancel() // 取消所有与此 ViewModel 相关联的协程
    }
}