package com.arashaghelifar.note_details.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arashaghelifar.note_details.ReminderOption
import com.arashaghelifar.ui.theme.ColorSecondaryVariant20

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderOptionsBottomSheet(
    options: List<ReminderOption>,
    onDismiss: () -> Unit,
    onClicked: (ReminderOption) -> Unit
) {

    val sheetState = rememberModalBottomSheetState()


    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {

            options.forEachIndexed { i, option ->
                ReminderOption(
                    option = option,
                    onClicked = onClicked
                )

                if (i != options.lastIndex) {
                    Spacer(modifier = Modifier.height(16.dp))
                } else {
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = ColorSecondaryVariant20,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

            }
        }
    }

}

@Composable
fun ReminderOption(
    option: ReminderOption,
    onClicked: (ReminderOption) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClicked(option) }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = option.icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
        )


        VerticalDivider(
            thickness = 1.dp,
            color = ColorSecondaryVariant20,
            modifier = Modifier
                .height(8.dp)
                .padding(horizontal = 8.dp)
        )

        Text(
            text = option.title,
            fontSize = 12.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )

        if (option.timeFormated != null) {
            Text(text = option.timeFormated, fontSize = 12.sp, color = Color.Black)
        }

        if (option.actionIcon != null) {
            Icon(
                painter = painterResource(id = option.actionIcon),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
