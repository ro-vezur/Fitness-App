package com.example.fitnessapp.data.dto.workout

data class Exercise(
    val name: String,
    val sets: List<ExerciseSet>,
    val order: Int,
    )
