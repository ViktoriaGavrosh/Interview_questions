package com.viktoriagavrosh.interview.ui.screens.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.interview.data.AppRepository
import com.viktoriagavrosh.interview.data.RequestResult
import com.viktoriagavrosh.interview.model.MenuItem
import com.viktoriagavrosh.interview.model.Question
import com.viktoriagavrosh.interview.model.Topic
import com.viktoriagavrosh.interview.model.toMenuItem
import com.viktoriagavrosh.interview.ui.screens.ScreenState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = MenuViewModel.MenuViewModelFactory::class)
class MenuViewModel @AssistedInject constructor(
    @Assisted topicId: Int,
    private val repository: AppRepository
) : ViewModel() {

    private val _menuState = MutableStateFlow<ScreenState<List<MenuItem>>>(ScreenState.Loading())
    val menuState = _menuState.asStateFlow()

    var topicName = ""

    init {
        getAllMenuItem(topicId)
    }

    fun getAllMenuItem(topicId: Int = 0) {
        viewModelScope.launch {
            val flowResults = if (topicId == 0) {
                repository.getAllTopics()
            } else {
                val resultTopic = repository.getTopicById(topicId).first()
                getFlowQuestions(resultTopic)
            }.first()

            when (flowResults) {
                is RequestResult.Success -> {
                    val data = convertData(flowResults.data)
                    _menuState.emit(ScreenState.Success(data = data))
                }

                is RequestResult.Error -> _menuState.emit(ScreenState.Error(error = flowResults.error))
                else -> _menuState.emit(ScreenState.Loading())
            }
        }
    }

    private suspend fun getFlowQuestions(result: RequestResult<Topic>): Flow<RequestResult<List<Question>>> {
        return when (result) {
            is RequestResult.Success -> {
                topicName = result.data.title
                repository.getQuestionsByTopic(topicName)
            }

            is RequestResult.Loading -> flow { emit(RequestResult.Loading()) }
            is RequestResult.Error -> flow { emit(RequestResult.Error(result.error)) }
        }
    }

    private fun convertData(data: List<Any>): List<MenuItem> {
        return data.map {
            when (it) {
                is Question -> it.toMenuItem()
                is Topic -> it.toMenuItem()
                else -> MenuItem()
            }
        }
    }

    @AssistedFactory
    interface MenuViewModelFactory {
        fun create(topicId: Int): MenuViewModel
    }
}
