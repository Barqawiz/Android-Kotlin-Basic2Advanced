# Android Kotlin Basic2Advanced

 <image src="_resources/android_head.png" width="200px" />
<p> A series of Android Kotlin apps based on Google & Udacity trainings. In addition to extra resources to cover latest MAD features and Android associate developer certificate requirements.</p>
 
 This repository includes Kotlin sample application created based on latest Android guidelines available in the following references:<br>
- Developing Android apps with Kotlin series. <br>
- Advanced Android apps with Kotlin series. <br>
- [Associate Android developer materials](https://developers.google.com/certification/associate-android-developer).<br>
 
 You can use these applications as reference to learn more about Android latest features or to prepare for Android associate developer certificate. 
 
# Available applications

 The applications tuned a little from the original course with more features to validate extra development capabilities or to work with latest Android studio and Kotlin releases in 2021 after the course:
 
### Basics
All applications under /basic folder:

- **DiceRoller**: the basic of Android layouts and actions.<br><br>
- **Aboutme**: how to use Android data binding features.<br><br>
- **ColorMyviews**: build responsive views with constraint layout.<br><br>
- **AndroidTrivia**: introduction to navigation graphs, navigation drawer and fragments.<br><br>
- **DessertPusher**: handling acitivty/ fragment lifecycle and observers.<br><br>
- **GuessIt**: a word guessing game to learn about application archeticture, viewModel and liveData.<br><br>
- **SleepQualityTracker**: room database, recyclerview, coroutines and basic unit testing.<br><br>
- **GridSleepQualityTracker**: room database and grid layout.<br><br>
- **MarsRealEstate**: demo app using viewModel & liveData with retrofit, glide and moshi in Kotlin.!<br><br>
- **DevByteViewer**: show case room database and retrofit2 with workManager using MVVM architecture.<br><br>
- **Theme**: themes, styles, overlays, attributes, tint and more.<br><br>

### Advanced
All applications under /advanced folder:

- **EggTimer**: learn how to show and custom notifications (*require to generate google-services.json from firebase*).<br><br>
- **CustomFanControl**: design and implement the functionality of custom view to control a dummy fan.<br><br>
- **MiniPaint**: draw on custom view using a canvas and cache for better performance.<br><br>
- **TodoTester**: Write basic tests for a todo application, and choose between instrumented/ local tests.<br><br>
- **firebaseLogin**: Create user authentication using Firebase backend.<br><br>

### Extras
Projects to showcase the rest of the Android associate certificate topics, that was not fully covered in the kotlin series applications.<br>
All applications under /extras folder:<bt>

- **MotionDemo**: learn how to build widget animation using the motion layout.<br><br>
- **MemoryEater**: async task and memory leaks.<br><br>
- **GitHubPaging**: search GitHub for repositories displayed in paging list.<br><br>
- **AppWithSettings**: Simple settings fragment with toggle switch.<br><br>

# Android Certificate Topics

This section to show the topics of the Kotlin Android associate developer exam and related reference applications in this repository.

### Core 
Topic  | Application | Filename 
------------- | ------------- | -------------
Toast  | EggTimer | EggTimerFragment
Snackbar  | SleepQualityTracker | SleepTrackerFragment
Snackbar  | Theme | GdgListFragment
Create a Notification | EggTimer | NotificationUtils,<br>EggTimerFragment (createChannel),<br>AlarmReceiver [optional]
AndroidX | DiceRoller | -
Android KTX | [reference](https://developer.android.com/kotlin/ktx) | -
Android KTX (Navigation) | AndroidTrivia | MainActivity,<br>navigation_main
Android KTX (Room) | GridSleepQualityTracker | -
Android KTX (WorkManager) | DevByteViewer | RefreshDataWork,<br>DevByteApplication (setupPeriodicWorker)


### User Interface
Topic  | Application | Filename 
------------- | ------------- | -------------
ConstraintLayout | ColorMyviews | activity_main,<br>build.gradle
RecyclerView list | SleepQualityTracker,<br>GridSleepQualityTracker | SleepNightAdapter,<br>SleepTrackerFragment,<br>fragment_sleep_tracker
Navigation drawer | AndroidTrivia | MainActivity,<br>activity_main,<br>navdrawer_menu
Custom view components | CustomFanControl | DialView
Start activity | GridSleepQualityTracker | SleepTrackerFragment
Start activity with result | FirebaseLogin | MainFragment (firebaseLauncher)
Share Intent | AndroidTrivia | GameWonFragment (createShareIntent)
themes | Theme | -
Lifecycles | DessertPusher | MainActivity
Paging library and flow | GitHubPaging | GithubPagingSource,<br>GithubRepository,<br>SearchRepositoriesViewModel,<br>ReposAdapter
Loading state footer | GitHubPaging | ReposLoadStateViewHolder,<br>ReposLoadStateAdapter,<br>SearchRepositoriesActivity (initAdapter)
Menu | AndroidTrivia | winner_menu,<br>GameWonFragment

### Data Management
Topic  | Application | Filename | Fun
------------- | ------------- | ------------- | -------------
viewModelScope | TodoTester| TasksViewModel | completeTask,<br>clearCompletedTasks
App settings with shared preferences | AppWithSettings | SettingsFragment, MainActivity (displaySwitchValue)
App settings with shared preferences | firebaseLogin | settings,<br>SettingsFragment
Room database and liveview | SleepQualityTracker,<br>GridSleepQualityTracker | SleepTrackerViewModel (allNights)
Repository | DevByteViewer | VideosRepository,<br>VideoDao,<br>Network

### Debugging
Topic  | Reference
------------- | -------------
Debugging | https://developer.android.com/studio/debug/
Android logs | https://developer.android.com/studio/debug/am-logcat


### Testing
Topic  | Application | Filename | Test Method
------------- | ------------- | ------------- | -------------
Junit basic| TodoTester | StatisticsUtilsTest | Local Test
Hamcrest<br> (English written test) | TodoTester | StatisticsUtilsTest | Local Test
Junit for liveData and viewModel<br> (AndroidX test) | TodoTester | TasksViewModelTest | Local Test
Fake viewModel repository | TodoTester | TasksViewModelTestFake | Local Test
Dependency inject test | TodoTester | DefaultTasksRepositoryTest | Local Test
coroutines test | TodoTester | build.gradle,<br>DefaultTasksRepositoryTest | Local Test
Fragment intigration test<br>(ServiceLocator, Espresso & Mokito) | TodoTester | build.gradle,<br>ServiceLocator,<br>TodoApplication,<br>TaskDetailFragmentTest | AndroidTest
Mocks using mockito | TodoTester | TasksFragmentTest | AndroidTest
runBlockingTest | TodoTester | TasksFragmentTest | AndroidTest
Junit rule & coroutine dispatcher | TodoTester | MainCoroutineRule,<br>TasksViewModelTestFake | Local Test
Room and Dao test | TodoTester | build.gradle,<br>TasksDaoTest | AndroidTest
Room and local data source | TodoTester | build.gradle,<br>TasksLocalDataSourceTest | AndroidTest
End to end espresso test<br>(ActivityScenario used) | TodoTester | TasksActivityTest | AndroidTest

### Important
Make sure to review the official exam guide:, for the latest updated exam topics:
https://developers.google.com/certification/associate-android-developer/study-guide


# Tools

The applications created using Android studio 4.1.1 
