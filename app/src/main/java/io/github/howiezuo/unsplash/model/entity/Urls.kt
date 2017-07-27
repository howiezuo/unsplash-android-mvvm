package io.github.howiezuo.unsplash.model.entity

import io.realm.RealmObject


open class Urls : RealmObject() {

    var raw: String? = null

    var full: String? = null

    var regular: String? = null

    var small: String? = null

    var thumb: String? = null

}