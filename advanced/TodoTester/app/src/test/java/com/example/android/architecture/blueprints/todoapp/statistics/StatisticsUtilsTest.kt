package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.junit.Test
import org.junit.Assert.*
// english readable test
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`

class StatisticsUtilsTest {

    @Test
    fun getActiveAndCompletedStats_onlyActive() {
        val tasks = listOf<Task>(
                Task("Task1","Work Task", false)
        )

        // When the list of tasks is computed with an active task
        val result = getActiveAndCompletedStats(tasks)

        // Then the percentages are 1 and 100
        /* assertEquals(0f, result.completedTasksPercent)
        assertEquals(100f, result.activeTasksPercent) */
        assertThat(result.completedTasksPercent,  `is`(0f))
        assertThat(result.activeTasksPercent,  `is`(100f))
    }

    @Test
    fun getActiveAndCompletedStats_both() {
        // Given 3 completed tasks and 2 active tasks
        val tasks = listOf<Task>(
                Task("Task1","Work Task", true),
                Task("Task1","Work Task", true),
                Task("Task1","Work Task", false),
                Task("Task1","Work Task", false),
                Task("Task1","Work Task", false),

        )

        // When the list of tasks is computed
        val result = getActiveAndCompletedStats(tasks)

        // Then the result is 40-60
        assertEquals(40f, result.completedTasksPercent)
        assertEquals(60f, result.activeTasksPercent)
    }

    @Test
    fun getActiveAndCompletedStats_empty() {

        // When there are no tasks
        val result = getActiveAndCompletedStats(emptyList<Task>())

        // Both active and completed tasks are 0
        assertEquals(0f, result.completedTasksPercent)
        assertEquals(0f, result.activeTasksPercent)
    }

    @Test
    fun getActiveAndCompletedStats_null() {

        // When there's an error loading stats
        val result = getActiveAndCompletedStats(null)

        // Both active and completed tasks are 0
        assertEquals(0f, result.completedTasksPercent)
        assertEquals(0f, result.activeTasksPercent)
    }

}