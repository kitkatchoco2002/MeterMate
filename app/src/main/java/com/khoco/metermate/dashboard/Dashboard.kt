@file:OptIn(ExperimentalMaterial3Api::class)

package com.khoco.metermate.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.khoco.metermate.ui.theme.Typography

@Composable
fun DashboardCard1() {
    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary
    val tertiaryColor = MaterialTheme.colorScheme.tertiary
    val backgroundColor = MaterialTheme.colorScheme.background
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    Box(
        modifier = Modifier
            .padding(all = 12.dp)
            .shadow(
                elevation = 8.dp, // <-- shadow size
                shape = RoundedCornerShape(12.dp),
                clip = true,// <-- clips content to shape (required for shadow to follow corners)

            )
            .background(color = tertiaryColor, shape = RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
    ) {
        Column(
            modifier = Modifier.padding(all = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                //Header
                Column {
                    Text(
                        text = "Recent Reading",
                        style = Typography.titleMedium,
                        color = primaryColor
                    )
                    Text(
                        text = "Last Updated: april 1",
                        style = Typography.titleMedium,
                        color = primaryColor
                    )
                }

                IconButton(onClick = {/*ToDo: navigate to calculate page*/ }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = primaryColor
                    )
                }
            }
            Spacer(modifier = Modifier.height(screenHeight * 0.05f))

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
                    Text(
                        text = "2458 kWh", style = Typography.titleLarge, color = primaryColor
                    )
                    Text(
                        text = "Electricity", style = Typography.titleMedium, color = primaryColor
                    )
                }

                Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
                    Text(
                        text = "₱1,100", style = Typography.titleLarge, color = primaryColor
                    )
                    Text(
                        text = "Bill",
                        style = Typography.titleMedium,
                        textAlign = TextAlign.Left,
                        color = primaryColor
                    )
                }
            }
        }
    }
}


@Composable
fun DashboardCard2(modifier: Modifier = Modifier) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary
    val tertiaryColor = MaterialTheme.colorScheme.tertiary
    val backgroundColor = MaterialTheme.colorScheme.background
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Box(
        modifier = modifier
            .padding(all = 6.dp)
            .shadow(
                elevation = 8.dp, // <-- shadow size
                shape = RoundedCornerShape(12.dp), clip = true
            )
            .background(color = primaryColor, shape = RoundedCornerShape(12.dp))
            .aspectRatio(3f / 4f)
    ) {
        Column {
            Text(text = "REMINDER")
        }
    }
}

@Composable
fun DashboardCard3(modifier: Modifier = Modifier) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary
    val tertiaryColor = MaterialTheme.colorScheme.tertiary
    val backgroundColor = MaterialTheme.colorScheme.background
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Box(
        modifier = modifier
            .padding(all = 6.dp)
            .shadow(
                elevation = 8.dp, // <-- shadow size
                shape = RoundedCornerShape(12.dp), clip = true
            )
            .background(color = primaryColor, shape = RoundedCornerShape(12.dp))
            .aspectRatio(3f / 4f)
    ) {
        Column {
            Text(text = "ALERT")
        }
    }
}

@Composable
fun DashboardCard4() {
    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary
    val tertiaryColor = MaterialTheme.colorScheme.tertiary
    val backgroundColor = MaterialTheme.colorScheme.background
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Box(
        modifier = Modifier
            .padding(all = 6.dp)
            .shadow(
                elevation = 8.dp, // <-- shadow size
                shape = RoundedCornerShape(12.dp), clip = true
            )
            .background(color = primaryColor, shape = RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .height(screenHeight * 0.3f)
    ) {
        Column(modifier = Modifier.padding(all = 12.dp)) {
            Text(text = "Quick Analysis")
        }
    }
}


@Composable
fun Dashboard() {
    val backgroundColor = MaterialTheme.colorScheme.background
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize() // Add this to constrain height!
            .padding(horizontal = 16.dp)
            .verticalScroll(scrollState)
            .background(backgroundColor)
    ) {
        DashboardCard1()

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            DashboardCard2(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(8.dp)) // Optional spacing
            DashboardCard3(modifier = Modifier.weight(1f))
        }

        DashboardCard4()
    }
}
