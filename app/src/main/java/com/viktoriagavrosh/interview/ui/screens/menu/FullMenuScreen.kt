package com.viktoriagavrosh.interview.ui.screens.menu

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.interview.R
import com.viktoriagavrosh.interview.model.MenuItem
import com.viktoriagavrosh.interview.ui.elements.InterviewTopBar
import com.viktoriagavrosh.interview.ui.theme.InterviewTheme

/**
 * Composable to display FullMenuScreen content with topBar
 *
 * @param listItemsProvider provides list items for menu
 * @param topBarTitleProvider provides title for the topBar
 * @param onGameButtonClick callback that is executed when game button is clicked
 * @param onAddButtonClick callback that is executed when add button is clicked
 * @param onEditButtonClick callback that is executed when edit button is clicked
 * @param onMenuItemClick callback that is executed when menu item is clicked
 * @param onBackClick callback that is executed when back button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullMenuScreen(
    listItemsProvider: () -> List<MenuItem>,
    topBarTitleProvider: () -> String,
    onGameButtonClick: () -> Unit,
    onAddButtonClick: () -> Unit,
    onEditButtonClick: (Int) -> Unit,
    onMenuItemClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)

    val title = topBarTitleProvider()

    Scaffold(
        modifier = modifier,
        topBar = {
            InterviewTopBar(
                title = title.ifEmpty { stringResource(R.string.topbar_title) },
                scrollBehavior = scrollBehavior,
                onBackClick = onBackClick,
                isBackButtonShow = title.isNotEmpty(),
                isGameButtonShow = true,
                onGameButtonClick = onGameButtonClick,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddButtonClick,
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_add),
                    contentDescription = stringResource(R.string.add),
                )
            }
        }
    ) { paddingValues ->
        ContentMenuScreen(
            listItemsProvider = listItemsProvider,
            onMenuItemClick = onMenuItemClick,
            onEditButtonClick = onEditButtonClick,
            modifier = Modifier.padding(paddingValues),
        )
    }
}

@Preview
@Composable
private fun FullMenuScreenPreview() {
    InterviewTheme {
        FullMenuScreen(
            listItemsProvider = {
                List(3) {
                    MenuItem(
                        id = it,
                        text = "Question"
                    )
                }
            },
            topBarTitleProvider = { "TopBar title" },
            onGameButtonClick = {},
            onMenuItemClick = {},
            onBackClick = {},
            onAddButtonClick = {},
            onEditButtonClick = {},
        )
    }
}
