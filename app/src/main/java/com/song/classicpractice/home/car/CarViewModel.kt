package com.song.classicpractice.home.car

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.song.baselibrary.BaseViewModel
import com.song.httplibrary.data.CartItem
import com.song.httplibrary.data.CartType
import com.song.httplibrary.data.json.cartData

class CarViewModel : BaseViewModel() {
    val carItem = MutableLiveData<List<CartItem>>()
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        carItem.value = loadCartItems()
    }

    private fun loadCartItems(): List<CartItem> {
        // 假设这是获取 JSON 数据的方法
        val cartResponse = cartData

        // 使用可变列表来存储最终的购物车项
        val cartItems = mutableListOf<CartItem>()

        // 遍历每个店铺
        cartResponse.cartItems.forEach { shop ->
            // 将店铺项添加到列表中
            cartItems.add(createCartItem(CartType.SHOP, "", shop.isShopSelected, shop))

            // 遍历店铺中的每个商品
            shop.items.forEach { item ->
                // 将商品项添加到列表中
                cartItems.add(
                    createCartItem(
                        CartType.ITEM,
                        shop.shopId,
                        item.isItemSelected,
                        item
                    )
                )
            }
        }

        // 返回构建好的购物车项列表
        return cartItems
    }

    // 辅助函数：根据参数创建 CartItem
    private fun createCartItem(
        type: CartType,
        shopId: String,
        isItemSelected: Boolean,
        data: Any
    ): CartItem {
        return CartItem(
            type = type,
            shopId = shopId,
            isItemSelected = isItemSelected,
            data = data
        )
    }
}