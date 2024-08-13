package com.song.baselibrary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * @Author : SongJin yu
 * @Email : kinnusou@gmail.com
 * @Date : on 2024/5/16 14:39.
 * @Description :一个通用的适配器，用于处理列表项。该适配器使用 ViewBinding 来高效处理视图。
 *
 * @param T 显示在列表中的项的类型。
 * @param VB 用于绑定视图的 ViewBinding 类型。
 * @param diffCallback 用于计算列表之间差异的 DiffUtil.ItemCallback 实例。
 */
abstract class BaseListAdapter<T, VB : ViewBinding>(
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BaseListAdapter.BaseViewHolder<VB>>(diffCallback) {

    /**
     * 创建新的 ViewBinding 实例。
     * 该方法在创建 ViewHolder 时调用。
     *
     * @param inflater 用于创建视图的 LayoutInflater。
     * @param parent 新视图将附加到的父视图组。
     * @return 新创建的 ViewBinding 实例。
     */
    abstract fun createBinding(inflater: LayoutInflater, parent: ViewGroup): VB

    /**
     * 将数据绑定到指定的 ViewBinding。
     *
     * @param binding 要绑定数据的 ViewBinding 实例。
     * @param item 要绑定的项。
     * @param position 项在适配器中的位置。
     */
    abstract fun bind(binding: VB, item: T, position: Int)

    /**
     * 创建一个新的 ViewHolder 实例。
     * 此方法在 RecyclerView 需要一个新的 ViewHolder 时调用。
     *
     * @param parent 新 ViewHolder 将附加到的父视图组。
     * @param viewType 要创建的视图类型（本适配器未使用该参数）。
     * @return 一个新的 BaseViewHolder 实例。
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        val binding = createBinding(LayoutInflater.from(parent.context), parent)
        return BaseViewHolder(binding)
    }

    /**
     * 将数据绑定到 ViewHolder 的视图。
     * 此方法在 RecyclerView 需要绑定数据时调用。
     *
     * @param holder 要绑定数据的 ViewHolder。
     * @param position 项在适配器中的位置。
     */
    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        val item = getItem(position)
        bind(holder.binding, item, position)
    }

    /**
     * 用于持有 ViewBinding 实例的 ViewHolder。
     *
     * @param VB 在该 ViewHolder 中使用的 ViewBinding 类型。
     * @property binding ViewBinding 实例。
     */
    class BaseViewHolder<VB : ViewBinding>(val binding: VB) :
        RecyclerView.ViewHolder(binding.root)
}