package com.song.classicpractice.home.car

import androidx.recyclerview.widget.LinearLayoutManager
import com.song.baselibrary.BaseFragment
import com.song.classicpractice.databinding.FragmentCarBinding
import com.song.httplibrary.data.CartItem
import com.song.httplibrary.data.ItemType
import com.song.httplibrary.data.json.cartData

class CarFragment : BaseFragment<FragmentCarBinding, CarViewModel>(FragmentCarBinding::inflate) {

    private lateinit var cartAdapter: CartAdapter

    override fun initView() {
        cartAdapter = CartAdapter()
        binding.recyclerViewCart.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewCart.adapter = cartAdapter

        // 加载数据并初始化 RecyclerView
        val cartItems = loadCartItems()
        cartAdapter.submitList(cartItems)

        // 设置按钮点击事件
        setupButtonListeners()
    }

    override fun initData() {
        // 初始化数据（如果需要）
    }

    override fun initEvent() {
        // 初始化事件（如果需要）
    }

    private fun loadCartItems(): List<CartItem> {
        val cartResponse = cartData // 假设这是获取你的 JSON 数据的方法

        val cartItems = mutableListOf<CartItem>()

        // 先添加店铺项
        for (shop in cartResponse.cartItems) {
            cartItems.add(
                CartItem(
                    type = ItemType.SHOP,
                    shopId = shop.shopId,
                    shopName = shop.shopName,
                    shopLogoUrl = shop.shopLogoUrl,
                    isItemSelected = shop.isShopSelected // 从数据中获取是否选中
                )
            )

            // 添加店铺中的商品项
            for (item in shop.items) {
                cartItems.add(
                    CartItem(
                        type = ItemType.ITEM,
                        itemId = item.itemId,
                        itemName = item.itemName,
                        itemImageUrl = item.itemImageUrl,
                        itemPrice = item.itemPrice,
                        itemQuantity = item.itemQuantity,
                        itemTotalPrice = item.itemTotalPrice,
                        itemSku = item.itemSku,
                        isItemSelected = item.isItemSelected,
                        itemDiscount = item.itemDiscount,
                        finalPrice = item.finalPrice,
                        itemLink = item.itemLink
                    )
                )
            }
        }

        return cartItems
    }

    private fun setupButtonListeners() {
        // 设置全选按钮的点击事件
        binding.selectAllButton.setOnClickListener {
            selectAllItems(true)
        }

        // 设置全不选按钮的点击事件
        binding.deselectAllButton.setOnClickListener {
            selectAllItems(false)
        }
    }

    private fun selectAllItems(isSelected: Boolean) {
        val updatedItems = cartAdapter.currentList.map { item ->
            when (item.type) {
                ItemType.SHOP -> {
                    item.copy(isItemSelected = isSelected)
                }

                ItemType.ITEM -> {
                    item.copy(isItemSelected = isSelected)
                }
            }
        }
        cartAdapter.submitList(updatedItems)
        cartAdapter.notifyDataSetChanged()
    }
}
