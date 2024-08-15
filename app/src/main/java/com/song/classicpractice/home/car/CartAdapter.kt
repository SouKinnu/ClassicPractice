package com.song.classicpractice.home.car

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.song.baselibrary.BaseListViewTypeAdapter
import com.song.classicpractice.databinding.CartGoodsItemBinding
import com.song.classicpractice.databinding.CartShopItemBinding
import com.song.httplibrary.data.CartItem
import com.song.httplibrary.data.CartItems
import com.song.httplibrary.data.CartType
import com.song.httplibrary.data.Item

class CartAdapter : BaseListViewTypeAdapter<CartItem, ViewBinding>(CartItemDiffCallback()) {

    /**
     * @Author : SongJin yu
     * @Email : kinnusou@gmail.com
     * @Date : 2024/5/16 14:38
     * @Description : 购物车适配器，用于展示店铺和商品的列表项，支持多布局和选中状态的管理。
     */

    // 店铺选中状态变化的监听器
    private var shopCheckedChangeListener: ((String, Boolean) -> Unit)? = null

    // 商品选中状态变化的监听器
    private var goodsCheckedChangeListener: ((String) -> Unit)? = null

    // 设置店铺选中状态变化的监听器
    fun shopCheckedChangeListener(listener: (String, Boolean) -> Unit) {
        shopCheckedChangeListener = listener
    }

    // 设置商品选中状态变化的监听器
    fun goodsCheckedChangeListener(listener: (String) -> Unit) {
        goodsCheckedChangeListener = listener
    }

    companion object {
        const val SHOP = 0  // 店铺类型标识
        const val ITEM = 1  // 商品类型标识
    }

    // DiffUtil 用于高效更新 RecyclerView 的数据
    class CartItemDiffCallback : DiffUtil.ItemCallback<CartItem>() {
        // 判断两个 CartItem 是否是同一个项目（例如通过 ID 判断）
        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem.data == newItem.data
        }

        // 判断两个 CartItem 内容是否相同
        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem == newItem
        }
    }

    // 根据 position 返回对应的 viewType，用于绑定不同的布局
    override fun getItemViewType(position: Int, item: CartItem): Int {
        return when (item.type.name) {
            CartType.SHOP.name -> SHOP  // 返回店铺类型
            CartType.ITEM.name -> ITEM  // 返回商品类型
            else -> SHOP  // 默认返回店铺类型
        }
    }

    // 根据 viewType 创建相应的 ViewBinding
    override fun onCreateBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewBinding {
        return when (viewType) {
            SHOP -> CartShopItemBinding.inflate(inflater, parent, false)  // 创建店铺项的 ViewBinding
            ITEM -> CartGoodsItemBinding.inflate(inflater, parent, false)  // 创建商品项的 ViewBinding
            else -> CartShopItemBinding.inflate(inflater, parent, false)  // 默认创建店铺项的 ViewBinding
        }
    }

    // 绑定数据到对应的 ViewBinding
    override fun onBind(binding: ViewBinding, item: CartItem, position: Int) {
        when (binding) {
            is CartShopItemBinding -> {
                // 处理店铺项的绑定
                val shop = item.data as CartItems
                binding.shopNameTextView.text = shop.shopName  // 设置店铺名称
                binding.shopCheckbox.isChecked = item.isItemSelected  // 设置店铺选中状态
                binding.shopCheckbox.setOnCheckedChangeListener(null)
                // 处理店铺选中状态的变化
                binding.shopCheckbox.setOnCheckedChangeListener { _, isChecked ->
                    item.isItemSelected = isChecked
                    shopCheckedChangeListener?.invoke(shop.shopId, isChecked)  // 通知监听器选中状态的变化
                }
            }

            is CartGoodsItemBinding -> {
                // 处理商品项的绑定
                val goods = item.data as Item
                // 绑定商品名称、价格、数量和总价
                binding.itemNameTextView.text = goods.itemName
                binding.itemPriceTextView.text = goods.itemPrice.toString()
                binding.itemQuantityTextView.text = goods.itemQuantity.toString()
                binding.itemTotalPriceTextView.text = goods.itemTotalPrice.toString()
                // 使用 Glide 加载商品图片
                Glide.with(binding.root).load(goods.itemImageUrl).into(binding.itemImageView)
                binding.itemCheckbox.setOnCheckedChangeListener(null)
                // 设置商品选中状态
                binding.itemCheckbox.isChecked = item.isItemSelected
                // 处理商品选中状态的变化
                binding.itemCheckbox.setOnCheckedChangeListener { _, isChecked ->
                    item.isItemSelected = isChecked
                    goodsCheckedChangeListener?.invoke(item.shopId)  // 通知监听器选中状态的变化
                }
            }
        }
    }
}
