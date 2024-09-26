package com.example.composeApp.data

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class Note() : RealmObject {

    @PrimaryKey
    var id: String = ""
    var title: String = ""
    var content: String = ""
    var date: String = ""

    constructor(id: String, title: String, content: String, date: String) : this() {
        this.id = id
        this.title = title
        this.content = content
        this.date = date
    }
}
