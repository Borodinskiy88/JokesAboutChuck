package ru.borodinskiy.aleksei.jokesaboutchuck.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Jokes(
//    @SerializedName("categories")
//    val categories: List<Categories>,
    @SerializedName("created_at")
    val created: String,
    @SerializedName("icon_url")
    val iconUrl: String,
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @SerializedName("updated_at")
    val updated: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("value")
    val value: String
)