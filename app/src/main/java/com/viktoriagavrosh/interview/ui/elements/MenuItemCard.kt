package com.viktoriagavrosh.interview.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.interview.R
import com.viktoriagavrosh.interview.model.MenuItem
import com.viktoriagavrosh.interview.ui.theme.InterviewTheme

@Composable
fun MenuItemCard(
    item: MenuItem,
    onCardClick: (Int) -> Unit,
    onButtonClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.clickable { onCardClick(item.id) },
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = item.text,
                style = MaterialTheme.typography.bodyLarge,

                )
            IconButton(
                onClick = { onButtonClick(item.id) },
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_edit),
                    contentDescription = stringResource(R.string.edit),
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.icon_size))
                        .padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }
}

@Preview
@Composable
private fun MenuItemCardPreview() {
    InterviewTheme {
        MenuItemCard(
            item = MenuItem(text = "Text"),
            onCardClick = {},
            onButtonClick = {},
        )
    }
}
