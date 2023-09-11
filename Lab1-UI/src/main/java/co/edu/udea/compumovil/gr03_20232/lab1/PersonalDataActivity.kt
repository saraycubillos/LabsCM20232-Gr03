package co.edu.udea.compumovil.gr03_20232.lab1

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr03_20232.lab1.composable.BirthdateDatePicker
import co.edu.udea.compumovil.gr03_20232.lab1.composable.SurnameTextField
import co.edu.udea.compumovil.gr03_20232.lab1.composable.NameTextField
import co.edu.udea.compumovil.gr03_20232.lab1.composable.SchoolGradeSpinner
import co.edu.udea.compumovil.gr03_20232.lab1.composable.SexRadioButtons
import co.edu.udea.compumovil.gr03_20232.lab1.composable.TitleText
import co.edu.udea.compumovil.gr03_20232.lab1.ui.theme.Labs20232Gr03Theme

class PersonalDataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Labs20232Gr03Theme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    PersonalDataForm()
                }
            }
        }
    }
}

var name by mutableStateOf(TextFieldValue(""))
var surname by mutableStateOf(TextFieldValue(""))
var selectedSexOption = mutableStateOf("")
var birthDate = mutableStateOf("")
var selectedSchoolGradeOption by mutableStateOf("")

@Composable
fun PersonalDataForm() {
    val context = LocalContext.current
    val personalDataFormTitle = context.getString(R.string.personal_data_form_title)
    val nextString = context.getString(R.string.next)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(35.dp),
    ) {
        TitleText(personalDataFormTitle)
        Column {
            NameTextField(context, name) {
                    newName -> name = newName
            }
            Spacer(modifier = Modifier.height(10.dp))
            SurnameTextField(context, surname) {
                    newSurname -> surname = newSurname
            }
        }
        SexRadioButtons(context, selectedSexOption)
        BirthdateDatePicker(context, birthDate)
        SchoolGradeSpinner(context, selectedSchoolGradeOption) {
            newSelectedSchoolGradeOption -> selectedSchoolGradeOption = newSelectedSchoolGradeOption
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Button(onClick = {
               personalDataFormNextButtonOnClick(context)
            }
            ) {
                Text(text = nextString)
            }
        }
    }
}

fun personalDataFormNextButtonOnClick(context: Context) {
    isDataValid(context)
    trimNameAndSurname()
    toastAllData(context)
}

fun toastAllData(context: Context) {
    val allDataToastMessage = "${name.text} ${surname.text} ${selectedSexOption.value} ${birthDate.value} $selectedSchoolGradeOption"
    Toast.makeText(context, allDataToastMessage, Toast.LENGTH_SHORT).show()
}

fun trimNameAndSurname() {
    name = name.copy(text = name.text.trim())
    surname = surname.copy(text = surname.text.trim())
}

fun isDataValid(context: Context) {
    nameIsValid(context)
    surnameIsValid(context)
    birthDateIsValid(context)
}

fun nameIsValid(context: Context){
    if (name.text.isEmpty()) {
        val invalidNameToastMessage = context.getString(R.string.invalid_name_toast_message)
        Toast.makeText(context, invalidNameToastMessage, Toast.LENGTH_SHORT).show()
    }
}

fun surnameIsValid(context: Context) {
    if (surname.text.isEmpty()) {
        val invalidSurnameToastMessage = context.getString(R.string.invalid_surname_toast_message)
        Toast.makeText(context, invalidSurnameToastMessage, Toast.LENGTH_SHORT).show()
    }
}

fun birthDateIsValid(context: Context) {
    if (birthDate.value.isEmpty()) {
        val invalidBirthDateToastMessage = context.getString(R.string.invalid_birthdate_toast_message)
        Toast.makeText(context, invalidBirthDateToastMessage, Toast.LENGTH_SHORT).show()
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    val context = LocalContext.current
    Labs20232Gr03Theme {
        BirthdateDatePicker(context, birthDate)
    }
}