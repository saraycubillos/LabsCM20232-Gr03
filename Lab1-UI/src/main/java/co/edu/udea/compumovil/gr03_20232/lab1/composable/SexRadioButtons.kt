package co.edu.udea.compumovil.gr03_20232.lab1.composable

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr03_20232.lab1.R

@Composable
fun SexRadioButtons(context: Context, selectedSexOption: MutableState<String>) {
    val sexTitle = context.getString(R.string.sex_title_radiobutton)
    val maleString = context.getString(R.string.male_sex)
    val femaleString = context.getString(R.string.female_sex)
    val otherString = context.getString(R.string.other)
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Filled.Favorite, contentDescription = sexTitle)
        SexRadioButton(selectedSexOption, maleString)
        SexRadioButton(selectedSexOption, femaleString)
        SexRadioButton(selectedSexOption, otherString)
    }
}

@Composable
fun SexRadioButton(selectedSexOption: MutableState<String>, sexOption: String) {
    Row (
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selectedSexOption.value == sexOption,
            onClick = { selectedSexOption.value = sexOption }
        )
        Text(sexOption)
    }
}