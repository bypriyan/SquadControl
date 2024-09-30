package com.bypriyan.ostiamare.model

data class PlayerAttendance(
    val player: ModelPlayer,
    val presentCount: Int,
    val absentCount: Int
)
