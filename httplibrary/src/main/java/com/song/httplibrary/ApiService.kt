package com.song.httplibrary

import com.song.httplibrary.data.BaseResultData
import com.song.httplibrary.data.MiYSheData
import com.song.httplibrary.utils.MI_YOU_SHE_AVATAR
import retrofit2.http.GET


/**
 * @Author      : SongJin yu
 * @Email       : kinnusou@gmail.com
 * @Date        : on 2024/5/17 13:44.
 * @Description :描述
 */
interface ApiService {
    /*** 米游社随机头像*/
    @GET(MI_YOU_SHE_AVATAR)
    suspend fun getMiYouShe(): BaseResultData<List<MiYSheData>>
}