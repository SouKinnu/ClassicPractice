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
 * @Description : 一个通用的适配器，用于处理具有不同视图类型的列表项。
 * 该适配器使用 ViewBinding 来高效处理视图，并提供了处理不同项类型和视图绑定的抽象方式。
 *
 * @param T 显示在列表中的项的类型。
 * @param VB 用于绑定视图的 ViewBinding 类型。
 * @param diffCallback 用于计算列表之间差异的 DiffUtil.ItemCallback 实例。
 */
abstract class BaseListViewTypeAdapter<T, VB : ViewBinding>(
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BaseListViewTypeAdapter.BaseViewHolder<VB>>(diffCallback) {

    /**
     * 返回给定位置的项的视图类型。
     * 此方法允许在同一适配器中使用多种视图类型。
     *
     * @param position 适配器数据集中项的位置。
     * @param item 给定位置的项。
     * @return 表示项视图类型的整数值。
     */
    protected abstract fun getItemViewType(position: Int, item: T): Int

    /**
     * 为指定的视图类型创建新的 ViewBinding 实例。
     *
     * @param inflater 用于创建视图的 LayoutInflater。
     * @param parent 新视图将附加到的父视图组。
     * @param viewType 要创建的视图类型。
     * @return 指定视图类型的 ViewBinding 实例。
     */
    protected abstract fun onCreateBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): VB

    /**
     * 将数据绑定到指定的 ViewBinding。
     *
     * @param binding 要绑定数据的 ViewBinding 实例。
     * @param item 要绑定的项。
     * @param position 项在适配器中的位置。
     */
    protected abstract fun onBind(binding: VB, item: T, position: Int)

    /**
     * 当 RecyclerView 需要一个新的 ViewHolder 时调用。
     * 创建并返回一个新的 BaseViewHolder 实例。
     *
     * @param parent 新 ViewHolder 将附加到的父视图组。
     * @param viewType 要创建的视图类型。
     * @return 一个新的 BaseViewHolder 实例。
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = onCreateBinding(inflater, parent, viewType)
        return BaseViewHolder(binding)
    }

    /**
     * 用于显示指定位置的数据。
     * 使用 onBind 方法将数据绑定到 ViewBinding。
     *
     * @param holder 要绑定数据的 ViewHolder。
     * @param position 项在适配器中的位置。
     */
    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        val item = getItem(position)
        onBind(holder.binding, item, position)
    }

    /**
     * 返回给定位置项的视图类型。
     *
     * @param position 适配器数据集中项的位置。
     * @return 表示项视图类型的整数值。
     */
    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return getItemViewType(position, item)
    }

    /**
     * 一个用于持有 ViewBinding 实例的 ViewHolder。
     *
     * @param VB 在该 ViewHolder 中使用的 ViewBinding 类型。
     * @property binding ViewBinding 实例。
     */
    class BaseViewHolder<VB : ViewBinding>(val binding: VB) :
        RecyclerView.ViewHolder(binding.root)
}