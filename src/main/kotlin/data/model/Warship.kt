package data.model

import de.floribe2000.warships_java.direct.api.typeDefinitions.Nation
import de.floribe2000.warships_java.direct.api.typeDefinitions.ShipType
import de.floribe2000.warships_java.direct.api.typeDefinitions.Tier

data class Warship(
    val shipId: String,
    val name: String?,
    val isPremium: Boolean,
    val image: String,
    val tier: Tier,
    val type: ShipType,
    val nation: Nation?
)