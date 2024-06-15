package com.yazantarifi.legora.android.composables


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun LegoraTextInputComposable(
    text: MutableState<String>,
    label: String,
    placeholder: String,
    isLastInput: Boolean,
    isSingleLine: Boolean,
    maxLines: Int,
    onChangeValue: (String) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val modifier = if (isFocused) {
        Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.outline)
            .border(1.dp, MaterialTheme.colorScheme.tertiary, RoundedCornerShape(10.dp))
    } else {
        Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.outline)
    }

    TextField(
        label = {
            Text(text = label, style = TextStyle(color = MaterialTheme.colorScheme.onSecondary))
        },
        placeholder = {
            Text(text = placeholder, style = TextStyle(color = MaterialTheme.colorScheme.onSecondary))
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
            focusedIndicatorColor = MaterialTheme.colorScheme.outline,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
            disabledIndicatorColor = MaterialTheme.colorScheme.outline,
            disabledTextColor = MaterialTheme.colorScheme.onPrimary,
            cursorColor = MaterialTheme.colorScheme.tertiary,
            focusedContainerColor = MaterialTheme.colorScheme.outline,
            disabledContainerColor = MaterialTheme.colorScheme.outline,
            unfocusedContainerColor = MaterialTheme.colorScheme.outline,
            focusedSupportingTextColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedSupportingTextColor = MaterialTheme.colorScheme.onPrimary,
            selectionColors = TextSelectionColors(MaterialTheme.colorScheme.tertiary, MaterialTheme.colorScheme.onSecondary),
        ),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text, imeAction = if (isLastInput) ImeAction.Done else ImeAction.Next),
        value = text.value,
        onValueChange = {
            text.value = it
            onChangeValue(it)
        },
        modifier = modifier,
        singleLine = isSingleLine,
        maxLines = maxLines,
        minLines = maxLines,
        textStyle = TextStyle(fontWeight = FontWeight.Bold),
        interactionSource = interactionSource,
    )

    Spacer(modifier = Modifier.height(10.dp))

}