package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.Event
import com.example.android.architecture.blueprints.todoapp.MainCoroutineRule
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.FakeTestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.*
import org.junit.runner.RunWith
import org.junit.Assert.*

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
@ExperimentalCoroutinesApi
class TasksViewModelTestFake {

    /** start TestCoroutineDispatcher using junit rule ***/
    // related to the function --> completeTask_dataAndSnackbarUpdated
    // related to library execution --> kotlinx-coroutines-test
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    /** end TestCoroutineDispatcher ***/


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

    @Test
    fun completeTask_dataAndSnackbarUpdated() {
        // Create an active task and add it to the repository.
        val task = Task("Title", "Description")
        tasksRepository.addTasks(task)

        // Mark the task as complete task.
        taskViewModel.completeTask(task, true)

        // Verify the task is completed.
        assertThat(tasksRepository.tasksServiceData[task.id]?.isCompleted, `is`(true))

        // Assert that the snackbar has been updated with the correct text.
        val snackbarText: Event<Int> =  taskViewModel.snackbarText.getOrAwaitValue()
        assertThat(snackbarText.getContentIfNotHandled(), `is`(R.string.task_marked_complete))
    }

}