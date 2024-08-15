package com.song.httplibrary.utils

import com.song.httplibrary.ApiService
import com.song.httplibrary.HttpClient
import com.song.httplibrary.data.BaseResultData
import com.song.httplibrary.data.MiYSheData

/**
 * @Author      : Song Jin yu
 * @Email       : kinnusou@gmail.com
 * @Date        : 2024/5/18 13:46
 * @Description : 该类负责与 API 进行交互，提供数据访问功能。
 */
class ApiRepository {
    /**
     * API 服务实例，由 [HttpClient] 提供。使用 lazy 初始化确保只有在首次访问时才会创建实例。
     */
    private val mApiService: ApiService by lazy {
        HttpClient().apiService
    }

    /***
     * 获取米游社随机头像数据。
     *
     * 这个方法调用 [ApiService] 中定义的 `getMiYouShe` 方法，返回米游社随机头像的数据列表。
     *
     * @return 返回一个 [BaseResultData] 对象，包含米游社随机头像数据的列表。
     */
    suspend fun getMiYouShe(): BaseResultData<List<MiYSheData>> {
        return mApiService.getMiYouShe()
    }
}