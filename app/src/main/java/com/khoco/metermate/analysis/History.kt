package com.khoco.metermate.analysis

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.khoco.metermate.ui.theme.BlackTheme
import com.khoco.metermate.ui.theme.WhiteTheme
import com.khoco.metermate.ui.theme.YellowTheme

val months = listOf("January", "February", "March", "April")

@Composable
fun History() {
        Box(
            modifier = Modifier
                .background(color = WhiteTheme)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = "Bill History",
                    style = MaterialTheme.typography.titleMedium,
                    color = BlackTheme,
                )

                Spacer(modifier = Modifier.padding(8.dp))

                //Scrollable List of months
                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(months) { month ->
                        MonthCard(month = month)
                    }
                }
            }

        }
    }


@Composable
fun MonthCard(month: String, bill: Int = 0) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = YellowTheme,
                shape = RoundedCornerShape(12.dp),

                )
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = month,
                style = MaterialTheme.typography.bodyLarge,
                fontStyle = FontStyle.Italic,
                color = BlackTheme
            )
            Text(
                text = "Calculated Bill : $bill",
                style = MaterialTheme.typography.bodySmall,
                color = BlackTheme
            )
        }

    }
}
