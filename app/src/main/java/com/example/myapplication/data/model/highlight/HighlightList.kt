package com.example.myapplication.data.model.highlight

import com.google.gson.annotations.SerializedName

data class HighlightList(
    @SerializedName("content") val highlightList: List<Highlight>
) {

    val idList: String
        get() {
            var idList = ""
            if (highlightList.isNotEmpty()) {
                highlightList.forEach {
                    idList += "${it.id},"
                }
                idList.substring(0, highlightList.lastIndex)
            }
            return idList
        }
}
