package io.github.howiezuo.unsplash;

import android.app.Application;

import java.io.IOException;
import java.util.Properties;


public class AppApplication extends Application {

    private static AppApplication INSTANCE;

    private String clientId;
    private String clientSecret;

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;

        Properties properties = new Properties();
        try {
            properties.load(getAssets().open("private.properties"));
            clientId = properties.getProperty("client.id");
            clientSecret = properties.getProperty("client.secret");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AppApplication getInstance() {
        return INSTANCE;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}
