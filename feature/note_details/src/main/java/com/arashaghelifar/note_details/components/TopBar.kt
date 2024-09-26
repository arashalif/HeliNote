package com.arashaghelifar.note_details.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.arashaghelifar.note_details.R
import com.arashaghelifar.ui.theme.ColorButton
import com.arashaghelifar.ui.theme.ColorOnButton

@Composable
fun TopBar(
    modifier: Modifier,
    onBackedPres: () -> Unit,
    onReminderPressed: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Card(
            modifier = modifier.size(48.dp),
            onClick = onBackedPres,
            shape = CircleShape,
            colors = CardColors(
                Color.Transparent,ColorOnButton,Color.Transparent,Color.Transparent,
            ),
            border = BorderStroke(
                width = 1.dp,
                color = ColorButton
            ),
        ) {
            Icon(
                modifier = Modifier.fillMaxSize().padding(12.dp),
                painter = painterResource(id = R.drawable.svg_back),
                contentDescription = "back button"
            )
        }

        Card(
            modifier = modifier.size(48.dp),
            onClick = onReminderPressed,
            shape = CircleShape,
            colors = CardColors(
                Color.Transparent,ColorOnButton,Color.Transparent,Color.Transparent,
            ),
            border = BorderStroke(
                width = 1.dp,
                color = ColorButton
            ),
        ) {
            Icon(
                modifier = Modifier.fillMaxSize().padding(12.dp),
                painter = painterResource(id = R.drawable.svg_reminder),
                contentDescription = "back button"
            )
        }
    }

}
