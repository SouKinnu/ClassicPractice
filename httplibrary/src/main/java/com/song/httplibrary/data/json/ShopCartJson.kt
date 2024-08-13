package com.song.httplibrary.data.json

import com.google.gson.Gson
import com.song.httplibrary.data.Cart

/**
 * @Author      : SongJin Yu
 * @Email       : kinnusou@gmail.com
 * @Date        : on 2024/5/17 21:17.
 * @Description :
 */


val shopCartJson = """
{
  "userId": "123456789",
  "cartItems": [
    {
      "shopId": "shop001",
      "shopName": "酷炫电子店",
      "isShopSelected": true,
      "shopLogoUrl": "https://example.com/shop_logo_001.png",
      "items": [
        {
          "itemId": "item001",
          "itemName": "无线蓝牙耳机",
          "itemImageUrl": "https://example.com/item_image_001.png",
          "itemPrice": 59.99,
          "itemQuantity": 2,
          "itemTotalPrice": 119.98,
          "itemSku": "黑色",
          "isItemSelected": true,
          "itemDiscount": 10.00,
          "finalPrice": 109.98,
          "itemLink": "https://example.com/product/item001"
        },
        {
          "itemId": "item002",
          "itemName": "便携式充电宝 10000mAh",
          "itemImageUrl": "https://example.com/item_image_002.png",
          "itemPrice": 29.99,
          "itemQuantity": 1,
          "itemTotalPrice": 29.99,
          "itemSku": "白色",
          "isItemSelected": true,
          "itemDiscount": 0.00,
          "finalPrice": 29.99,
          "itemLink": "https://example.com/product/item002"
        }
      ]
    },
    {
      "shopId": "shop002",
      "shopName": "时尚精品店",
      "isShopSelected": true,
      "shopLogoUrl": "https://example.com/shop_logo_002.png",
      "items": [
        {
          "itemId": "item003",
          "itemName": "男士休闲手表",
          "itemImageUrl": "https://example.com/item_image_003.png",
          "itemPrice": 79.99,
          "itemQuantity": 1,
          "itemTotalPrice": 79.99,
          "itemSku": "皮带款",
          "isItemSelected": false,
          "itemDiscount": 15.00,
          "finalPrice": 64.99,
          "itemLink": "https://example.com/product/item003"
        },
        {
          "itemId": "item004",
          "itemName": "女士太阳镜",
          "itemImageUrl": "https://example.com/item_image_004.png",
          "itemPrice": 25.99,
          "itemQuantity": 3,
          "itemTotalPrice": 77.97,
          "itemSku": "黑色",
          "isItemSelected": true,
          "itemDiscount": 5.00,
          "finalPrice": 72.97,
          "itemLink": "https://example.com/product/item004"
        }
      ]
    }
  ],
  "cartSummary": {
    "totalItems": 7,
    "totalPrice": 307.93,
    "totalDiscount": 30.00,
    "finalPrice": 277.93,
    "isAllSelected": false
  }
}
""".trimIndent()
val cartData: Cart = Gson().fromJson(shopCartJson, Cart::class.java)