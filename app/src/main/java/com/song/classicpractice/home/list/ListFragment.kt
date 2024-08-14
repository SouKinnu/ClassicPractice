package com.song.classicpractice.home.list

import androidx.recyclerview.widget.GridLayoutManager
import com.song.baselibrary.BaseFragment
import com.song.classicpractice.databinding.FragmentListBinding
import com.song.httplibrary.data.ListType

// ListFragment 类继承自 BaseFragment，用于显示带有网格布局的列表
class ListFragment :
    BaseFragment<FragmentListBinding, ListViewModel>(FragmentListBinding::inflate) {

    // 懒加载 GridLayoutManager，使用 4 列的网格布局
    private val gridLayoutManager: GridLayoutManager by lazy {
        GridLayoutManager(requireContext(), 4)
    }

    // 懒加载 ListAdapter 适配器，用于绑定数据到 RecyclerView
    private val avatarAdapter: ListAdapter by lazy {
        ListAdapter()
    }

    // 初始化视图
    override fun initView() {
        // 观察 ViewModel 中的 listItem 数据
        viewModel.listItem.observe(requireActivity()) { listItems ->
            // 将数据提交给适配器
            avatarAdapter.submitList(listItems)

            // 设置 GridLayoutManager 的 SpanSizeLookup，用于动态调整列宽
            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                // 根据列表项的类型返回不同的列宽
                override fun getSpanSize(position: Int): Int {
                    return when (listItems[position].type) {
                        ListType.TITLE -> 4  // 标题项占据整行
                        ListType.CONTENT -> 1  // 内容项占据一列
                    }
                }
            }

            // 为 RecyclerView 设置适配器
            binding.root.adapter = avatarAdapter
        }
    }

    // 初始化数据
    override fun initData() {
        // 设置 RecyclerView 的布局管理器为 GridLayoutManager
        binding.root.layoutManager = gridLayoutManager
    }

    // 初始化事件处理（此处为空，留作扩展）
    override fun initEvent() {
        // 可以在此添加事件监听或处理逻辑
    }
}