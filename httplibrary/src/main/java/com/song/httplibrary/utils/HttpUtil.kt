package com.song.httplibrary.utils

/**
 * @Author      : Song Jin yu
 * @Email       : kinnusou@gmail.com
 * @Date        : 2024/5/17 13:48
 * @Description : 该文件包含了一些常量定义，用于网络请求和 API 地址的配置。
 */

/** API 请求成功的状态码 */
const val API_STATUS_OK = 200

/** 网络请求超时时间（秒） */
const val TIME_OUT = 30L

/** 基础 API 地址 */
const val BASE_URL = "https://api.oioweb.cn/"

/***
 * 米游社随机头像 API 接口路径
 * 用于获取米游社的随机头像图片
 */
const val MI_YOU_SHE_AVATAR = "/api/picture/miyoushe_avatar"
