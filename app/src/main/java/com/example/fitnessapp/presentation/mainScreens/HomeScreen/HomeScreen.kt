package com.example.fitnessapp.presentation.mainScreens.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessapp.data.dto.User
import com.example.fitnessapp.enums.DayState
import com.example.fitnessapp.helper.TimeHelper
import com.example.fitnessapp.presentation.components.UserImage
import com.example.fitnessapp.ui.theme.FitnessAppTheme
import com.example.fitnessapp.ui.theme.responsiveLayout

@Composable
fun HomeScreen(
    uiState: HomeUiState
) {

    Column(
        modifier = Modifier
            .padding(horizontal = MaterialTheme.responsiveLayout.screenWidthPadding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopBar()

        UserRow(
            user = uiState.user
        )

        WeeklyActivity()

        NextWorkoutButton(
            onClick = {

            }
        )
    }
}

@Composable
private fun TopBar(

) {
    Row(
        modifier = Modifier
            .padding(vertical = MaterialTheme.responsiveLayout.spacingMedium)
            .fillMaxWidth(),
    ) {
        Spacer(Modifier.weight(1f))

        BadgedBox(
            badge = {

            }
        ) {
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    modifier = Modifier
                        .size(MaterialTheme.responsiveLayout.iconLarge),
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = "Notifications",
                )
            }
        }
    }
}

@Composable
private fun UserRow(
    user: User,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        UserImage(
            modifier = Modifier,
            userImageUrl = user.imageUrl,
            size = MaterialTheme.responsiveLayout.imageLarge
        )

        Column(
            modifier = Modifier
                .padding(start = MaterialTheme.responsiveLayout.spacingLarge),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.responsiveLayout.spacingSmall)
        ) {
            Text(
                text = user.name,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = user.email,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Composable
private fun WeeklyActivity(

) {
    Text(
        modifier = Modifier
            .padding(vertical = MaterialTheme.responsiveLayout.spacingExtraLarge),
        text = "Weekly activity",
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.SemiBold
    )

    Row(
        modifier = Modifier
      //      .padding(top = MaterialTheme.responsiveLayout.spacingExtraLarge)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TimeHelper.getShortDaysOfWeek().forEach { dayName ->
            DayActivityCard(
                dayState = DayState.FINISHED_WORKOUT,
                dayName = dayName,
            )
        }
    }
}

@Composable
private fun DayActivityCard(
    dayState: DayState,
    dayName: String
) {
    val backgroundColor = when(dayState) {
        DayState.FINISHED_WORKOUT -> MaterialTheme.colorScheme.primary
        DayState.FUTURE_WORKOUT -> MaterialTheme.colorScheme.onSurface.copy(0.3f)
        DayState.NO_WORKOUT -> MaterialTheme.colorScheme.secondary.copy(0.3f)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.responsiveLayout.spacingMedium)
    ) {
        Box(
            modifier = Modifier
                .height(MaterialTheme.responsiveLayout.dailyActivityCardHeight)
                .clip(CircleShape)
                .background(backgroundColor)
                .padding(horizontal = MaterialTheme.responsiveLayout.spacingExtraSmall),
            contentAlignment = Alignment.Center,
        ) {
            when (dayState) {
                DayState.FINISHED_WORKOUT -> {
                    Icon(
                        modifier = Modifier
                            .size(MaterialTheme.responsiveLayout.iconLarge),
                        imageVector = Icons.Filled.Check,
                        contentDescription = null,
                        tint = Color.White
                    )
                }

                DayState.FUTURE_WORKOUT -> TODO()
                DayState.NO_WORKOUT -> TODO()
            }
        }

        Text(
            text = dayName,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
private fun NextWorkoutButton(
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Button(
        modifier = Modifier
            .padding(top = MaterialTheme.responsiveLayout.spacingExtraLarge)
            .fillMaxWidth()
            .height(MaterialTheme.responsiveLayout.buttonHeight),
        shape = MaterialTheme.responsiveLayout.cardShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        interactionSource = interactionSource,
        onClick = { onClick() }
    ) {
        Text(
            text = "Next workout",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
private fun HomeScreenPrev() {
    FitnessAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen(
                uiState = HomeUiState(
                    user = User(
                        name = "User Name",
                        email = "user.name@gmail.com"
                    )
                )
            )
        }
    }
}