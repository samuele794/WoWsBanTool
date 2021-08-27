package view.codeinput

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.arkivanov.essenty.lifecycle.subscribe
import navigation.Component
import navigation.NavHostComponent
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import theme.StreamDesktopTheme
import view.stream.StreamScreenComponent

class CodeInputScreenComponent(
    private val componentContext: ComponentContext,
    private val navHostComponent: NavHostComponent
) : Component, KoinComponent, ComponentContext by componentContext {

    private var state by mutableStateOf(CodeModel())
    private val codeInputViewModel by inject<CodeInputViewModel>()
    private var showStream by mutableStateOf(false)

    @Composable
    override fun render() {
        CodeInputScreen(
            state.code,
            onTextChange = {
                state = state.copy(code = it)
            }
        ) {
            showStream = true
        }

        if (showStream){
            // TODO : DA SPOSTARE
            Window(onCloseRequest = {
                showStream = false
            }){
                StreamDesktopTheme {
                    StreamScreenComponent(componentContext).render()
                }
            }
        }
    }

    private data class CodeModel(val code: String = "")
}