package com.song.httplibrary.utils

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.InputStream

/**
 * 作者: 宋金宇
 * 邮箱: kinnusou@gmail.com
 * 日期: 2024-08-15
 * 描述: 用于下载图片和视频到相册的工具类，使用 Glide 和 OkHttp 组合实现，支持单张、多张图片和视频的下载。
 */
object MediaDownloader {

    private const val TAG = "com.song.httplibrary.utils.MediaDownloader" // 日志标签
    private val okHttpClient = OkHttpClient() // OkHttpClient 实例，用于处理网络请求

    /**
     * 下载单张图片到相册。
     *
     * @param context 上下文对象
     * @param imageUrl 图片的 URL 地址
     * @param imageName 保存的图片名称（可选）
     * @return 下载成功时返回图片的 Uri，失败时返回 null
     */
    suspend fun downloadImage(context: Context, imageUrl: String, imageName: String? = null): Uri? {
        return try {
            // 使用 Glide 下载图片并转换为 Bitmap
            val bitmap = withContext(Dispatchers.IO) {
                Glide.with(context)
                    .asBitmap() // 指定返回类型为 Bitmap
                    .load(imageUrl) // 加载图片 URL
                    .submit() // 提交请求
                    .get() // 获取 Bitmap 对象
            }
            // 保存图片到相册，并返回保存后的 Uri
            saveImageToGallery(context, bitmap, imageName)
        } catch (e: Exception) {
            // 捕获异常并记录日志
            Log.e(TAG, "下载图片失败: ${e.message}")
            null // 返回 null 表示下载失败
        }
    }

    /**
     * 下载多张图片到相册。
     *
     * @param context 上下文对象
     * @param imageUrls 图片 URL 地址列表
     * @return 下载成功的图片 Uri 列表
     */
    suspend fun downloadImages(context: Context, imageUrls: List<String>): List<Uri?> {
        val uris = mutableListOf<Uri?>() // 创建一个列表用于存储 Uri
        // 遍历每一个图片 URL 并进行下载
        for ((index, url) in imageUrls.withIndex()) {
            val uri = downloadImage(context, url, "image_$index.jpg") // 下载并命名图片
            uris.add(uri) // 将 Uri 添加到列表中
        }
        return uris // 返回所有下载成功的 Uri 列表
    }

    /**
     * 下载视频到相册。
     *
     * @param context 上下文对象
     * @param videoUrl 视频的 URL 地址
     * @param videoName 保存的视频名称（可选）
     * @return 下载成功时返回视频的 Uri，失败时返回 null
     */
    suspend fun downloadVideo(context: Context, videoUrl: String, videoName: String? = null): Uri? {
        return try {
            // 使用 OkHttp 下载视频并获取输入流
            val inputStream = withContext(Dispatchers.IO) {
                val request = Request.Builder().url(videoUrl).build() // 构建请求
                okHttpClient.newCall(request).execute().body?.byteStream() // 执行请求并获取输入流
            }
            inputStream?.let { saveVideoToGallery(context, it, videoName) } // 保存视频到相册
        } catch (e: Exception) {
            // 捕获异常并记录日志
            Log.e(TAG, "下载视频失败: ${e.message}")
            null // 返回 null 表示下载失败
        }
    }

    /**
     * 保存图片到相册。
     *
     * @param context 上下文对象
     * @param bitmap 要保存的图片 Bitmap 对象
     * @param imageName 保存的图片名称（可选）
     * @return 保存成功时返回图片的 Uri，失败时返回 null
     */
    private fun saveImageToGallery(context: Context, bitmap: Bitmap, imageName: String?): Uri? {
        // 创建 ContentValues 对象，设置图片的显示名称、类型和保存路径
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, imageName ?: "downloaded_image.jpg") // 设置图片名称
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg") // 设置图片类型为 JPEG
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES) // 设置图片保存路径
        }
        // 插入图片信息到 MediaStore 并获取返回的 Uri
        val uri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        uri?.let {
            // 打开输出流并将 Bitmap 写入到指定的 Uri
            context.contentResolver.openOutputStream(it).use { outputStream ->
                if (outputStream != null) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                } // 将 Bitmap 写入文件
            }
        }
        return uri // 返回保存后的图片 Uri
    }

    /**
     * 保存视频到相册。
     *
     * @param context 上下文对象
     * @param inputStream 视频文件输入流
     * @param videoName 保存的视频名称（可选）
     * @return 保存成功时返回视频的 Uri，失败时返回 null
     */
    private fun saveVideoToGallery(context: Context, inputStream: InputStream, videoName: String?): Uri? {
        // 创建 ContentValues 对象，设置视频的显示名称、类型和保存路径
        val contentValues = ContentValues().apply {
            put(MediaStore.Video.Media.DISPLAY_NAME, videoName ?: "downloaded_video.mp4") // 设置视频名称
            put(MediaStore.Video.Media.MIME_TYPE, "video/mp4") // 设置视频类型为 MP4
            put(MediaStore.Video.Media.RELATIVE_PATH, Environment.DIRECTORY_MOVIES) // 设置视频保存路径
        }
        // 插入视频信息到 MediaStore 并获取返回的 Uri
        val uri = context.contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues)
        uri?.let {
            // 打开输出流并将输入流的数据写入到指定的 Uri
            context.contentResolver.openOutputStream(it).use { outputStream ->
                inputStream.copyTo(outputStream!!) // 将输入流复制到输出流
            }
        }
        return uri // 返回保存后的视频 Uri
    }
}