package data

import data.model.Warship
import de.floribe2000.warships_java.direct.api.ApiBuilder
import de.floribe2000.warships_java.direct.api.typeDefinitions.Language
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region
import de.floribe2000.warships_java.direct.encyclopedia.WarshipsRequest
import it.github.samuele___.BanTool.WOWS_API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class WarshipRepository {
    private val shipsContainer = mutableMapOf<String, Warship>()
    val shipList: MutableMap<String, Warship> = shipsContainer

    init {
        ApiBuilder.Companion.createInstance(WOWS_API_KEY)
    }

    suspend fun fetchShipsDataAndSave(page: Int = 1): Boolean = withContext(Dispatchers.IO) {
        val warshipsData = WarshipsRequest
            .createRequest()
            .pageNo(page)
            .region(Region.EU)
            .language(Language.ENGLISH)
            .fetch()

//        val clan = ClansRequest
//            .createRequest()
//            .search("RAPAX")
//            .fetch()


        warshipsData.data.values.map {
            Warship(
                shipId = it.shipId.toString(),
                name = it.name,
                isPremium = it.isPremium,
                imageUrl = it.images!!.small!!,
                tier = it.tier!!,
                type = it.type!!,
                nation = it.nation,
                special = it.isSpecial,
                description = it.description
            )
        }.forEach {
            shipsContainer[it.shipId] = it
        }


        if (warshipsData.meta.page < warshipsData.meta.page_total) {
            coroutineScope {
                delay(200)
                fetchShipsDataAndSave(warshipsData.meta.page + 1)
            }
        } else {
            return@withContext true
        }

    }
}