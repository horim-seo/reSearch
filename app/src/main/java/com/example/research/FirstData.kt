package com.example.research

data class FirstData(
    var title: String,
    var description: String,
    var urlToImage: String,
    var content: String,
    var articles : List<FirstData>,
    var totalResults: String,
    var status:String
)
