package com.song.httplibrary.data

import android.os.Parcelable
import com.song.httplibrary.utils.API_STATUS_OK
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

/**
 * @Author      : SongJin Yu
 * @Email       : kinnusou@gmail.com
 * @Date        : on 2024/5/17 21:17.
 * @Description : 网络请求最外层视图，封装了服务器响应的数据结构
 *
 * @param T             具体的实体类型
 * @property code       响应的状态码，通常用于判断请求是否成功
 * @property msg        响应的消息，通常用于显示给用户或调试
 * @property result     响应的具体数据，泛型类型，通常是API返回的数据模型
 */
@Parcelize
open class BaseResultData<out T>(
    val code: Int? = null,
    val msg: String? = null,
    val result: @RawValue T? = null,
) : Parcelable {

    /**
     * 判断请求是否成功的辅助方法
     */
    fun isSuccess(): Boolean {
        return code == API_STATUS_OK
    }
}