package co.edu.udea.compumovil.gr03_20232.lab1.composable

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr03_20232.lab1.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchoolGradeSpinner(
    context: Context,
    selectedSchoolGradeOption: String,
    onSelectedSchoolGradeOptionChange: (String) -> Unit
) {
    val schoolGradeOptions = listOf(
        context.getString(R.string.school_grade_primary),
        context.getString(R.string.school_grade_secondary),
        context.getString(R.string.school_grade_university),
        context.getString(R.string.other)
    )
    val schoolGradeTitle = context.getString(R.string.school_grade_title)
    var expandedState by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), // Ajusta el espaciado según tus preferencias
        contentAlignment = Alignment.Center
    ) {
        ExposedDropdownMenuBox(
            expanded = expandedState,
            onExpandedChange = {
                expandedState = !expandedState
            },
            modifier = Modifier
                .fillMaxWidth(0.7f) // Ancho del 70%
        ) {
            TextField(
                readOnly = true,
                value = selectedSchoolGradeOption,
                onValueChange = { },
                label = { Text(schoolGradeTitle) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expandedState
                    )
                },
                leadingIcon = {
                    Icon(Icons.Filled.List, contentDescription = schoolGradeTitle)
                },
                modifier = Modifier
                    .height(60.dp)
                    .menuAnchor(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                ),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                ),
            )
            ExposedDropdownMenu(
                expanded = expandedState,
                onDismissRequest = {
                    expandedState = false
                }
            ) {
                schoolGradeOptions.forEach { schoolGradeOption ->
                    DropdownMenuItem(
                        text = { Text(schoolGradeOption) },
                        onClick = {
                            onSelectedSchoolGradeOptionChange(schoolGradeOption)
                            expandedState = false
                        }
                    )
                }
            }
        }
    }
}