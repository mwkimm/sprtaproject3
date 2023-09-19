package net.flow9.thisiskotlin.spartaproject

import com.google.gson.annotations.SerializedName

data class RecyclerViewModel (
    @SerializedName("image_url")
    var thumbnail: String,
    @SerializedName("display_sitename")
    var sitename: String,
    @SerializedName("datetime")
    var detetime: String
    )
    data class RecyclerViewModelList(
        @SerializedName("documents")
        val data: List<RecyclerViewModel>
    )
