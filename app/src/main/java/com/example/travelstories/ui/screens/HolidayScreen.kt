package com.example.travelstories.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.travelstories.ui.common.UrlImage
import com.example.travelstories.ui.theme.TravelStoriesTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun HolidayScreen() {
    Surface(color = MaterialTheme.colorScheme.background) {
        Column(
            modifier =
            Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            UrlImage(
                url = "https://cdn.britannica.com/82/195482-050-2373E635/Amalfi-Italy.jpg",
                modifier = Modifier
                    .padding(0.dp, 10.dp)
                    .fillMaxWidth()
            )

            Text(
                text = "Created on 11.05.2009",
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray,
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 10.dp)
            )

            Divider()

            Text(
                text = "Holiday title", //todo: read data from the passed id parameter
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(15.dp, 5.dp)

            )

            Divider()
            DayPicker()

            
        }
    }
}

@Composable
fun DayPicker() {
    val dates: List<LocalDate> = listOf(
        LocalDate.of(2020, 1, 10),
        LocalDate.of(2020, 1, 11),
        LocalDate.of(2020, 1, 12),
        LocalDate.of(2020, 1, 13),
        LocalDate.of(2020, 1, 14),
        LocalDate.of(2020, 1, 15),
        LocalDate.of(2020, 1, 16),
    )

    val dateSelected = remember { mutableStateOf(0) }
    val onClickNextDay: () -> Unit = {
        if (dateSelected.value != dates.size - 1) {
            dateSelected.value = dateSelected.value + 1
        }
    }

    val onClickPrevDay: () -> Unit = {
        if (dateSelected.value != 0) {
            dateSelected.value = dateSelected.value - 1
        }
    }

    DayPickerHeader(dates[dateSelected.value], onClickNextDay, onClickPrevDay)
    DaysCarousel(dates[dateSelected.value], dates) { date ->
        dateSelected.value = dates.indexOf(date)
    }
}

@Composable
fun DaysCarousel(daySelected: LocalDate, dates: List<LocalDate>, onClickDay: (LocalDate) -> Unit) {
    // todo: scroll when date is not viewable

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(dates) { date ->
            DayCard(date = date, date == daySelected, onClickDay)
        }
    }
}

@Composable
fun DayPickerHeader(daySelected: LocalDate, onClickNextDay: () -> Unit, onClickPrevDay: () -> Unit) {
    Row(modifier = Modifier
        .padding(0.dp, 10.dp, 0.dp, 0.dp)){
        Text(
            text = daySelected.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
        IconButton(onClick = { onClickPrevDay() }) {
            Icon(
                imageVector = Icons.Filled.ChevronLeft,
                contentDescription = "Previous day"
            )
        }

        IconButton(onClick = { onClickNextDay() }) {
            Icon(
                imageVector = Icons.Filled.ChevronRight,
                contentDescription = "Next day"
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DayCard(date: LocalDate, isSelected: Boolean, onClickDay: (LocalDate) -> Unit) {
    Card(
        border = BorderStroke(2.dp, if (isSelected) Color.Magenta else Color.Gray),
        onClick = { if (!isSelected) onClickDay(date)}
    ) {
        Column(
            modifier = Modifier
                .width(40.dp)
                .height(48.dp)
                .padding(4.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = date.format(DateTimeFormatter.ofPattern("eee")),
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = date.dayOfMonth.toString(),
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview
@Composable
fun PreviewHoliday() {
    TravelStoriesTheme {
        HolidayScreen()
    }
}