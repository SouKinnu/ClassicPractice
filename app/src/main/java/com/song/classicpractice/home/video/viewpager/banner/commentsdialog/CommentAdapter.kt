package com.song.classicpractice.home.video.viewpager.banner.commentsdialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.song.baselibrary.BaseListViewTypeAdapter
import com.song.classicpractice.databinding.CommentCommentItemBinding
import com.song.classicpractice.databinding.CommentReplyItemBinding
import com.song.httplibrary.data.Comment
import com.song.httplibrary.data.CommentItem
import com.song.httplibrary.data.CommentType
import com.song.httplibrary.data.Reply

/**
 * @Author      : SongJin Yu
 * @Email       : kinnusou@gmail.com
 * @Date        : 2024/5/17 21:17
 * @Description : 评论适配器，用于展示评论和回复的列表项，支持多布局和选中状态的管理。
 */
class CommentAdapter : BaseListViewTypeAdapter<CommentItem, ViewBinding>(ListItemDiffCallback()) {

    // 选中状态变化的监听器
    private var checkedChangeListener: ((Comment, Int) -> Unit)? = null

    /**
     * 设置选中状态变化的监听器
     *
     * @param listener 监听器函数，接收评论和位置
     */
    fun checkedChangeListener(listener: (Comment, Int) -> Unit) {
        checkedChangeListener = listener
    }

    companion object {
        const val COMMENT = 0 // 评论类型标识
        const val REPLY = 1 // 回复类型标识
    }

    /**
     * DiffUtil 用于高效更新 RecyclerView 的数据
     */
    class ListItemDiffCallback : DiffUtil.ItemCallback<CommentItem>() {
        // 判断两个 CommentItem 是否是同一个项目（例如通过 ID 判断）
        override fun areItemsTheSame(oldItem: CommentItem, newItem: CommentItem): Boolean {
            return oldItem == newItem
        }

        // 判断两个 CommentItem 内容是否相同
        override fun areContentsTheSame(oldItem: CommentItem, newItem: CommentItem): Boolean {
            return oldItem == newItem
        }
    }

    /**
     * 根据 position 返回对应的 viewType，用于绑定不同的布局
     */
    override fun getItemViewType(position: Int, item: CommentItem): Int {
        return when (item.type) {
            CommentType.COMMENT -> COMMENT // 返回评论类型
            CommentType.REPLY -> REPLY // 返回回复类型
        }
    }

    /**
     * 根据 viewType 创建相应的 ViewBinding
     */
    override fun onCreateBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewBinding {
        return when (viewType) {
            COMMENT -> CommentCommentItemBinding.inflate(inflater, parent, false) // 创建评论项的 ViewBinding
            REPLY -> CommentReplyItemBinding.inflate(inflater, parent, false) // 创建回复项的 ViewBinding
            else -> CommentCommentItemBinding.inflate(inflater, parent, false) // 默认创建评论项的 ViewBinding
        }
    }

    /**
     * 绑定数据到对应的 ViewBinding
     */
    override fun onBind(binding: ViewBinding, item: CommentItem, position: Int) {
        when (binding) {
            is CommentCommentItemBinding -> {
                val comment = item.data as Comment
                // 处理评论项的绑定
                binding.name.text = comment.userName // 设置用户名称
                binding.content.text = comment.content // 设置评论内容
                Glide.with(binding.root).load(comment.userAvatarUrl).into(binding.cover) // 加载用户头像
                binding.isExpanded.setOnCheckedChangeListener { _, isChecked ->
                    // 处理评论选中状态的变化
                    checkedChangeListener?.invoke(comment, position) // 通知监听器选中状态的变化
                }
            }
            is CommentReplyItemBinding -> {
                val reply = item.data as Reply
                // 处理回复项的绑定
                binding.name.text = reply.userName // 设置用户名称
                binding.content.text = reply.content // 设置回复内容
                Glide.with(binding.root).load(reply.userAvatarUrl).into(binding.cover) // 加载用户头像
            }
        }
    }
}
