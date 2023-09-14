package co.edu.udea.compumovil.gr03_20232.lab1.composable

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr03_20232.lab1.R

@Composable
fun NextButtonCenterEnd (
    context: Context,
    onClickFunction: () -> Unit
) {
    val nextString = context.getString(R.string.next)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Button(onClick = {
            onClickFunction()
        }
        ) {
            Text(text = nextString)
        }
    }
}