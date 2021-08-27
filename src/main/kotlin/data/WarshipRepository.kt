package data

import data.model.Warship
import de.floribe2000.warships_java.direct.api.ApiBuilder
import de.floribe2000.warships_java.direct.api.typeDefinitions.Language
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region
import de.floribe2000.warships_java.direct.encyclopedia.WarshipsRequest
import it.github.samuele___.BanTool.WOWS_API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class WarshipRepository {
    private val shipsContainer = mutableMapOf<String, Warship>()

    init {
        ApiBuilder.Companion.createInstance(WOWS_API_KEY)
    }

    suspend fun fetchShipsDataAndSave(): Boolean = withContext(Dispatchers.IO) {
        val warshipsData = WarshipsRequest
            .createRequest()
            .region(Region.EU)
            .language(Language.ENGLISH)
            .fetch()

        warshipsData.data.values.filter {
            !it.isSpecial
        }.map {
            Warship(
                it.shipId.toString(),
                it.name,
                it.isPremium,
                it.images!!.large!!,
                it.tier!!,
                it.type!!,
                it.nation
            )
        }.forEach {
            shipsContainer[it.shipId] = it
        }


        if (warshipsData.meta.page <= warshipsData.meta.page_total) {
            coroutineScope {
                fetchShipsDataAndSave()
            }
        } else {
            true
        }

    }
}