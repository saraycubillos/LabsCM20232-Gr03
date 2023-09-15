package co.edu.udea.compumovil.gr03_20232.lab1.composable

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityDropdownField() {
    var expanded by remember { mutableStateOf(false) }
    var gender by remember {
        mutableStateOf("")
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { newValue ->
            expanded = newValue
        }
    ){
        TextField(
            value = gender,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            placeholder = {
                Text(text = "Please select your city")
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            DropdownMenuItem(
                text = {
                    Text(text = "Medellin")
                },
                onClick = {
                    gender = "Medellin"
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = {
                    Text(text = "Bogota")
                },
                onClick = {
                    gender = "Bogota"
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = {
                    Text(text = "Other")
                },
                onClick = {
                    gender = "Other"
                    expanded = false
                }
            )
        }
    }


}

