package view.managment.menu.clan

import androidx.annotation.ColorRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toComposeBitmap
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import java.awt.image.BufferedImage

@Composable
fun ClanMenuInsertScreen(
    onBackPressed: () -> Unit,
    clanTextValue: String,
    onClanTextChange: (String) -> Unit,
    loadIconButtonClicked: () -> Unit,
    clanLoadImage: BufferedImage?,
    onUploadClanViewSizeChange: (IntSize) -> Unit,
    viewImageClanSize: IntSize,
    errorImageUploadText: String?
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            navigationIcon = {
                IconButton(
                    onClick = {
                        onBackPressed.invoke()
                    }
                ) {
                    Image(Icons.Filled.ArrowBack, "Torna indietro")
                }
            }
        )

        Row(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .onSizeChanged {
                        onUploadClanViewSizeChange.invoke(it)
                    }
                    .weight(0.5F, true)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                OutlinedTextField(
                    clanTextValue,
                    onValueChange = onClanTextChange,
                    singleLine = true,
                    label = { Text("Nome Clan") },
                    modifier = Modifier.padding(16.dp)
                )

                OutlinedTextField(
                    clanTextValue,
                    onValueChange = onClanTextChange,
                    singleLine = true,
                    label = { Text("[TAG Clan]") },
                    modifier = Modifier.padding(16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = loadIconButtonClicked) {
                    Text("Carica icona del clan")
                }

                if (errorImageUploadText != null){
                    Text(errorImageUploadText, modifier = Modifier.padding(16.dp), color = Color.Red)
                }

                Spacer(modifier = Modifier.height(16.dp))
                if (clanLoadImage != null) {
                    Image(
                        clanLoadImage.toComposeBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .width(viewImageClanSize.width.dp)
                    )
                }
            }

            Column(
                modifier = Modifier.weight(0.5F, true),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Testo di prova")
            }
        }


    }
}