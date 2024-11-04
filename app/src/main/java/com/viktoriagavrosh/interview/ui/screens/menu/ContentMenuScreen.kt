package com.viktoriagavrosh.interview.ui.screens.menu

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.viktoriagavrosh.interview.R
import com.viktoriagavrosh.interview.model.MenuItem
import com.viktoriagavrosh.interview.ui.elements.MenuItemCard

@Composable
fun ContentMenuScreen(
    listItemsProvider: () -> List<MenuItem>,
    onEditButtonClick: (Int) -> Unit,
    onMenuItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(listItemsProvider()) {
            MenuItemCard(
                item = it,
                onCardClick = onMenuItemClick,
                onButtonClick = onEditButtonClick,
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium)),
            )
        }
    }
}
