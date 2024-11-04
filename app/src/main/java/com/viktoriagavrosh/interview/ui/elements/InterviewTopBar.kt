package com.viktoriagavrosh.interview.ui.elements

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.interview.R
import com.viktoriagavrosh.interview.ui.theme.InterviewTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InterviewTopBar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    isBackButtonShow: Boolean = true,
    isGameButtonShow: Boolean = false,
    onGameButtonClick: () -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        },
        modifier = modifier,
        navigationIcon = {
            if (isBackButtonShow) {
                TopBarButton(
                    iconId = R.drawable.ic_back,
                    contentDescriptionId = R.string.back,
                    onClick = onBackClick,
                )
            }
        },
        actions = {
            if (isGameButtonShow) {
                TopBarButton(
                    iconId = R.drawable.ic_game,
                    contentDescriptionId = R.string.game,
                    onClick = onGameButtonClick,
                )
            }
        },
        scrollBehavior = scrollBehavior,
    )
}

/**
 * Composable to display back button
 *
 * @param onBackClick callback that is executed when back icon is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
private fun TopBarButton(
    @DrawableRes iconId: Int,
    @StringRes contentDescriptionId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = { onClick() },
        modifier = modifier,
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = stringResource(contentDescriptionId),
            modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size))
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun InterviewTopBarPreview() {
    InterviewTheme {
        InterviewTopBar(
            title = "Title",
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
            onBackClick = {},
        )
    }
}
