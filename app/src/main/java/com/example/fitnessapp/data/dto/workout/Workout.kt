package com.example.fitnessapp.data.dto.workout

data class Workout(
    val name: String = "",
    val exercises: List<Exercise> = listOf(),
    val date: Long = 0,
) {
    companion object {
        val workoutFakeData = Workout(
            exercises = listOf(
                Exercise(
                    name = "Bench Press",
                    sets = listOf(
                        ExerciseSet(reps = 12, weight = 20),
                        ExerciseSet(reps = 12, weight = 25),
                        ExerciseSet(reps = 12, weight = 30),
                        ExerciseSet(reps = 12, weight = 35),
                    ),
                    order = 1
                ),
                Exercise(
                    name = "Dumbbell fly",
                    sets = listOf(
                        ExerciseSet(reps = 12, weight = 20),
                        ExerciseSet(reps = 12, weight = 25),
                        ExerciseSet(reps = 12, weight = 30),
                        ExerciseSet(reps = 12, weight = 35),
                    ),
                    order = 2
                )
            ),
            date = System.currentTimeMillis()
        )
    }
}
