package theme

import androidx.compose.desktop.DesktopTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Font

val primary = Color(48, 163, 230)
val secondary = Color(24, 25, 29)

val primaryStream = Color(198, 40, 40)

private val fontFamily = FontFamily(Font("google_sans_regular.ttf"))

private val DarkColors = darkColors(
    primary = primary,
    secondary = secondary,
    surface = secondary,
    onPrimary = Color.White,
    onSecondary = Color.White
)

private val StreamColors = lightColors(
    primary = primaryStream,
    secondary = secondary,
    surface = Color.Green,
    onPrimary = Color.Black,
    onSecondary = Color.Black
)

@Composable
fun DecomposeDesktopTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = DarkColors,
        typography = Typography(
            defaultFontFamily = fontFamily
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            DesktopTheme {
                content()
            }
        }

    }
}

@Composable
fun StreamDesktopTheme(
    content: @Composable () -> Unit
){
    MaterialTheme(
        colors = StreamColors,
        typography = Typography(
            defaultFontFamily = fontFamily
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            DesktopTheme {
                content()
            }
        }

    }
}