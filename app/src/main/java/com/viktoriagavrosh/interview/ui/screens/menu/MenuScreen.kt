package com.viktoriagavrosh.interview.ui.screens.menu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.viktoriagavrosh.interview.model.MenuItem
import com.viktoriagavrosh.interview.ui.screens.ScreenState
import com.viktoriagavrosh.interview.ui.screens.error.ErrorScreen

/**
 * Composable to display MenuScreen
 *
 * @param onGameButtonClick callback that is executed when game button is clicked
 * @param onAddButtonClick callback that is executed when add button is clicked
 * @param onEditButtonClick callback that is executed when edit button is clicked
 * @param onMenuItemClick callback that is executed when menu item is clicked
 * @param modifier the modifier to be applied to this layout node
 * @param topicId id of topic of questions
 * @param onBackClick callback that is executed when back button is clicked
 */
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
    val viewModel: MenuViewModel = hiltViewModel(
        creationCallback = { factory: MenuViewModel.MenuViewModelFactory ->
            factory.create(topicId = topicId)
        }
    )

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
        onBackClick = onBackClick,
        onRepeatLoadingButtonClick = { viewModel.getAllMenuItem(topicId) },
        modifier = modifier,
    )
}

/**
 * Composable to display MenuScreen
 *
 * @param screenStateProvider provides state of screen
 * @param topBarTitleProvider provides title for the topBar
 * @param onGameButtonClick callback that is executed when game button is clicked
 * @param onAddButtonClick callback that is executed when add button is clicked
 * @param onEditButtonClick callback that is executed when edit button is clicked
 * @param onMenuItemClick callback that is executed when menu item is clicked
 * @param onBackClick callback that is executed when back button is clicked
 * @param onRepeatLoadingButtonClick callback that is executed when error screen button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
private fun MenuScreen(
    screenStateProvider: () -> ScreenState<List<MenuItem>>,
    topBarTitleProvider: () -> String,
    onGameButtonClick: () -> Unit,
    onAddButtonClick: () -> Unit,
    onEditButtonClick: (Int) -> Unit,
    onMenuItemClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    onRepeatLoadingButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (val screenState: ScreenState<List<MenuItem>> = screenStateProvider()) {
        is ScreenState.Error -> {
            ErrorScreen(
                onButtonClick = onRepeatLoadingButtonClick,
                modifier = modifier,
            )
        }

        is ScreenState.Loading -> {}
        is ScreenState.Success -> {
            FullMenuScreen(
                listItemsProvider = { screenState.data },
                topBarTitleProvider = topBarTitleProvider,
                onGameButtonClick = onGameButtonClick,
                onAddButtonClick = onAddButtonClick,
                onEditButtonClick = onEditButtonClick,
                onMenuItemClick = onMenuItemClick,
                onBackClick = onBackClick,
                modifier = modifier,
            )
        }
    }
}
