package view.splash

import androidx.compose.runtime.*
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.delay
import navigation.Component
import navigation.NavHostComponent

class SplashScreenComponent(
    private val componentContext: ComponentContext,
    private val navHostComponent: NavHostComponent
): Component, ComponentContext by componentContext {


    @Composable
    override fun render() {
        SplashScreen()

        LaunchedEffect(Unit){
            navHostComponent.navigateToCodeInputScreen()
        }
    }
}