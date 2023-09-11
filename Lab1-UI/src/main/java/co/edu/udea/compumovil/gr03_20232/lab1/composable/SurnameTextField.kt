package co.edu.udea.compumovil.gr03_20232.lab1.composable

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr03_20232.lab1.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurnameTextField(
    context: Context,
    surname: TextFieldValue,
    onSurnameChanged: (TextFieldValue) -> Unit
) {
    val lastNameTextFieldHint = context.getString(R.string.surname_text_field_hint)
    TextField(
        value = surname,
        onValueChange = {
            onSurnameChanged(it)
        },
        label = {
            Text(lastNameTextFieldHint)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
        ),
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.primary,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
            capitalization = KeyboardCapitalization.Words,
            autoCorrect = false
        ),
        singleLine = true,
        leadingIcon = {
            Icon(Icons.Outlined.Person, contentDescription = lastNameTextFieldHint)
        }
    )
}