package com.song.classicpractice.home.car

import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.song.baselibrary.BaseFragment
import com.song.classicpractice.R
import com.song.classicpractice.databinding.FragmentCarBinding
import com.song.httplibrary.data.CartItems
import com.song.httplibrary.data.CartType
import com.song.httplibrary.data.json.cartData

class CarFragment : BaseFragment<FragmentCarBinding, CarViewModel>(FragmentCarBinding::inflate) {

    // 使用 lazy 初始化 CartAdapter，延迟加载
    private val cartAdapter: CartAdapter by lazy {
        CartAdapter()
    }

    // 初始化视图
    override fun initView() {
        // 设置 RecyclerView 的布局管理器为线性布局管理器
        binding.recyclerViewCart.layoutManager = LinearLayoutManager(requireContext())
        // 为 RecyclerView 设置适配器
        binding.recyclerViewCart.adapter = cartAdapter

        // 监听 ViewModel 中购物车项的变化，更新 RecyclerView 数据
        viewModel.carItem.observe(requireActivity()) {
            val cartItems = it
            // 提交购物车项列表数据给适配器
            cartAdapter.submitList(cartItems)
        }
    }

    // 初始化数据（如果需要）
    override fun initData() {
        // 此方法中可用于初始化数据，目前为空实现
    }

    // 初始化事件监听
    override fun initEvent() {
        // 设置全选按钮的点击事件
        binding.selectAllButton.setOnClickListener {
            // 调用 selectAllItems 方法处理全选或取消全选操作
            selectAllItems(cartData.cartSummary.isAllSelected)
        }

        // 配置 CartAdapter 的事件监听器
        cartAdapter.apply {
            // 店铺选中状态变化的监听器
            shopCheckedChangeListener { shopName, isSelected ->
                // 更新购物车项列表
                val updatedItems = cartAdapter.currentList.map { item ->
                    // 如果当前项的 shopName 与传入的 shopName 匹配，则更新 isItemSelected 状态
                    if (item.shopName == shopName) {
                        item.copy(isItemSelected = isSelected)
                    } else {
                        // 否则保持原样
                        item
                    }
                }
                // 提交更新后的列表以刷新界面
                submitList(updatedItems)
            }

            // 商品选中状态变化的监听器
            goodsCheckedChangeListener { s ->
                // 计算当前店铺下的所有商品是否都被选中
                val result = cartAdapter.currentList
                    .filter { it.shopName == s }  // 筛选出与传入的 shopName 匹配的商品项
                    .all { it.isItemSelected == true }  // 检查这些商品项的 isItemSelected 是否都为 true

                // 遍历 currentList 对列表进行映射转换
                val updatedItems = cartAdapter.currentList.map { item ->
                    Log.d("CartLog", "initEvent: 商品选中状态 = ${item.isItemSelected}")
                    // 判断当前项是否为店铺项，且其 shopName 与传入的 s 匹配
                    if (item.type == CartType.SHOP && item.data is CartItems && (item.data as CartItems).shopName == s) {
                        // 如果上述条件成立，则将当前店铺的 isItemSelected 更新为 result
                        item.copy(isItemSelected = result)
                    } else {
                        // 否则保持原样
                        item
                    }
                }
                // 提交更新后的列表以刷新界面
                submitList(updatedItems)
            }
        }
    }

    // 处理全选或取消全选操作
    private fun selectAllItems(isSelected: Boolean) {
        // 设置全选按钮的文本
        binding.selectAllButton.text = getSelectAllButtonText(isSelected)

        // 更新购物车项的选中状态
        val updatedItems = cartAdapter.currentList.map { item ->
            item.copy(isItemSelected = isSelected)
        }
        // 更新购物车数据中的全选状态
        cartData.cartSummary.isAllSelected = !isSelected

        // 提交更新后的列表
        cartAdapter.submitList(updatedItems)
    }

    // 辅助函数：根据选中状态返回全选按钮的文本
    private fun getSelectAllButtonText(isSelected: Boolean): String {
        return if (isSelected)
            ContextCompat.getString(requireContext(), R.string.cancel_select_all)
        else
            ContextCompat.getString(requireContext(), R.string.select_all)
    }
}