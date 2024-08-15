package com.song.httplibrary

import com.song.httplibrary.data.BaseResultData
import com.song.httplibrary.data.MiYSheData
import com.song.httplibrary.utils.MI_YOU_SHE_AVATAR
import retrofit2.http.GET

/**
 * @Author      : Song Jin yu
 * @Email       : kinnusou@gmail.com
 * @Date        : 2024/5/17 13:44
 * @Description : 定义 API 服务接口，声明所有的 API 请求方法。
 */
interface ApiService {
    /***
     * 获取米游社随机头像数据。
     *
     * 这个方法发送一个 GET 请求到 [MI_YOU_SHE_AVATAR] 定义的 URL，并返回一个 [BaseResultData] 对象，
     * 其中包含了米游社随机头像的数据列表。
     *
     * @return 返回一个 [BaseResultData] 对象，包含米游社随机头像数据的列表。
     */
    @GET(MI_YOU_SHE_AVATAR)
    suspend fun getMiYouShe(): BaseResultData<List<MiYSheData>>
}