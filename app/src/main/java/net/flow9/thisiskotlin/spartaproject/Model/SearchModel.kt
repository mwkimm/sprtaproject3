package net.flow9.thisiskotlin.spartaproject.Model

data class SearchModel (
    var title: String, // 제목
    val dateTime: String, // 시간
    var url: String, // url 주소
    var isLike: Boolean = false // 좋아요
)