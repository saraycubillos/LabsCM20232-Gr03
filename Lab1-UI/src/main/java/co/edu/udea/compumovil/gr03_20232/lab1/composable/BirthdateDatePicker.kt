package co.edu.udea.compumovil.gr03_20232.lab1.composable

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr03_20232.lab1.R
import java.util.Calendar
import java.util.Date

@Composable
fun BirthdateDatePicker(
    context: Context,
    birthdate: MutableState<String>
) {
    val birthdateTitle = context.getString(R.string.birth_date_title)
    val selectString = context.getString(R.string.select)
    val calendar = Calendar.getInstance()
    val birthdateYear = calendar.get(Calendar.YEAR)
    val birthdateMonth = calendar.get(Calendar.MONTH)
    val birthdateDay = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            birthdate.value = "$dayOfMonth/${month + 1}/$year"
        },
        birthdateYear,
        birthdateMonth,
        birthdateDay
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { datePickerDialog.show() },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.DateRange, contentDescription = birthdateTitle)
                Spacer(modifier = Modifier.width(4.dp)) // Espaciado entre el icono y el texto
                Text(
                    text = birthdateTitle,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                )
            }

            Text(
                text = birthdate.value,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                textDecoration = TextDecoration.Underline,
            )
        }

        Button(
            onClick = {
                datePickerDialog.show()
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp) // Espaciado entre el texto y el botón
        ) {
            Text(selectString)
        }
    }
}