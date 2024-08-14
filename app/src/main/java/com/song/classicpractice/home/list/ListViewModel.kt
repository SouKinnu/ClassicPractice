package com.song.classicpractice.home.list

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.song.baselibrary.BaseViewModel
import com.song.httplibrary.data.ListItem
import com.song.httplibrary.data.ListType
import com.song.httplibrary.data.MiYSheData
import com.song.httplibrary.utils.ApiRepository
import com.song.httplibrary.utils.launchRequest

class ListViewModel : BaseViewModel() {
    private val apiRepository: ApiRepository by lazy {
        ApiRepository()
    }
    val listItem = MutableLiveData<List<ListItem>>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        getMiYouShe()
    }

    private fun getMiYouShe() {
        launchRequest({ apiRepository.getMiYouShe() }, {
            if (it != null) {
                listItem.value = loadListItems(it)
            }
        })
    }

    private fun loadListItems(list: List<MiYSheData>): List<ListItem> {
        val listItems = mutableListOf<ListItem>()
        for (miYSheData in list) {
            listItems.add(ListItem(type = ListType.TITLE, data = miYSheData.name))
            for (item in miYSheData.list) {
                listItems.add(ListItem(type = ListType.CONTENT, data = item))
            }
        }
        return listItems
    }
}