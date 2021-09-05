package view.stream.manage

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import com.arkivanov.decompose.ComponentContext
import data.WarshipRepository
import data.model.Warship
import navigation.Component
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class StreamManageScreenComponent(
    private val componentContext: ComponentContext
) : Component, KoinComponent, ComponentContext by componentContext {

    private var showAlert by mutableStateOf(false)
    private val warshipRepository by inject<WarshipRepository>()

    val banRow = arrayOfNulls<BanRow>(7).apply {
        fill(BanRow())
    }.filterNotNull().toMutableStateList()

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    override fun render() {

        StreamManageScreen(
            banList = banRow,
            onSelectShipClicked = {
                showAlert = true
            },
            shipList = warshipRepository.shipList
        )
    }

    private data class BanListData(val banList: List<BanRow>)

    data class BanRow(var leftShip: Warship? = null, var rightShip: Warship? = null)
}