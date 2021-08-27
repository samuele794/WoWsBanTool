package view.stream

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import com.arkivanov.decompose.ComponentContext
import navigation.Component
import navigation.NavHostComponent
import org.koin.core.component.KoinComponent

class StreamScreenComponent(
    private val componentContext: ComponentContext

) : Component, KoinComponent, ComponentContext by componentContext {

    @Composable
    override fun render() {
        StreamScreen()
    }
}