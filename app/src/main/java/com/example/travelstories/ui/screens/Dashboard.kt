package com.example.travelstories.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.travelstories.model.Holiday
import com.example.travelstories.model.HolidayType
import com.example.travelstories.ui.common.UrlImage
import com.example.travelstories.ui.theme.TravelStoriesTheme

@Composable
fun Dashboard(onHolidayClick: (Holiday) -> Unit) {
    Surface(color = MaterialTheme.colorScheme.background) {
        val holidays = listOf(
            Holiday("1", "My lovely trip", HolidayType.BUSINESS, "1 Dec 1980", "my holiday description goes here", "Chile"),
            Holiday("2","Kinda lame", HolidayType.SOLO, "15 Feb 2004", "my holiday description goes here", "Greece"),
            Holiday("3", "Loved the food", HolidayType.ROMANTIC, "20 Mar 1999", "my holiday description goes here", "Spain"),
            Holiday("4", "italy is great man", HolidayType.FAMILY, "02 Sep 2001", "my holiday description goes here", "Italy")
            )
        val searchResult = remember { mutableStateOf(holidays) }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(searchResult.value) { holiday ->
                HolidayListItem(holiday, onHolidayClick)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HolidayListItem(holiday: Holiday, onHolidayClick: (Holiday) -> Unit) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        onClick = {onHolidayClick(holiday)}
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ) {
            Column(modifier = Modifier
                .fillMaxWidth(0.6F)
                .requiredHeightIn(100.dp, 170.dp),


                verticalArrangement = Arrangement.SpaceBetween) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // todo: change color according to the holiday type
                    val borderColor = MaterialTheme.colorScheme.tertiary
                    Text(holiday.type.name,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .drawBehind {
                                val borderSize = 2.dp.toPx()
                                val y = size.height - borderSize / 2
                                drawLine(
                                    color = borderColor,
                                    start = Offset(0f, y),
                                    end = Offset(size.width / 2, y),
                                    strokeWidth = borderSize
                                )
                            }
                            .padding(0.dp, 5.dp)

                    )
                    Text(holiday.destination,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Text(holiday.name, color = MaterialTheme.colorScheme.onSurface)
                Text(holiday.date,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            UrlImage(
                url = "https://cdn.britannica.com/82/195482-050-2373E635/Amalfi-Italy.jpg",
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun HolidayListItemPreview() {
    TravelStoriesTheme {
        HolidayListItem(
            holiday = Holiday(
                "1",
                "name 1",
                HolidayType.BUSINESS,
                "01-01-1980",
                "my holiday description goes here",
                "Chile"
            )
        ) {}

    }
}

@Preview
@Composable
fun DashboardPreview() {
    TravelStoriesTheme {
        Dashboard {}
    }
}