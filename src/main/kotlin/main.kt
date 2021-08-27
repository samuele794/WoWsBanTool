import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import data.AwsDynamoRepository
import data.WarshipRepository
import di.viewModelModule
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import navigation.NavHostComponent
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import theme.DecomposeDesktopTheme

fun main() {
    //https://github.com/Fmaldonado6/jetbrains-compose-anime-demo

    startKoin {
        printLogger(Level.DEBUG)
        modules(viewModelModule)
    }

    application {
        val windowState = rememberWindowState()
        val lifecycle = LifecycleRegistry()
        val navigationComponent = NavHostComponent(DefaultComponentContext(lifecycle), windowState)

        LifecycleController(lifecycle, windowState)

        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = "BanTool"
        ) {
            DecomposeDesktopTheme {
                navigationComponent.render()
            }
        }


    }
}