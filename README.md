# unsplash-android-mvvm

Android App using Unsplash [API](https://unsplash.com/developers) for study and play.

*This is an endless project so the status is always on progress*

## Architecture

[MVVM](https://github.com/googlesamples/android-architecture/tree/todo-mvvm-databinding/) + [Dagger2](https://google.github.io/dagger/) + [RXAndroid](https://github.com/ReactiveX/RxAndroid)

## Libraries

- [OkHttp2](http://square.github.io/okhttp)
- [Retrofit](https://square.github.io/retrofit)
- [RxJava](https://github.com/ReactiveX/RxJava)
- [Glide](https://github.com/bumptech/glide)

## Project structure

Traditionally we organize the packages by layers, but I'm trying a new [way](https://medium.com/@cesarmcferreira/package-by-features-not-layers-2d076df1964d#.crq09v17m).

Still can not say which is better.

## Before playing
You need put `private.properties` under the `assets` folder first.
```
##
# Local settings that you don't want to upload
#

client.id=[YOUR_CLIENT_ID]
client.secret=[YOUR_CLIENT_SECRET]
```
Get your `client id` and `client secret` from Unsplash [API](https://unsplash.com/developers)
