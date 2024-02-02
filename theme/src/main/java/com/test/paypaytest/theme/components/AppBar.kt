package com.test.paypaytest.theme.components

import android.content.res.Configuration
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.test.paypaytest.theme.PayPayTheme


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
    PayPayTheme {
        Surface {
            Appbar(title = "Test")
        }
    }

}