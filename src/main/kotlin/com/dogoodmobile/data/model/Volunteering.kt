package com.dogoodmobile.data.model

data class Volunteering(
    val id: Int,
    val type: VolunteeringType,
    val title: String,
    val detail: String,
    val ownerName: String,
    val location: Location,
    val tags: List<String>,
    val day: String,
    val month: String,
    val ownerMailAddress: String = ""
)
