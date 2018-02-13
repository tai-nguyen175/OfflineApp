# Offline App 
## What is an Offline App?

Offline App (or Offline-First App) enables user to seamlessly interact with it by using local device storage and then synchronizing the data with some remote storage (cloud database, etc) later via a background process.

## Libraries Used

* Patterns and frameworks
	* MVVM (Model-View-ViewModel) using Google's new Architecture components `ViewModel`, `LiveData`, `LifecycleObserver`, etc.
	* [Clean Architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html) with `ViewModel` interacting with UseCases and the latter interacting with local database. Making each layer highly testable.
* Database
	* [Room Persistence Library](https://developer.android.com/topic/libraries/architecture/room.html), part of Google's new Architecture components.
* Background Job processing
	* [Android Priority JobQueue](https://github.com/yigit/android-priority-jobqueue) which uses [Job Scheduler](https://developer.android.com/reference/android/app/job/JobScheduler.html) for API level Lollipop and above and [GcmNetworkManager](https://developers.google.com/android/reference/com/google/android/gms/gcm/GcmNetworkManager) for API level below Lollipop.
* Remote Call APIs
	* [Retrofit 2](http://square.github.io/retrofit/) to perform HTTP requests.
	* Fake remote database using simple [JSONPlaceholder](https://jsonplaceholder.typicode.com) REST API.
* Dependency Injection
    * [Dagger Android 2.11](https://github.com/google/dagger/releases/tag/dagger-2.11) to manage App and Activity-scoped dependencies.
* Communication between app layers
    * [RxJava2](https://github.com/ReactiveX/RxJava) and [RxAndroid](https://github.com/ReactiveX/RxAndroid) for interacting between `ViewModel` and local database. 
    * [RxRelay](https://github.com/JakeWharton/RxRelay) for publishing requests from the background job so that lifecycle observer components can update local database. 
* Other
    * [ButterKnife](http://jakewharton.github.io/butterknife/) to simplify View and Listener bindings.
    * [Travis CI](https://travis-ci.org/) is used for automating continuous integration.
    * The following quality checks are configured: checkstyle, pmd, findbugs, lint and [RxLint](http://bitbucket.org/littlerobots/rxlint). You can perform all of them at once by executing `./gradlew check`
