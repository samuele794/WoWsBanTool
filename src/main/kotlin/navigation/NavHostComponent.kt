package navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.WindowState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.pop
import com.arkivanov.decompose.push
import com.arkivanov.decompose.router
import com.arkivanov.essenty.parcelable.Parcelable
import view.codeinput.CodeInputScreenComponent
import view.managment.menu.AdminMenuScreenComponent
import view.managment.menu.clan.ClanMenuInsertScreenComponent
import view.splash.SplashScreenComponent
import view.stream.manage.StreamManageScreenComponent

class NavHostComponent(
    componentContext: ComponentContext,
    windowState: WindowState
) : Component,  ComponentContext by componentContext {

    private val router = router<ScreenConfig, Component>(
        initialConfiguration = ScreenConfig.Splash,
        childFactory = ::createScreenComponent,
    )

    fun onBackPressed(){
        router.pop()
    }

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

            is ScreenConfig.AdminMenuScreen -> AdminMenuScreenComponent(
                componentContext,
                this
            )

            is ScreenConfig.AdminMenuClanScreen -> ClanMenuInsertScreenComponent(
                componentContext,
                this
            )

            is ScreenConfig.StreamManageScreen -> StreamManageScreenComponent(
                componentContext
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


    fun navigateToAdminScreen() {
        router.push(ScreenConfig.AdminMenuScreen)
    }

    fun navigateToClanSettings() {
        router.push(ScreenConfig.AdminMenuClanScreen)
    }

    fun navigateToStreamManage() {
        router.push(ScreenConfig.StreamManageScreen)
    }

    private sealed class ScreenConfig : Parcelable {
        object Splash : ScreenConfig()
        object CodeInputScreen : ScreenConfig()

        object AdminMenuScreen : ScreenConfig()
        object AdminMenuClanScreen : ScreenConfig()

        object StreamManageScreen : ScreenConfig()
    }
}