package io.github.howiezuo.unsplash.app

import android.app.Application
import java.io.IOException
import java.util.*


class AppApplication : Application() {

    var clientId: String? = null
        private set
    var clientSecret: String? = null
        private set

    override fun onCreate() {
        super.onCreate()

        instance = this

        val properties = Properties()
        try {
            properties.load(assets.open("private.properties"))
            clientId = properties.getProperty("client.id")
            clientSecret = properties.getProperty("client.secret")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    companion object {
        lateinit var instance: AppApplication
            private set
    }

}
