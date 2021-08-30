package view.managment.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AdminMenuScreen(
    selectClanOnClickListener: () -> Unit,
    onBackPressed: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            navigationIcon = {
                IconButton(
                    onClick = {
                        onBackPressed.invoke()
                    }
                ) {
                    Image(Icons.Filled
                        .ArrowBack.apply { }, "Torna indietro")
                }
            }

        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = selectClanOnClickListener, content = {
                Text("Impostazioni clan")
            })
        }
    }




}