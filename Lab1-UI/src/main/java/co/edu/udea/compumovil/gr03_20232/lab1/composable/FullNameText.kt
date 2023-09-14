package co.edu.udea.compumovil.gr03_20232.lab1.composable

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import co.edu.udea.compumovil.gr03_20232.lab1.R

@Composable
fun FullNameText(context: Context) {
    val fullNameString = context.getString(R.string.full_name)
    Text(
        text = fullNameString,
        fontSize = MaterialTheme.typography.titleMedium.fontSize,
        fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
    )
}