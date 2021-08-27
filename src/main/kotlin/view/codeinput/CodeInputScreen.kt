package view.codeinput

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CodeInputScreen(
    codeTextValue: String,
    onTextChange: (String) -> Unit,
    buttonClickedListener: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            codeTextValue,
            onValueChange = onTextChange,
            singleLine = true,
            label = { Text("Codice Partita") }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = buttonClickedListener) {
            Text(text = "Ricerca partita")
        }
    }
}