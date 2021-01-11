package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Before

/**
 * To run a local test that require a class from Android:
 * 1- Add AndroidX test dependencies
 * 2- Add Robolectric dependency
 * 3- Use AndroidX test library
 * 4- Add AndroidJunit4 test runner
 *
 * The related dependency for point (1,2,3)
 * testImplementation "androidx.test.ext:junit-ktx:$androidXTestExtKotlinRunnerVersion"
 * testImplementation "androidx.test:core-ktx:$androidXTestCoreVersion"
 * testImplementation "org.robolectric:robolectric:$robolectricVersion"
 *
 * The class tag for point (4):
 * @RunWith(AndroidJUnit4::class)
 *
 * Note that AndroidX Test can run on both local and emulator instrument
 */
@RunWith(AndroidJUnit4::class)
class TasksViewModelTest {

    // instant task executor rule - junit rules
    // rules run before and after each run
    // dependency: testImplementation "androidx.arch.core:core-testing:$archTestingVersion"
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    // Subject under test
    private lateinit var taskViewModel: TasksViewModel

    @Before
    fun setupViewModel() {
        taskViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun addNewTask_setNewTaskEvent() {

        // When adding new task
        taskViewModel.addNewTask()

        // Then the new task event is triggered
        var value = taskViewModel.newTaskEvent.getOrAwaitValue()
        assertNotNull(value.getContentIfNotHandled())

    }

    @Test
    fun setFilterAllTasks_tasksAddViewVisible() {

        // Given a fresh ViewModel
        val taskViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())

        // When the filter type is ALL_TASKS
        taskViewModel.setFiltering(TasksFilterType.ALL_TASKS)

        // Then the "Add task" action is visible
        var value = taskViewModel.tasksAddViewVisible.getOrAwaitValue()
        assertEquals(true, value)

    }

}