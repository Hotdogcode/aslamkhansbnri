package com.hotdogcode.aslamkhansbnri.data.model.api

import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.hotdogcode.aslamkhansbnri.utils.converters.DataConverters

@Entity(tableName = "users")
@TypeConverters(DataConverters::class)
data class User(

    @Expose
    @SerializedName("open_issues_count")
    val openIssuesCount:Int=0,

    @Expose
    @SerializedName("permissions")
    val permissions: Permissions?,

    @Expose
    @SerializedName("license")
    val license: License?,

    @Expose
    @SerializedName("name")
    val name:String="",

    @Expose
    @SerializedName("description")
    val description:String?=""
){

    @PrimaryKey(autoGenerate = true)
    var id:Long=0

    override fun toString() = name
}