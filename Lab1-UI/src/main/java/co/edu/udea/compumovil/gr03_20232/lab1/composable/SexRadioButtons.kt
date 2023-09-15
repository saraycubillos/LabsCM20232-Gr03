package co.edu.udea.compumovil.gr03_20232.lab1.composable

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr03_20232.lab1.R

@Composable
fun SexRadioButtons(
    context: Context,
    selectedSexOption: MutableState<String>
) {
    val sexTitle = context.getString(R.string.sex_title_radiobutton)
    val maleString = context.getString(R.string.male_sex)
    val femaleString = context.getString(R.string.female_sex)
    val otherString = context.getString(R.string.other)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Favorite, // Cambia esto por el icono que desees
            contentDescription = null, // Agrega una descripción adecuada
            modifier = Modifier.size(24.dp) // Ajusta el tamaño del icono
        )
        Text(
            text = sexTitle,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SexRadioButton(selectedSexOption, maleString)
            SexRadioButton(selectedSexOption, femaleString)
            SexRadioButton(selectedSexOption, otherString)
        }
    }
}

@Composable
fun SexRadioButton(selectedSexOption: MutableState<String>, sexOption: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selectedSexOption.value == sexOption,
            onClick = { selectedSexOption.value = sexOption }
        )
        Text(sexOption)
    }
}