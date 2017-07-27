package io.github.howiezuo.unsplash.model.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class Photo : RealmObject() {

    @PrimaryKey
    var id: String? = null

    var width: Int? = null

    var height: Int? = null

    var color: String? = null

    var likes = 0

    var likedByUser = false

    var urls: Urls? = null

}