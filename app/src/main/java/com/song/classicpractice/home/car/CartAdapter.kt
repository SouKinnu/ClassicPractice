package com.song.classicpractice.home.car

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.song.baselibrary.BaseListPlusAdapter
import com.song.classicpractice.databinding.CartItemBinding
import com.song.httplibrary.data.CartItem
import com.song.httplibrary.data.ItemType

class CartAdapter : BaseListPlusAdapter<CartItem, CartItemBinding>(CartItemDiffCallback()) {

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): CartItemBinding {
        return CartItemBinding.inflate(inflater, parent, false)
    }

    override fun bind(
        holder: BaseViewHolder<CartItemBinding>,
        binding: CartItemBinding,
        item: CartItem,
        position: Int
    ) {
        when (item.type) {
            ItemType.SHOP -> {
                // 绑定店铺信息
                binding.shopNameTextView.text = item.shopName
                // Glide.with(binding.root).load(item.shopLogoUrl).into(binding.shopLogoImageView)

                // 设置店铺选中状态
                binding.itemCheckbox.isChecked = item.isItemSelected ?: false

                // 隐藏商品视图，显示店铺视图
                binding.itemLayout.visibility = View.GONE
                binding.shopLayout.visibility = View.VISIBLE

                // 处理店铺点击事件，切换店铺下商品的选中状态
                binding.shopLayout.setOnClickListener {
                    val isSelected = !(item.isItemSelected ?: false)
                    binding.itemCheckbox.isChecked = isSelected
                    notifyItemChanged(position) // 更新店铺状态
                    notifyItemRangeChanged(position + 1, getItemCount() - position - 1) // 更新商品状态
                }
            }

            ItemType.ITEM -> {
                // 绑定商品信息
                binding.itemNameTextView.text = item.itemName
                binding.itemPriceTextView.text = item.itemPrice.toString()
                binding.itemQuantityTextView.text = item.itemQuantity.toString()
                binding.itemTotalPriceTextView.text = item.itemTotalPrice.toString()
                // Glide.with(binding.root).load(item.itemImageUrl).into(binding.itemImageView)

                // 商品选中状态
                binding.itemCheckbox.isChecked = item.isItemSelected ?: false

                // 处理点击事件，例如切换选中状态
                binding.itemLayout.setOnClickListener {
                    val isSelected = !(item.isItemSelected ?: false)
                    binding.itemCheckbox.isChecked = isSelected
                    notifyItemChanged(position)
                }

                // 显示商品视图，隐藏店铺视图
                binding.itemLayout.visibility = View.VISIBLE
                binding.shopLayout.visibility = View.GONE
            }
        }
    }

    class CartItemDiffCallback : DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem.itemId == newItem.itemId
        }

        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem == newItem
        }
    }
}
