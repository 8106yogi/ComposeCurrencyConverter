package com.test.currencyconverter.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import com.test.currencyconverter.theme.AppTheme
import com.test.currencyconverter.theme.R.string
import com.test.currencyconverter.theme.components.AppTextFields
import com.test.currencyconverter.theme.components.ThemePreview

@ThemePreview
@Composable
fun MainScreen(items: List<String>) {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            AppTextFields(
                label = string.enter_currency,
                hint = "yourname@domain.com",
                isPasswordField = false,
                onValueChanged = {},
                value = "2222",
                modifier = Modifier.fillMaxWidth(),
            )
            Demo_ExposedDropdownMenuBox(items)
        }
    }
}

@Composable
fun Demo_ExposedDropdownMenuBox(items: List<String>) {
    val context = LocalContext.current
    //   val coffeeDrinks = arrayOf("Americano", "Cappuccino", "Espresso", "Latte", "Mocha")
    var expanded by remember { mutableStateOf(false) }

    var selectedText by remember {
        mutableStateOf("Select Currency")
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End

    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .wrapContentWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            expanded = false
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}
