package view.stream.manage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.model.Warship

//https://foso.github.io/Jetpack-Compose-Playground/material/surface/

@Composable
fun StreamManageScreen(
    banList: MutableList<StreamManageScreenComponent.BanRow>,
    onSelectShipClicked: (StreamManageScreenComponent.BanRow) -> Unit,
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
                        .background(Color.White)
                        .padding(16.dp)
                        .weight(0.5F, true)

                ) {
                    if (itemRow.leftShip != null) {

                    } else {
                        Button(onClick = {
                            onSelectShipClicked.invoke(itemRow)
                        }) {
                            Text("Seleziona Nave")
                        }
                    }
                }

                Card(
                    modifier = Modifier
                        .background(Color.White)
                        .weight(0.5F, true)
                        .padding(16.dp)
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        if (itemRow.rightShip != null) {

                            Text("test")
                        } else {
                            var text by remember { mutableStateOf("") }
                            OutlinedTextField(
                                text,
                                onValueChange = { text = it },
                                label = { Text("Nome Nave") },
                                modifier = Modifier.fillMaxWidth()
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
                                                banList[index] = itemRow.copy(rightShip = itemShip)
                                            }, modifier = Modifier.fillMaxWidth()) {
                                                Text(itemShip.name.toString())
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }


            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}