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
- **MarsRealEstate**: use retrofit to connect to an API to fetch Mars real estates!<br><br>
- **DevByteViewer**: show case room database and retrofit2 with workManager using MVVM architecture.<br><br>
- **Theme**: themes, styles, overlays, attributes, tint and more.<br><br>

### Advanced
All applications under /advanced folder:

- **EggTimer**: learn how to show and custom notifications.<br><br>
- **CustomFanControl**: design and implement the functionality of custom view to control a dummy fan.<br><br>
- **MiniPaint**: draw on custom view using a canvas and cache for better performance.<br><br>
- **TodoTester**: Write basic tests for a todo application, and choose between instrumented/ local tests.


### Extras
Projects to showcase the rest of the Android associate certificate topics, that was not fully covered in the kotlin series applications.<br>
All applications under /extras folder:<bt>
- **MotionDemo**: learn how to build widget animation using the motion layout.



# Android Certificate Topics

This section to show the topics of the Kotlin Android associate developer exam and related reference applications

### Core 
Topic  | Application | Filename 
------------- | ------------- | -------------
Toast  | EggTimer | EggTimerFragment
Snacbar  | SleepQualityTracker | SleepTrackerFragment
Snacbar  | Theme | GdgListFragment
Create a Notification | EggTimer | NotificationUtils
AndroidX | DiceRoller | -
Android KTX | [reference](https://developer.android.com/kotlin/ktx) | -
Android KTX (Navigation) | AndroidTrivia | MainActivity, navigation_main
Android KTX (Room) | GridSleepQualityTracker | -
Android KTX (WorkManager) | DevByteViewer | - 


### User Interface
Topic  | Application | Filename 
------------- | ------------- | -------------
ConstraintLayout | ColorMyviews | activity_main,<br>build.gradle
RecyclerView list | SleepQualityTracker,<br>GridSleepQualityTracker | SleepNightAdapter,<br>SleepTrackerFragment,<br>fragment_sleep_tracker
Navigation drawer | AndroidTrivia | MainActivity,<br>activity_main,<br>navdrawer_menu
Custom view components | CustomFanControl | DialView
*< to be updated >*

### Data Management
*< to be updated >*

### Debugging
Topic  | Reference
------------- | -------------
Debugging | https://developer.android.com/studio/debug/
Android logs | https://developer.android.com/studio/debug/am-logcat


### Testing
Topic  | Application | Filename 
------------- | ------------- | -------------
Junit basic | TodoTester | StatisticsUtilsTest
Hamcrest <br> (English written test) | TodoTester | StatisticsUtilsTest
Junit for liveData and viewModel <br> (AndroidX test) | TodoTester | TasksViewModelTest
Fake viewModel repository | TodoTester | TasksViewModelTestFake
Dependency inject test | TodoTester | DefaultTasksRepositoryTest,<br>,
coroutines test | TodoTester | build.gradle,<br>DefaultTasksRepositoryTest
Fragment tntigration test<br>using junit with fake data<br>(Espresso & Mokito) | TodoTester | 


*< to be updated >*


### Important
Make sure to review the official exam guide:, for the latest updated exam topics:
https://developers.google.com/certification/associate-android-developer/study-guide


# Tools

The applications created using Android studio 4.1.1 
