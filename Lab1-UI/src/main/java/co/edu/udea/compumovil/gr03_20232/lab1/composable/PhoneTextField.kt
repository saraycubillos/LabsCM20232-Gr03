package co.edu.udea.compumovil.gr03_20232.lab1.composable

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
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
fun PhoneTextField(
    context: Context,
    widthFraction: Float,
    phone: TextFieldValue,
    onPhoneChanged: (TextFieldValue) -> Unit) {
    val phoneTextFieldHint = context.getString(R.string.phone_text_field_hint)
    TextField(
        value = phone,
        onValueChange = {
            onPhoneChanged(it)
        },
        label = {
            Text(phoneTextFieldHint)
        },
        modifier = Modifier
            .fillMaxWidth(widthFraction)
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
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Next,
            autoCorrect = false
        ),
        singleLine = true,
        leadingIcon = {
            Icon(Icons.Filled.Phone, contentDescription = phoneTextFieldHint)
        }
    )
}