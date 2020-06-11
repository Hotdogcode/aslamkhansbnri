package com.hotdogcode.aslamkhansbnri.data.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class License(
    @Expose
    @SerializedName("key")
    val key:String?="",

    @Expose
    @SerializedName("spdx_id")
    val spdxId:String?="",

    @Expose
    @SerializedName("url")
    val url:String?="",

    @Expose
    @SerializedName("name")
    val name:String?="",

    @Expose
    @SerializedName("node_id")
    val nodeId:String?=""
)