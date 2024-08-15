package com.song.baselibrary

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

/**
 * 作者: SongJin yu
 * 邮箱: kinnusou@gmail.com
 * 日期: 2024-08-15
 * 描述: 用于图片加载的 Glide 封装类，简化了 Glide 的使用，并提供了常用的功能配置。
 */
class ImageLoader private constructor(context: Context) {

    // 上下文对象，用于 Glide 加载图片
    private val appContext: Context = context.applicationContext

    /**
     * 单例对象，保证全局只存在一个 ImageLoader 实例。
     */
    companion object {
        @Volatile
        private var instance: ImageLoader? = null

        /**
         * 获取单例实例的方法。
         *
         * @param context 上下文对象
         * @return ImageLoader 的单例实例
         */
        fun getInstance(context: Context): ImageLoader {
            return instance ?: synchronized(this) {
                instance ?: ImageLoader(context).also { instance = it }
            }
        }
    }

    /**
     * 使用 Glide 加载图片到指定的 ImageView。
     *
     * @param url 图片的 URL 地址
     * @param imageView 要显示图片的 ImageView
     * @param placeholder 占位图资源 ID（可选）
     * @param errorImage 错误时显示的图片资源 ID（可选）
     * @param skipMemoryCache 是否跳过内存缓存（默认不跳过）
     * @param diskCacheStrategy 磁盘缓存策略（默认使用所有策略）
     */
    fun loadImage(
        url: String,
        imageView: ImageView,
        placeholder: Int? = null,
        errorImage: Int? = null,
        skipMemoryCache: Boolean = false,
        diskCacheStrategy: DiskCacheStrategy = DiskCacheStrategy.ALL
    ) {
        val requestOptions = RequestOptions().apply {
            placeholder?.let { placeholder(it) } // 设置占位图
            errorImage?.let { error(it) } // 设置错误时显示的图片
            skipMemoryCache(skipMemoryCache) // 是否跳过内存缓存
            diskCacheStrategy(diskCacheStrategy) // 磁盘缓存策略
        }

        // 使用 Glide 加载图片
        Glide.with(appContext)
            .load(url) // 加载图片的 URL
            .apply(requestOptions) // 应用请求选项
            .into(imageView) // 加载到 ImageView
    }

    /**
     * 取消所有 Glide 加载的图片请求，避免内存泄漏。
     *
     * @param imageView 要取消加载的 ImageView
     */
    fun clear(imageView: ImageView) {
        Glide.with(appContext).clear(imageView)
    }
}