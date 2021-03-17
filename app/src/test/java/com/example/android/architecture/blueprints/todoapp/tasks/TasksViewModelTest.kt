package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.Event
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TasksViewModelTest {

    //run tests synchronously for livedata testing
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun addNewTask_setsNewTaskEvent() {
        val tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())

        val observer = Observer<Event<Unit>> {}

        try {
            tasksViewModel.newTaskEvent.observeForever(observer)

            tasksViewModel.addNewTask()

            val value = tasksViewModel.newTaskEvent.value
            assertThat(value?.getContentIfNotHandled(), (not(nullValue())))

        } finally {
            tasksViewModel.newTaskEvent.removeObserver(observer)
        }
    }
}