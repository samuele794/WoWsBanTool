package view.stream

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.dp

@Composable
fun StreamScreen() {
    // https://fvilarino.medium.com/creating-a-viewpager-in-jetpack-compose-332d6a9181a5

    val list = mutableListOf(
        "Ciao",
        "Ciao",
        "Ciao",
        "Ciao",
        "Ciao",
        "Ciao",
    )


    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        itemsIndexed(list) { index, item ->
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(0.5F, true)
                        .background(Color.White)
                ) {
                    Image(
                        bitmap = useResource("takaoSmall.png", ::loadImageBitmap),
                        "logo",
                        modifier = Modifier.width(256.dp).height(256.dp)
                    )

                    Column(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("$item $index")
                    }
                }

                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(0.5F, true)
                        .background(Color.White),
                ) {
                    Image(
                        bitmap = useResource("takaoSmall.png", ::loadImageBitmap),
                        "logo",
                        modifier = Modifier.width(256.dp).height(256.dp)
                    )

                    Column(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {


                        Text("$item $index", Modifier.align(Alignment.CenterHorizontally))
                    }
                }
            }

        }
    }
}