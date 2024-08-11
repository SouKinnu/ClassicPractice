package com.song.httplibrary.utils

import android.util.Log
import com.song.baselibrary.BaseViewModel
import com.song.httplibrary.data.BaseResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @Author      : SongJin Yu
 * @Email       : kinnusou@gmail.com
 * @Date        : on 2024/5/17 16:06.
 * @Description : 封装的网络请求，支持成功、失败和完成回调
 *
 * @param T      返回数据类型
 * @param block  网络请求的具体实现，返回一个 [BaseResultData] 对象
 * @param success 请求成功的回调函数，处理成功的结果
 * @param error  请求失败的回调函数，处理异常情况 (可选)
 * @param finally 请求结束后的回调函数，无论成功与否都会执行 (可选)
 */
fun <T> BaseViewModel.launchRequest(
    block: suspend () -> BaseResultData<T>,
    success: suspend (T?) -> Unit,
    error: suspend (Throwable) -> Unit = { throwable ->
        // 默认的错误处理，可以根据需要进行全局处理
        Log.e("launchRequest", "请求失败：${throwable.message}", throwable)
    },
    finally: suspend () -> Unit = {
        // 默认的 finally 处理，可以在这里添加全局操作
        Log.d("launchRequest", "请求结束")
    }
) {
    launchUi {
        try {
            val resultData = withContext(Dispatchers.IO) {
                block()
            }
            Log.d("launchRequest", "请求结果：$resultData")
            if (resultData.isSuccess()) {
                success.invoke(resultData.result)
            } else {
                error.invoke(Exception("请求失败，错误码：${resultData.code}，错误信息：${resultData.msg}"))
            }
        } catch (e: Exception) {
            Log.e("launchRequest", "请求异常：${e.message}", e)
            error.invoke(e)
        } finally {
            finally.invoke()
        }
    }
}