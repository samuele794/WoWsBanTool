package navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.push
import com.arkivanov.decompose.router
import com.arkivanov.essenty.parcelable.Parcelable
import view.codeinput.CodeInputScreenComponent
import view.splash.SplashScreenComponent

class NavHostComponent(
    componentContext: ComponentContext,
    windowState: WindowState
) : Component, ComponentContext by componentContext {

    private val router = router<ScreenConfig, Component>(
        initialConfiguration = ScreenConfig.Splash,
        childFactory = ::createScreenComponent
    )

    private fun createScreenComponent(
        screenConfig: ScreenConfig,
        componentContext: ComponentContext
    ): Component {
        return when (screenConfig) {
            is ScreenConfig.Splash -> SplashScreenComponent(
                componentContext,
                this
            )

            is ScreenConfig.CodeInputScreen -> CodeInputScreenComponent(
                componentContext,
                this
            )
        }
    }

    @Composable
    override fun render() {
        Children(routerState = router.state){
            it.instance.render()
        }
    }

    fun navigateToCodeInputScreen(){
        router.push(ScreenConfig.CodeInputScreen)
    }

    private sealed class ScreenConfig : Parcelable {
        object Splash : ScreenConfig()
        object CodeInputScreen : ScreenConfig()
    }
}