package com.agentdesks.android.crm.assignment.feature.entity

import androidx.annotation.DrawableRes
import com.agentdesks.android.crm.assignment.feature.R

sealed class Icon(@DrawableRes val drawableRes: Int) {
    object Apartment : Icon(R.drawable.apartment)
    object Boat : Icon(R.drawable.boat)
    object Condo : Icon(R.drawable.condo)
    object Garage : Icon(R.drawable.garage)
    object Garden : Icon(R.drawable.garden)
    object Land : Icon(R.drawable.land)
    object NoRoom : Icon(R.drawable.no_room)
    object Rooms : Icon(R.drawable.rooms)
    object Swimming : Icon(R.drawable.swimming)

    companion object {
        operator fun get(icon: String): Icon {
            return when (icon) {
                "apartment" -> Apartment
                "boat" -> Boat
                "condo" -> Condo
                "garage" -> Garage
                "garden" -> Garden
                "land" -> Land
                "no-room" -> NoRoom
                "rooms" -> Rooms
                "swimming" -> Swimming
                else -> throw Exception("Icon not found")
            }
        }
    }
}