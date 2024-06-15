package com.yazantarifi.legora.android.composables


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LegoraTextPickerBottomSheetComposable(
    preSelectedIndex: Int = -1,
    updateKey: String,
    title: Int,
    options: ArrayList<String>,
    sheetState: SheetState,
    onDismissListener: (Int, String, String) -> Unit,
) {
    val selectedIndex = remember { mutableIntStateOf(preSelectedIndex) }
    ModalBottomSheet(
        onDismissRequest = { onDismissListener(selectedIndex.intValue, updateKey, options.getOrNull(selectedIndex.intValue) ?: "") },
        sheetState = sheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(if (updateKey == "server") 0.7f else 0.5f)
                .nestedScroll(rememberNestedScrollInteropConnection())
        ) { contentPadding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(contentPadding)
                    .padding(start = 15.dp, end = 15.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(id = title), color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.fillMaxWidth(), fontWeight = FontWeight.Bold)
                LazyColumn(modifier = Modifier
                    .fillMaxWidth()
                ) {
                    itemsIndexed(options) { index, item ->
                        val modifier = if (selectedIndex.intValue == index) {
                            Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(MaterialTheme.colorScheme.outline)
                                .border(
                                    1.dp,
                                    MaterialTheme.colorScheme.primaryContainer,
                                    RoundedCornerShape(10.dp)
                                )
                                .clickable { selectedIndex.intValue = index }
                        } else {
                            Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(MaterialTheme.colorScheme.outline)
                                .clickable { selectedIndex.intValue = index }
                        }

                        Spacer(modifier = Modifier.height(5.dp))
                        Column(verticalArrangement = Arrangement.Center, modifier = modifier) {
                            Text(text = options[index], modifier = Modifier
                                .fillMaxWidth()
                                .height(20.dp), color =  MaterialTheme.colorScheme.onPrimary, textAlign = TextAlign.Center, minLines = 1, maxLines = 1)
                        }
                    }
                }
            }
        }
    }
}