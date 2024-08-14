package com.song.httplibrary.utils

import com.song.httplibrary.ApiService
import com.song.httplibrary.HttpClient
import com.song.httplibrary.data.BaseResultData
import com.song.httplibrary.data.MiYSheData


/**
 * @Author      : SongJin yu
 * @Email       : kinnusou@gmail.com
 * @Date        : on 2024/5/18 13:46.
 * @Description :描述
 */
class ApiRepository {
    private val mApiService: ApiService by lazy {
        HttpClient().apiService
    }


    /*** /*** 米游社随机头像*/*/
    suspend fun getMiYouShe(
    ): BaseResultData<List<MiYSheData>> {
        return mApiService.getMiYouShe()
    }


}