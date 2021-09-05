package view.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.arkivanov.decompose.ComponentContext
import data.WarshipRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import navigation.Component
import navigation.NavHostComponent
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SplashScreenComponent(
    private val componentContext: ComponentContext,
    private val navHostComponent: NavHostComponent
) : Component, KoinComponent, ComponentContext by componentContext {

    val warshipRepository by inject<WarshipRepository>()


    @Composable
    override fun render() {
        SplashScreen()

        LaunchedEffect(Unit) {
            launch(Dispatchers.IO) {
                supervisorScope {
                    fetchData()
//                   navHostComponent.navigateToCodeInputScreen()
                }
            }


        }
    }

    private suspend fun fetchData(): Unit = withContext(Dispatchers.IO) {
        kotlin.runCatching {
            warshipRepository.fetchShipsDataAndSave()
        }.onSuccess {
            navHostComponent.navigateToCodeInputScreen()
            return@withContext
        }.onFailure {
            fetchData()
        }
    }
}