package com.example.android.architecture.blueprints.todoapp.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.android.architecture.blueprints.todoapp.data.Task
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi // used with run blocking test
@RunWith(AndroidJUnit4::class)
@SmallTest
class TasksDaoTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    // reference the database
    private lateinit var database: ToDoDatabase

    @Before
    fun initDB() {
        // in inMemoryDatabaseBuilder will be deleted once the process is killed
        database = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                ToDoDatabase::class.java).build()
    }
    @After
    fun closeDB() = database.close()


    @Test
    fun insertTaskAndGetById() = runBlockingTest {
        // GIVEN - Insert a task.
        val task = Task("title", "description")
        database.taskDao().insertTask(task)

        // WHEN - Get the task by id from the database.
        val loaded = database.taskDao().getTaskById(task.id)

        // THEN - The loaded data contains the expected values.
        Assert.assertNotNull(loaded as Task)
        Assert.assertEquals(task.id, loaded.id)
        Assert.assertEquals(task.title, loaded.title)
        Assert.assertEquals(task.description, loaded.description)

    }

    @Test
    fun updateTaskAndGetById() = runBlockingTest {
        // 1. Insert a task into the DAO.
        val task = Task("title", "description")
        database.taskDao().insertTask(task)

        // 2. Update the task by creating a new task with the same ID but different attributes.
        val task2 = Task("title2", "description2", id = task.id)
        database.taskDao().updateTask(task2)

        val loaded = database.taskDao().getTaskById(task.id)

        // 3. Check that when you get the task by its ID, it has the updated values.
        Assert.assertNotNull(loaded as Task)
        Assert.assertEquals(task2.id, loaded.id)
        Assert.assertEquals(task2.title, loaded.title)
        Assert.assertEquals(task2.description, loaded.description)
    }

}