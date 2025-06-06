package com.example.fitnessapp.presentation.mainScreens.NewWorkoutScreen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessapp.presentation.components.buttons.TurnBackButton
import com.example.fitnessapp.ui.theme.FitnessAppTheme
import com.example.fitnessapp.ui.theme.responsiveLayout
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import com.example.fitnessapp.data.dto.workout.Exercise
import com.example.fitnessapp.data.dto.workout.Workout
import com.example.fitnessapp.helper.TimeHelper


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutsScreen(
    uiState: WorkoutsUiState,
    executeEvent: (WorkoutsViewModelEvent) -> Unit,
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = TimeHelper.getCurrentDayTimeInMillis()
    )
    var showDatePicker by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(datePickerState.selectedDateMillis) {
        executeEvent(WorkoutsViewModelEvent.SetWorkout(datePickerState.selectedDateMillis ?: 0))
    }

    Box (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.responsiveLayout.screenWidthPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // TopBar( turnBack = {} )

            WorkoutDatePicker(
                selectedDateMillis = datePickerState.selectedDateMillis ?: 0,
                changeDatePickerVisibility = { showDatePicker = !showDatePicker },
                selectYesterday = {
                    datePickerState.selectedDateMillis = TimeHelper.getYesterdayInMillis(datePickerState.selectedDateMillis ?: 0)
                },
                selectTomorrowDay = {
                    datePickerState.selectedDateMillis = TimeHelper.getTomorrowDayInMillis(datePickerState.selectedDateMillis ?: 0)
                }
            )

            ExercisesList(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                selectedWorkout = uiState.selectedWorkout,
            )

            AddNewWorkoutButton(
                onClick = {

                }
            )

            CopyWorkoutButton(
                onClick = {

                }
            )
        }

        AnimatedVisibility(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            visible = showDatePicker
        ) {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    }
}

@Composable
private fun TopBar(
    turnBack: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(vertical = MaterialTheme.responsiveLayout.spacingMedium)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TurnBackButton(
            modifier = Modifier,
            onClick = { turnBack() }
        )

        Text(
            modifier = Modifier
                .padding(start = MaterialTheme.responsiveLayout.spacingSmall),
            text = "Back",
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun WorkoutDatePicker(
    selectedDateMillis: Long,
    changeDatePickerVisibility: () -> Unit,
    selectYesterday: () -> Unit,
    selectTomorrowDay: () -> Unit,
) {
    val selectedDateText = if(TimeHelper.isToday(selectedDateMillis)) "Today"
        else TimeHelper.getDateFromMillis(selectedDateMillis, TimeHelper.DateFormats.MMM_D_YYYY)

    Row(
        modifier = Modifier
            .padding(top = MaterialTheme.responsiveLayout.spacingExtraLarge)
            .fillMaxWidth()
            .clip(MaterialTheme.responsiveLayout.cardShape)
            .height(MaterialTheme.responsiveLayout.datePickerHeight)
            .background(MaterialTheme.colorScheme.primaryContainer),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        IconButton(
            onClick = {
                selectYesterday()
            },
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBackIosNew,
                contentDescription = null
            )
        }

        TextButton(
            onClick = {
                changeDatePickerVisibility()
            },
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.onSurface,
                containerColor = Color.Transparent
            )
        ) {
            AnimatedContent(
                targetState = selectedDateText,
                transitionSpec = {
                    fadeIn().togetherWith(fadeOut())
                },
            ) { animatedText ->
                Text(
                    text = animatedText,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

        IconButton(
            onClick = {
                selectTomorrowDay()
            },
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowForwardIos,
                contentDescription = null
            )
        }
    }
}

@Composable
private fun ExercisesList(
    modifier: Modifier,
    selectedWorkout: Workout?
) {
    val sortedExercises = remember(selectedWorkout?.exercises) {
        selectedWorkout?.exercises?.sortedBy { it.order } ?: listOf()
    }

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.responsiveLayout.spacingMedium)
    ) {
        item {}

        items(
            items = sortedExercises
        ) { exercise ->
            ExerciseCard(
                exercise = exercise
            )
        }

        item {}
    }
}

@Composable
private fun ExerciseCard(
    exercise: Exercise
) {
    Row(
        modifier = Modifier
            .clip(MaterialTheme.responsiveLayout.cardShape)
            .background(MaterialTheme.colorScheme.surface)
            .padding(
                top = MaterialTheme.responsiveLayout.spacingMedium,
                bottom = MaterialTheme.responsiveLayout.spacingMedium,
                start = MaterialTheme.responsiveLayout.spacingLarge,
                end = MaterialTheme.responsiveLayout.spacingSmall
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.responsiveLayout.spacingMedium)
        ) {
            Text(
                text = exercise.name,
                style = MaterialTheme.typography.titleLarge,
            )

            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.responsiveLayout.spacingLarge)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.responsiveLayout.spacingSmall)
                ) {
                    Text(
                        text = "Reps:",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Normal
                    )

                    Text(
                        text = "Weight:",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Normal
                    )
                }


                exercise.sets.forEach { set ->
                    Column(
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.responsiveLayout.spacingSmall)
                    ) {
                        Text(
                            text = set.reps.toString(),
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Text(
                            text = set.weight.toString(),
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }

        IconButton(
            onClick = {}
        ) {
            Icon(
                modifier = Modifier
                    .size(MaterialTheme.responsiveLayout.iconSmall),
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "more vert"
            )
        }
    }
}

@Composable
private fun AddNewWorkoutButton(
    onClick: () -> Unit,
) {

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.responsiveLayout.buttonHeight),
        shape = MaterialTheme.responsiveLayout.cardShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        onClick = onClick
    ) {
        Text(
            text = "Create New",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun CopyWorkoutButton(
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier
            .padding(
                top = MaterialTheme.responsiveLayout.spacingLarge,
                bottom = MaterialTheme.responsiveLayout.spacingExtraLarge
            )
            .fillMaxWidth()
            .height(MaterialTheme.responsiveLayout.buttonHeight),
        shape = MaterialTheme.responsiveLayout.cardShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        onClick = {
            onClick
        }
    ) {
        Text(
            text = "Copy previous",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
private fun AddNewWorkoutScreenPreview() {
    FitnessAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            WorkoutsScreen(
                uiState = WorkoutsUiState(),
                executeEvent = {}
            )
        }
    }
}