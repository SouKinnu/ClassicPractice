package com.song.classicpractice.home.video.viewpager.banner.commentsdialog

import androidx.recyclerview.widget.LinearLayoutManager
import com.song.baselibrary.BaseBottomSheetDialogFragment
import com.song.classicpractice.databinding.DialogFragmentCommentsBinding
import com.song.httplibrary.data.CommentItem
import com.song.httplibrary.data.CommentType

/**
 * @Author      : SongJin Yu
 * @Email       : kinnusou@gmail.com
 * @Date        : 2024/5/17 21:17
 * @Description : 评论对话框 Fragment，用于展示视频的评论列表和处理评论的展开/收起逻辑。
 */
class CommentsDialogFragment :
    BaseBottomSheetDialogFragment<DialogFragmentCommentsBinding, CommentsDialogViewModel>(
        DialogFragmentCommentsBinding::inflate
    ) {

    // 使用 lazy 关键字延迟初始化 CommentAdapter，直到第一次使用时才会创建
    private val commentAdapter: CommentAdapter by lazy {
        CommentAdapter()
    }

    // 初始化视图，当 Fragment 的视图被创建时调用
    override fun initView() {
        binding.RecyclerView.apply {
            // 配置 RecyclerView
            adapter = commentAdapter // 设置 RecyclerView 的适配器为 commentAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false) // 设置线性布局管理器
        }

        // 观察 ViewModel 中的用户评论数据变化，并更新适配器的数据
        viewModel.userCommentItem.observe(viewLifecycleOwner) { comments ->
            commentAdapter.submitList(comments) // 将用户评论数据提交给适配器
        }

        // 设置评论选中状态变化的监听器
        commentAdapter.checkedChangeListener { comment, position ->
            // 处理评论展开/收起逻辑
            val currentList = commentAdapter.currentList.toMutableList() // 获取当前评论列表
            val insertIndex = currentList.indexOfFirst { it.commentId == comment.commentId } + 1 // 计算插入位置
            val replies = comment.replies.map {
                CommentItem(CommentType.REPLY, it, comment.commentId) // 创建回复项
            }
            currentList.addAll(insertIndex, replies) // 将回复项插入到评论后面
            commentAdapter.submitList(currentList) // 提交更新后的列表
        }
    }

    // 初始化数据，这里可以进行数据的预加载或初始化
    override fun initData() {
        // 可以在此方法中初始化数据，目前为空实现
    }

    // 初始化事件监听器
    override fun initEvent() {
        // 可以在此方法中设置事件监听器，当前为空实现
    }
}
