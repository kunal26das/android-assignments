package io.github.kunal26das.kisan_network.contact

import java.io.Serializable

data class Contact(
    val displayName: String,
    val phoneNumber: String,
    val countryCode: String,
) : Serializable {

    val number
        get() = "${countryCode}${phoneNumber}"

    companion object {
        const val KEY_CONTACT = "contact"
    }
}
