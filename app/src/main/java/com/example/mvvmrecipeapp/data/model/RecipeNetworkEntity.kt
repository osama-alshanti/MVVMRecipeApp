package com.example.mvvmrecipeapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class RecipeNetworkEntity(

    @PrimaryKey
    @SerializedName("pk")
    @ColumnInfo(name = "pk")
    var pk: Int? = null,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    var title: String? = null,

    @SerializedName("publisher")
    @ColumnInfo(name = "publisher")
    var publisher: String? = null,

    @SerializedName("featured_image")
    @ColumnInfo(name = "featured_image")
    var featuredImage: String? = null,

    @SerializedName("rating")
    @ColumnInfo(name = "rating")
    var rating: Int? = 0,

    @SerializedName("source_url")
    @ColumnInfo(name = "source_url")
    var sourceUrl: String? = null,

    @SerializedName("description")
    @ColumnInfo(name = "description")
    var description: String? = null,

    @SerializedName("cooking_instructions")
    @ColumnInfo(name = "cooking_instructions")
    var cookingInstructions: String? = null,

    @SerializedName("ingredients")
    @ColumnInfo(name = "ingredients")
    var ingredients: List<String>? = null,

    @SerializedName("date_added")
    @ColumnInfo(name = "date_added")
    var dateAdded: String? = null,

    @SerializedName("date_updated")
    @ColumnInfo(name = "date_updated")
    var dateUpdated: String? = null,
)