package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.FakeTestRepository
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Before

/**
 * Important: Note this fake version does not require the AndroidJUnit4 any more, because it use fake datasource
 *
 * To run a local test that require a class from Android:
 * 1- Add AndroidX test dependencies
 * 2- Use AndroidX test library
 * 3- Add espresso and mokito
 *
 * The related dependency for point (1,2)
 * testImplementation "androidx.test.ext:junit-ktx:$androidXTestExtKotlinRunnerVersion"
 * testImplementation "androidx.test:core-ktx:$androidXTestCoreVersion"
 *
 * Note that AndroidX Test can run on both local and emulator instrument
 *
 *
 */
class TasksViewModelTestFake {

    // Use a fake repository to be injected into the viewmodel
    private lateinit var tasksRepository: FakeTestRepository

    // instant task executor rule - junit rules
    // rules run before and after each run
    // dependency: testImplementation "androidx.arch.core:core-testing:$archTestingVersion"
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    // Subject under test
    private lateinit var taskViewModel: TasksViewModel

    @Before
    fun setupViewModel() {

        // how to get application context from text test class
        // ApplicationProvider.getApplicationContext()

        // We initialise the tasks to 3, with one active and two completed
        tasksRepository = FakeTestRepository()
        val task1 = Task("Title1", "Description1")
        val task2 = Task("Title2", "Description2", true)
        val task3 = Task("Title3", "Description3", true)
        tasksRepository.addTasks(task1, task2, task3)

        taskViewModel = TasksViewModel(tasksRepository)
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

        // When the filter type is ALL_TASKS
        taskViewModel.setFiltering(TasksFilterType.ALL_TASKS)

        // Then the "Add task" action is visible
        var value = taskViewModel.tasksAddViewVisible.getOrAwaitValue()
        assertEquals(true, value)

    }

}