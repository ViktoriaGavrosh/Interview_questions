package com.viktoriagavrosh.interview.ui.screens.menu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.viktoriagavrosh.interview.ui.screens.ScreenState
import com.viktoriagavrosh.interview.ui.screens.error.ErrorScreen

@Composable
fun MenuScreen(
    onGameButtonClick: (Int) -> Unit,
    onAddButtonClick: () -> Unit,
    onEditButtonClick: (Int) -> Unit,
    onMenuItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    topicId: Int = 0,
    onBackClick: () -> Unit = {},
) {
    val viewModel: MenuViewModel = viewModel(factory = MenuViewModel.factory(topicId = topicId))

    LaunchedEffect(Unit) {
        viewModel.getAllMenuItem(topicId)
    }

    val uiState = viewModel.menuState.collectAsStateWithLifecycle()

    MenuScreen(
        screenStateProvider = { uiState.value },
        topBarTitleProvider = viewModel::topicName,
        onGameButtonClick = { onGameButtonClick(topicId) },
        onAddButtonClick = onAddButtonClick,
        onEditButtonClick = onEditButtonClick,
        onMenuItemClick = onMenuItemClick,
        modifier = modifier,
        onBackClick = onBackClick,
        onRepeatLoadingButtonClick = { viewModel.getAllMenuItem(topicId) },
    )
}

@Composable
private fun MenuScreen(
    screenStateProvider: () -> ScreenState,
    topBarTitleProvider: () -> String,
    onGameButtonClick: () -> Unit,
    onAddButtonClick: () -> Unit,
    onEditButtonClick: (Int) -> Unit,
    onMenuItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onRepeatLoadingButtonClick: () -> Unit,
) {
    when (screenStateProvider()) {
        is ScreenState.Error -> {
            ErrorScreen(
                onButtonClick = onRepeatLoadingButtonClick,
                modifier = modifier,
            )
        }
        is ScreenState.Loading -> {}
        is ScreenState.Success<*> -> {

        }
    }
}
