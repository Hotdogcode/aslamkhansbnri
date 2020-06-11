package com.hotdogcode.aslamkhansbnri.utils.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hotdogcode.aslamkhansbnri.data.model.api.License
import com.hotdogcode.aslamkhansbnri.data.model.api.Permissions

class DataConverters {

    @TypeConverter
    fun convertPermissionToJson(permissions: Permissions?): String {
        return Gson().toJson(permissions)
    }

    @TypeConverter
    fun convertJsonToPermission(permissionString: String): Permissions {
        val type = object : TypeToken<Permissions>() {}.type
        return Gson().fromJson(permissionString, type)
    }

    @TypeConverter
    fun convertLicenseToJson(license: License?): String {
        return Gson().toJson(license)
    }

    @TypeConverter
    fun convertJsonToLicense(licenseString: String): License? {
        val type = object : TypeToken<License>() {}.type
        return Gson().fromJson(licenseString, type)
    }


}