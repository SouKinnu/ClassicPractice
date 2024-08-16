package com.song.classicpractice.home.car

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.song.baselibrary.BaseFragment
import com.song.classicpractice.R
import com.song.classicpractice.databinding.FragmentCarBinding
import com.song.httplibrary.data.CartItems
import com.song.httplibrary.data.CartType
import com.song.httplibrary.data.json.cartData

/**
 * @Author      : SongJin Yu
 * @Email       : kinnusou@gmail.com
 * @Date        : 2024/5/16 14:38
 * @Description : 购物车界面 Fragment，负责展示和管理购物车中的商品列表。
 */
class CarFragment : BaseFragment<FragmentCarBinding, CarViewModel>(FragmentCarBinding::inflate) {

    // 使用 lazy 关键字延迟初始化 CartAdapter，直到第一次使用时才会创建
    private val cartAdapter: CartAdapter by lazy { CartAdapter() }

    /**
     * 初始化视图，当 Fragment 的视图被创建时调用
     */
    override fun initView() {
        // 为 RecyclerView 设置线性布局管理器和适配器
        binding.recyclerViewCart.apply {
            layoutManager = LinearLayoutManager(requireContext()) // 设置 RecyclerView 为线性布局
            adapter = cartAdapter // 设置 RecyclerView 的适配器为 cartAdapter
        }

        // 监听 ViewModel 中的购物车数据变化，并更新适配器的数据
        viewModel.carItem.observe(viewLifecycleOwner) {
            cartAdapter.submitList(it) // 将购物车数据提交给适配器
        }
    }

    /**
     * 初始化数据，这里可以进行数据的预加载
     */
    override fun initData() {
        // 可在此方法中初始化数据，目前为空实现
    }

    /**
     * 初始化事件监听器
     */
    override fun initEvent() {
        // 设置全选按钮的点击事件监听器
        binding.selectAllButton.setOnClickListener {
            toggleSelectAll() // 调用 toggleSelectAll 方法切换全选状态
        }

        // 配置购物车适配器的事件监听器
        cartAdapter.apply {
            // 监听店铺选中状态变化
            shopCheckedChangeListener { shopId, isSelected ->
                updateShopSelection(shopId, isSelected) // 更新店铺下所有商品的选中状态
            }

            // 监听商品选中状态变化
            goodsCheckedChangeListener { shopId ->
                updateGoodsSelection(shopId) // 更新店铺选中状态
            }
        }
    }

    /**
     * 切换全选或取消全选状态
     */
    private fun toggleSelectAll() {
        // 获取当前全选状态
        val isSelected = !cartData.cartSummary.isAllSelected
        // 根据全选状态设置按钮文本
        binding.selectAllButton.text = getSelectAllButtonText(isSelected)
        // 更新所有购物车项的选中状态
        cartAdapter.submitList(cartAdapter.currentList.map { it.copy(isItemSelected = isSelected) })
        cartData.cartSummary.isAllSelected = isSelected // 正确更新全选状态
    }

    /**
     * 更新店铺下所有商品的选中状态
     *
     * @param shopId  店铺 ID
     * @param isSelected 店铺是否被选中
     */
    private fun updateShopSelection(shopId: String, isSelected: Boolean) {
        // 遍历购物车列表，更新对应店铺下所有商品的选中状态
        val updatedItems = cartAdapter.currentList.map { item ->
            if (item.shopId == shopId) item.copy(isItemSelected = isSelected) else item
        }
        // 提交更新后的列表给适配器
        cartAdapter.submitList(updatedItems)
        // 更新全选按钮的状态
        updateSelectAllButtonState()
    }

    /**
     * 更新店铺的选中状态
     *
     * @param shopId 店铺 ID
     */
    private fun updateGoodsSelection(shopId: String) {
        // 检查该店铺下是否所有商品都被选中
        val allItemsSelected =
            cartAdapter.currentList.filter { it.shopId == shopId && it.type == CartType.ITEM }
                .all { it.isItemSelected }

        // 更新购物车列表中对应店铺的选中状态
        val updatedItems = cartAdapter.currentList.map { item ->
            if (item.type == CartType.SHOP && (item.data as CartItems).shopId == shopId) {
                item.copy(isItemSelected = allItemsSelected)
            } else item
        }
        // 提交更新后的列表给适配器
        cartAdapter.submitList(updatedItems)
        // 更新全选按钮的状态
        updateSelectAllButtonState()
    }

    /**
     * 更新全选按钮的文本和状态
     */
    private fun updateSelectAllButtonState() {
        // 判断是否所有商品都被选中，并更新全选状态
        val allSelected = cartAdapter.currentList.all { it.isItemSelected }
        cartData.cartSummary.isAllSelected = allSelected
        // 根据当前全选状态设置按钮文本
        binding.selectAllButton.text = getSelectAllButtonText(allSelected)
    }

    /**
     * 根据选中状态返回全选按钮的文本
     *
     * @param isSelected 当前全选状态
     * @return 全选按钮的文本
     */
    private fun getSelectAllButtonText(isSelected: Boolean): String {
        return getString(
            if (isSelected) R.string.cancel_select_all else R.string.select_all
        )
    }
}
