package com.hotdogcode.aslamkhansbnri.data.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Permissions(
    @Expose
    @SerializedName("admin")
    val admin:Boolean?=false,

    @Expose
    @SerializedName("push")
    val push:Boolean?=false,

    @Expose
    @SerializedName("pull")
    val pull:Boolean?=false
)