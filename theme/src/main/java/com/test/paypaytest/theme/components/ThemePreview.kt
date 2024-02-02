package com.test.paypaytest.theme.components

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview


@Preview(showBackground = true, name = "Day", uiMode = Configuration.UI_MODE_NIGHT_NO)

@Preview(showBackground = true, name = "Night", uiMode = Configuration.UI_MODE_NIGHT_YES)

annotation class ThemePreview()
