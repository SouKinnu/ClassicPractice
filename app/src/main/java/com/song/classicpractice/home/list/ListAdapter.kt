package com.song.classicpractice.home.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.song.baselibrary.BaseListViewTypeAdapter
import com.song.classicpractice.databinding.ListContentItemBinding
import com.song.classicpractice.databinding.ListTitleItemBinding
import com.song.httplibrary.data.Avatar
import com.song.httplibrary.data.ListItem
import com.song.httplibrary.data.ListType

// 自定义适配器，用于处理多种类型的列表项
class ListAdapter : BaseListViewTypeAdapter<ListItem, ViewBinding>(ListItemDiffCallback()) {

    // 伴生对象，定义常量表示列表项的类型
    companion object {
        const val TITLE = 0  // 标题类型
        const val CONTENT = 1  // 内容类型
    }

    // DiffUtil 回调类，用于计算列表项之间的差异
    class ListItemDiffCallback : DiffUtil.ItemCallback<ListItem>() {
        // 判断两个列表项是否为同一项（通过类型判断）
        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem.type == newItem.type
        }

        // 判断两个列表项的内容是否相同
        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem == newItem
        }
    }

    // 根据列表项的位置返回对应的视图类型
    override fun getItemViewType(position: Int, item: ListItem): Int {
        return when (item.type.name) {
            ListType.CONTENT.name -> CONTENT  // 返回内容类型
            ListType.TITLE.name -> TITLE  // 返回标题类型
            else -> CONTENT  // 默认返回内容类型
        }
    }

    // 根据视图类型创建对应的 ViewBinding
    override fun onCreateBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewBinding {
        return when (viewType) {
            CONTENT -> ListContentItemBinding.inflate(inflater, parent, false)  // 创建内容类型的 ViewBinding
            TITLE -> ListTitleItemBinding.inflate(inflater, parent, false)  // 创建标题类型的 ViewBinding
            else -> ListContentItemBinding.inflate(inflater, parent, false)  // 默认创建内容类型的 ViewBinding
        }
    }

    // 绑定数据到视图
    override fun onBind(binding: ViewBinding, item: ListItem, position: Int) {
        when (binding) {
            is ListContentItemBinding -> {
                // 如果绑定的是内容项
                val avatar = item.data as Avatar  // 获取数据并转换为 Avatar 类型
                binding.name.text = avatar.name  // 设置名称
                Glide.with(binding.root).load(avatar.icon).into(binding.icon)  // 使用 Glide 加载图像
            }

            is ListTitleItemBinding -> {
                // 如果绑定的是标题项
                val string = item.data as String  // 获取数据并转换为 String 类型
                binding.title.text = string  // 设置标题文本
            }
        }
    }
}