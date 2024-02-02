package com.test.paypaytest.theme.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.test.paypaytest.theme.PayPayTheme

@Composable
fun AppTextFields(
    value: String,
    modifier: Modifier = Modifier.fillMaxWidth(),
    hint: String = "",
    @StringRes label: Int,
    onValueChanged: (value: String) -> Unit,
    isPasswordField: Boolean,
    isClick: Boolean = false,
    onClick: () -> Unit = {},
    @StringRes error: Int? = null,
    onDone: () -> Unit = {}

) {
    val focusManager = LocalFocusManager.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
    )
    {
        TextField(
            value = value,
            modifier = modifier,
            onValueChange = { onValueChanged(it) },
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
            label = { Text(text = stringResource(label)) }
        )
    }
}

@ThemePreview
@Composable
private fun AppTextViewPreview() {
    PayPayTheme {
        Surface {
            AppTextFields(
                value = "MyField",
                onValueChanged = {},
                label = 0,
                isPasswordField = false
            )
        }
    }
}
