package com.song.classicpractice.home.video.viewpager.banner.commentsdialog

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.song.baselibrary.BaseViewModel
import com.song.httplibrary.data.CommentItem
import com.song.httplibrary.data.CommentType
import com.song.httplibrary.data.json.commentsData

/**
 * @Author      : SongJin Yu
 * @Email       : kinnusou@gmail.com
 * @Date        : on 2024/5/17 21:17
 * @Description : 代表评论对话框的 ViewModel，用于加载和管理评论数据。
 */
class CommentsDialogViewModel : BaseViewModel() {
    // 声明一个 MutableLiveData 用于保存用户评论项
    val userCommentItem = MutableLiveData<List<CommentItem>>()

    // 在 ViewModel 的生命周期开始时调用
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        loadCommentItems() // 加载评论项
    }

    // 加载评论项数据
    private fun loadCommentItems() {
        // 从 JSON 数据中获取评论数据
        val cartResponse = commentsData

        // 使用可变列表来存储最终的评论项
        val userCommentItems = mutableListOf<CommentItem>()

        // 遍历每个评论
        cartResponse.comments.forEach { comment ->
            // 将评论项添加到列表中
            userCommentItems.add(CommentItem(CommentType.COMMENT, comment, comment.commentId))
        }

        // 更新 LiveData 的值，以通知观察者数据已变化
        userCommentItem.value = userCommentItems
    }
}
