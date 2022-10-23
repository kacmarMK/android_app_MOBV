package com.example.model

data class PubTags(val amenity: String = "", val name: String = "*meno baru neznáme*",
                   val opened_hours: String = "*neuvedené*", val phone: String = "*neuvedené*",
                   val website: String = "*neuvedené*") {
    override fun toString(): String {
        return "PubTags(amenity=$amenity, name=$name, opening_hours=$opened_hours, phone=$phone, website=$website)"
    }
}