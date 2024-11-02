package com.viktoriagavrosh.interview.ui.screens.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.interview.R
import com.viktoriagavrosh.interview.ui.theme.InterviewTheme

@Composable
fun ErrorScreen(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.something_went_wrong),
            style = MaterialTheme.typography.displayMedium,
        )
        Button(
            onClick = onButtonClick,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_medium))
        ) {
            Text(
                text = "Try again",
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
            )
        }
    }
}

@Preview
@Composable
private fun ErrorScreenPreview() {
    InterviewTheme {
        ErrorScreen(
            onButtonClick = {}
        )
    }
}
