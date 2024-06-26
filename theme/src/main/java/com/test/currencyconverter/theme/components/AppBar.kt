package com.test.currencyconverter.theme.components

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.test.currencyconverter.theme.AppTheme


@Composable
fun Appbar(title: String) {
    TopAppBar(
        title = { Text(text = title) },
        colors = TopAppBarDefaults.topAppBarColors(),
    )
}

@ThemePreview
@Composable
private fun AppBarPreview() {
    AppTheme {
        Surface {
            Appbar(title = "Test")
        }
    }

}