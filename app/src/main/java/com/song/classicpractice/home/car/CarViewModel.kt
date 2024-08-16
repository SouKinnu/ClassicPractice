package com.song.classicpractice.home.car

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.song.baselibrary.BaseViewModel
import com.song.httplibrary.data.CartItem
import com.song.httplibrary.data.CartType
import com.song.httplibrary.data.json.cartData

/**
 * @Author      : SongJin Yu
 * @Email       : kinnusou@gmail.com
 * @Date        : on 2024/5/17 21:17.
 * @Description : ViewModel 类，用于处理购物车数据的加载和存储。
 */
class CarViewModel : BaseViewModel() {

    // MutableLiveData 用于观察购物车数据的变化
    val carItem = MutableLiveData<List<CartItem>>()

    /**
     * 生命周期创建时调用，初始化数据加载。
     *
     * @param owner 生命周期拥有者
     */
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        // 加载购物车项数据
        carItem.value = loadCartItems()
    }

    /**
     * 从 JSON 数据中加载购物车项。
     *
     * @return 构建好的购物车项列表
     */
    private fun loadCartItems(): List<CartItem> {
        // 获取购物车数据的 JSON 响应
        val cartResponse = cartData

        // 使用可变列表来存储最终的购物车项
        val cartItems = mutableListOf<CartItem>()

        // 遍历每个店铺
        cartResponse.cartItems.forEach { shop ->
            // 将店铺项添加到列表中，店铺项的类型为 SHOP
            cartItems.add(createCartItem(CartType.SHOP, "", shop.isShopSelected, shop))

            // 遍历店铺中的每个商品
            shop.items.forEach { item ->
                // 将商品项添加到列表中，商品项的类型为 ITEM
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

    /**
     * 辅助函数：根据参数创建 CartItem。
     *
     * @param type             项的类型，如 SHOP 或 ITEM
     * @param shopId           店铺 ID，商品项时为空字符串
     * @param isItemSelected   项是否被选中
     * @param data             项的数据
     * @return                 创建的 CartItem 对象
     */
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
