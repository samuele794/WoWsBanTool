package view.managment.menu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.delay
import navigation.Component
import navigation.NavHostComponent
import view.splash.SplashScreen

class AdminMenuScreenComponent(
    private val componentContext: ComponentContext,
    private val navHostComponent: NavHostComponent
): Component, ComponentContext by componentContext {


    @Composable
    override fun render() {
        AdminMenuScreen(
            selectClanOnClickListener = {
                navHostComponent.navigateToClanSettings()
            },
            onBackPressed = {
                navHostComponent.onBackPressed()
            }
        )
    }
}