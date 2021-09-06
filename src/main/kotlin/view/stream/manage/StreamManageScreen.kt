package view.stream.manage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeBitmap
import androidx.compose.ui.unit.dp
import data.model.Warship
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL
import javax.imageio.ImageIO

//https://foso.github.io/Jetpack-Compose-Playground/material/surface/

@Composable
fun StreamManageScreen(
    banList: MutableList<StreamManageScreenComponent.BanRow>,
    shipList: MutableMap<String, Warship>
) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        itemsIndexed(banList) { index, itemRow ->

            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = Modifier
                        .weight(0.5F, true),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    if (itemRow.leftShip != null) {
                        ShipShower(itemRow.leftShip!!)
                    } else {
                        ShipFilterTextField(
                            shipList = shipList,
                            onShipSelected = {
                                banList[index] = itemRow.copy(leftShip = it)
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                Card(
                    modifier = Modifier
                        .weight(0.5F, true),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        if (itemRow.rightShip != null) {
                            ShipShower(itemRow.rightShip!!)
                        } else {
                            ShipFilterTextField(
                                shipList = shipList,
                                onShipSelected = {
                                    banList[index] = itemRow.copy(rightShip = it)
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ShipShower(
    warship: Warship
) {
    Row(modifier = Modifier.fillMaxHeight()) {
        val imageShip = remember { mutableStateOf<ImageBitmap?>(null) }
        if (imageShip.value != null) {
            Image(imageShip.value!!, contentDescription = null, Modifier.padding(16.dp))
        } else {
            LaunchedEffect(Unit) {
                withContext(Dispatchers.IO) {
                    imageShip.value = ImageIO.read(URL(warship.imageUrl)).toComposeBitmap()
                }
            }
        }

        Text(warship.name.toString(), Modifier.align(Alignment.CenterVertically))
    }
}

@Composable
fun ShipFilterTextField(shipList: MutableMap<String, Warship>, onShipSelected: (Warship) -> Unit) {
    var text by remember { mutableStateOf("") }
    Column {
        OutlinedTextField(
            text,
            onValueChange = { text = it },
            label = { Text("Nome Nave") },
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
        )
        if (text.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            ) {
                items(shipList.values.filter {
                    it.name?.contains(text, ignoreCase = true) ?: false
                }) { itemShip ->
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        TextButton(onClick = {
                            onShipSelected.invoke(itemShip)
                        }, modifier = Modifier.fillMaxWidth()) {
                            Text(itemShip.name.toString())
                        }

                    }
                }
            }
        }
    }
}