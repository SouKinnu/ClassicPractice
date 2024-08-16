package com.song.classicpractice.home.video

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.song.baselibrary.BaseListAdapter
import com.song.classicpractice.databinding.ItemBannerBinding
import com.song.httplibrary.data.json.BannerItem

/**
 * @Author : SongJin Yu
 * @Email : kinnusou@gmail.com
 * @Date : on 2024/5/16 14:39
 * @Description : 用于展示轮播图的适配器，继承自 BaseListAdapter。
 *
 * @param banners 用于展示的 BannerItem 列表。
 */
class BannerAdapter : BaseListAdapter<BannerItem, ItemBannerBinding>(BannerItemDiffCallback()) {

    /**
     * DiffUtil.Callback 实现，用于高效比较和更新 BannerItem 列表。
     */
    class BannerItemDiffCallback : DiffUtil.ItemCallback<BannerItem>() {
        // 判断两个 BannerItem 是否为同一个项目
        override fun areItemsTheSame(oldItem: BannerItem, newItem: BannerItem): Boolean {
            return oldItem.id == newItem.id
        }

        // 判断两个 BannerItem 内容是否相同
        override fun areContentsTheSame(oldItem: BannerItem, newItem: BannerItem): Boolean {
            return oldItem == newItem
        }
    }

    /**
     * 创建 ViewBinding 实例。
     *
     * @param inflater 用于创建布局的 LayoutInflater。
     * @param parent 父视图容器。
     * @return 创建的 ItemBannerBinding 实例。
     */
    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ItemBannerBinding {
        return ItemBannerBinding.inflate(inflater, parent, false)
    }

    /**
     * 将数据绑定到 ViewBinding。
     *
     * @param binding 绑定的 ViewBinding 实例。
     * @param item 要绑定的数据项（BannerItem）。
     * @param position 数据项的位置。
     */
    override fun bind(binding: ItemBannerBinding, item: BannerItem, position: Int) {
        // 使用 Glide 加载 BannerItem 的图片
        Glide.with(binding.root.context)
            .load(item.imageUrl) // 设置图片 URL
            .into(binding.imageView) // 加载图片到 ImageView
    }
}
