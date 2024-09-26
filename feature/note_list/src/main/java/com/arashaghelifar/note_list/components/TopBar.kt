package com.arashaghelifar.note_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arashaghelifar.note_list.ListType
import com.arashaghelifar.note_list.R
import com.arashaghelifar.note_list.toggle
import com.arashaghelifar.ui.theme.ColorSecondaryVariant20


@Composable
internal fun TopBar(
    searchQuery : String,
    onSearchChanged: (String)->Unit,
    listType: ListType,
    onListTypeChange: (ListType) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.svg_person),
            contentDescription = "User Avatar",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )

        VerticalDivider(
            thickness = 1.dp,
            color = ColorSecondaryVariant20,
            modifier = Modifier
                .height(14.dp)
                .padding(horizontal = 8.dp)
        )

        BasicTextField(
            value = searchQuery,
            onValueChange = onSearchChanged,
            modifier = Modifier.weight(1f),
            textStyle = androidx.compose.ui.text.TextStyle(
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
            ),
            decorationBox = { innerTextField ->
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.svg_search),
                        contentDescription = "Search Icon"
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    if (searchQuery.isEmpty()) {
                        Text(text = "Search your notes", color = Color.Gray)
                    } else {
                        innerTextField()
                    }
                }
            }
        )

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            modifier = Modifier.clickable {
                onListTypeChange(listType.toggle())
            },
            painter = painterResource(
                id = when (listType) {
                    ListType.GridNotes -> R.drawable.svg_row
                    ListType.ListNotes -> R.drawable.svg_grid
                }
            ),
            contentDescription = "list type"
        )
    }
}
