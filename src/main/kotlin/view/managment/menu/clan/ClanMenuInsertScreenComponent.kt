package view.managment.menu.clan

import androidx.compose.runtime.*
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.window.AwtWindow
import com.arkivanov.decompose.ComponentContext
import navigation.Component
import navigation.NavHostComponent
import java.awt.FileDialog
import java.awt.Frame
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class ClanMenuInsertScreenComponent(
    private val componentContext: ComponentContext,
    private val navHostComponent: NavHostComponent
) : Component, ComponentContext by componentContext {

    private var clanState by mutableStateOf(ClanModel())
    private var showLoadIcon by mutableStateOf(false)
    private var errorImageUploadText by mutableStateOf<String?>(null)

    @Composable
    override fun render() {
        var widthSize by remember { mutableStateOf(IntSize.Zero) }

        ClanMenuInsertScreen(
            onBackPressed = {
                navHostComponent.onBackPressed()
            },
            clanTextValue = clanState.clanName,
            onClanTextChange = {
                clanState = clanState.copy(
                    clanName = it
                )
            },
            loadIconButtonClicked = {
                showLoadIcon = true
            },
            clanLoadImage = clanState.clanIcon,
            onUploadClanViewSizeChange = {
                widthSize = it
            },
            viewImageClanSize = widthSize,
            errorImageUploadText = errorImageUploadText
        )

        if (showLoadIcon) {
            FileDialog {
                showLoadIcon = false

                if (it != null) {
                    clanState = if (it.length().div(1024).div(1024) <= 8) {
                        val image = ImageIO.read(it)
                        errorImageUploadText = null

                        clanState.copy(clanIcon = image)
                    } else {
                        errorImageUploadText = "Immagine troppo pesante, MASSIMO 1MB"
                        clanState.copy(clanIcon = null)
                    }

                }
            }
        }
    }


    private data class ClanModel(
        val clanName: String = "",
        val clanTag: String = "",
        val clanIcon: BufferedImage? = null
    )
}

@Composable
private fun FileDialog(
    parent: Frame? = null,
    onCloseRequest: (result: File?) -> Unit
) = AwtWindow(
    create = {
        object : FileDialog(parent, "Choose a file", LOAD) {
            override fun setVisible(value: Boolean) {
                super.setVisible(value)
                if (value) {
                    onCloseRequest(files.firstOrNull())
                }
            }


        }.apply {
            file = arrayOf("png", "jpg", "jpeg").joinToString(";") { "*$it" }
            setFilenameFilter { _, name ->
                "*.png".any {
                    name.endsWith(it)
                }
            }
        }
    },
    dispose = FileDialog::dispose
)