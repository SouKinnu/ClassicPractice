package com.song.classicpractice.home.list

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.song.baselibrary.BaseViewModel
import com.song.httplibrary.data.ListItem
import com.song.httplibrary.data.ListType
import com.song.httplibrary.data.MiYSheData
import com.song.httplibrary.utils.ApiRepository
import com.song.httplibrary.utils.launchRequest

/**
 * @Author      : SongJin Yu
 * @Email       : kinnusou@gmail.com
 * @Date        : on 2024/5/17 21:17.
 * @Description : ViewModel 类，处理加载和存储。
 */
class ListViewModel : BaseViewModel() {

    // 使用懒加载初始化 ApiRepository 实例
    private val apiRepository: ApiRepository by lazy {
        ApiRepository()
    }

    // MutableLiveData 用于观察评论数据的变化
    val listItem = MutableLiveData<List<ListItem>>()

    /**
     * 生命周期创建时调用，初始化数据加载。
     *
     * @param owner 生命周期拥有者
     */
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        // 调用获取数据的方法
        getMiYouShe()
    }

    /**
     * 发起网络请求获取 MiYouShe 数据。
     */
    private fun getMiYouShe() {
        // 使用 launchRequest 启动异步请求
        launchRequest(
            // 请求的实际逻辑，调用 apiRepository 获取 MiYouShe 数据
            { apiRepository.getMiYouShe() },
            // 请求成功的回调
            {
                if (it != null) {
                    // 将获取到的数据转化为 ListItem 并更新 MutableLiveData
                    listItem.value = loadListItems(it)
                }
            }
        )
    }

    /**
     * 将 MiYSheData 数据转换为 ListItem 列表。
     *
     * @param list MiYSheData 列表
     * @return 转换后的 ListItem 列表
     */
    private fun loadListItems(list: List<MiYSheData>): List<ListItem> {
        val listItems = mutableListOf<ListItem>()
        for (miYSheData in list) {
            // 将每个 MiYSheData 的名称添加为标题类型的 ListItem
            listItems.add(ListItem(type = ListType.TITLE, data = miYSheData.name))
            // 将每个 MiYSheData 的子项添加为内容类型的 ListItem
            for (item in miYSheData.list) {
                listItems.add(ListItem(type = ListType.CONTENT, data = item))
            }
        }
        return listItems
    }
}
