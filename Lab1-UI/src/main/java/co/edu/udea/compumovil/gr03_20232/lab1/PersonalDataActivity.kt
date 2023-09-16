package co.edu.udea.compumovil.gr03_20232.lab1

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr03_20232.lab1.composable.BirthdateDatePicker
import co.edu.udea.compumovil.gr03_20232.lab1.composable.FullNameText
import co.edu.udea.compumovil.gr03_20232.lab1.composable.SurnameTextField
import co.edu.udea.compumovil.gr03_20232.lab1.composable.NameTextField
import co.edu.udea.compumovil.gr03_20232.lab1.composable.NextButtonCenterEnd
import co.edu.udea.compumovil.gr03_20232.lab1.composable.SchoolGradeSpinner
import co.edu.udea.compumovil.gr03_20232.lab1.composable.SexRadioButtons
import co.edu.udea.compumovil.gr03_20232.lab1.composable.TitleText
import co.edu.udea.compumovil.gr03_20232.lab1.ui.theme.Labs20232Gr03Theme
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.text.StringBuilder

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
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            PersonalDataFormPortrait(context, personalDataFormTitle)
        }
        else -> {
            PersonalDataFormLandscape(context, personalDataFormTitle)
        }
    }

}

@Composable
fun PersonalDataFormLandscape(context: Context, personalDataFormTitle: String) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(25.dp),
    ) {

        TitleText(personalDataFormTitle)
        FullNameText(context)
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            NameTextField(context, 0.5F, name) {
                    newName -> name = newName
            }
            SurnameTextField(context, 1F, surname) {
                    newSurname -> surname = newSurname
            }
        }
        SexRadioButtons(context, selectedSexOption)
        BirthdateDatePicker(context, birthDate)
        SchoolGradeSpinner(context, selectedSchoolGradeOption) {
                newSelectedSchoolGradeOption -> selectedSchoolGradeOption = newSelectedSchoolGradeOption
        }
        //Spacer(modifier = Modifier.height(10.dp))
        NextButtonCenterEnd(
            context = context,
            onClickFunction = {
                personalDataFormNextButtonOnClick(context)
            }
        )
    }
}

@Composable
fun PersonalDataFormPortrait(context: Context, personalDataFormTitle: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(2.dp),
        verticalArrangement = Arrangement.spacedBy(35.dp),
    ) {
        TitleText(personalDataFormTitle)
        Column (
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FullNameText(context)
            NameTextField(context, 1F, name) {
                    newName -> name = newName

            }
            SurnameTextField(context, 1F, surname) {
                    newSurname -> surname = newSurname
            }
        }
        SexRadioButtons(context, selectedSexOption)
        BirthdateDatePicker(context, birthDate)
        SchoolGradeSpinner(context, selectedSchoolGradeOption) {
                newSelectedSchoolGradeOption -> selectedSchoolGradeOption = newSelectedSchoolGradeOption
        }
        //Spacer(modifier = Modifier.height(10.dp))
        NextButtonCenterEnd(
            context = context,
            onClickFunction = {
                personalDataFormNextButtonOnClick(context)
            }
        )
    }
}

fun personalDataFormNextButtonOnClick(context: Context) {
    if (isDataValid(context)) {
        trimNameAndSurname()
        logcatAllData()
        val contactDataActivityIntent = Intent(context, ContactDataActivity::class.java)
        context.startActivity(contactDataActivityIntent)
    }
}

fun logcatAllData() {
    val logAllDataMessage = StringBuilder()
    logAllDataMessage.append("Información personal: \n")
    logAllDataMessage.append("Nombre completo: ${name.text} ${surname.text} \n")
    logAllDataMessage.append("Fecha de nacimiento: ${birthDate.value} \n")
    if (selectedSexOption.value.isNotEmpty()) {
        logAllDataMessage.append("Sexo: ${selectedSexOption.value} \n")
    }
    if (selectedSchoolGradeOption.isNotEmpty()) {
        logAllDataMessage.append("Grado escolaridad: $selectedSchoolGradeOption \n")
    }
    Log.i("PersonalDataActivity", logAllDataMessage.toString())
}

fun trimNameAndSurname() {
    name = name.copy(text = name.text.trim())
    surname = surname.copy(text = surname.text.trim())
}

fun isDataValid(context: Context) : Boolean {
    val toastMessage = StringBuilder()
    if (!nameIsValid()) {
        toastMessage.append(context.getString(R.string.invalid_name_toast_message))
        toastMessage.append(". ")
    }
    if (!surnameIsValid()) {
        toastMessage.append(context.getString(R.string.invalid_surname_toast_message))
        toastMessage.append(". ")
    }
    if (!birthDateIsValid()) {
        toastMessage.append(context.getString(R.string.invalid_birthdate_toast_message))
        toastMessage.append(". ")
    }
    if (toastMessage.isNotEmpty()) {
        Toast.makeText(context, toastMessage.toString(), Toast.LENGTH_SHORT).show()
        return false
    }
    return true
}

fun nameIsValid(): Boolean{
    if (name.text.isNotEmpty()) {
        return name.text.contains(Regex("[a-zA-Z]"))
    }
    return false
}

fun surnameIsValid(): Boolean {
    if (surname.text.isNotEmpty()) {
        return surname.text.contains(Regex("[a-zA-Z]"))
    }
    return false
}

fun birthDateIsValid() : Boolean {
    if (birthDate.value.isNotEmpty()) {
        val currentDate = Date()
        val localDateOfBirthDate = SimpleDateFormat("dd/MM/yyyy").parse(birthDate.value)
        if (localDateOfBirthDate != null) {
            if (localDateOfBirthDate.after(currentDate)) {
                return false
            }
        }
        return true
    }
    return false
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    val context = LocalContext.current
    val personalDataFormTitle = context.getString(R.string.personal_data_form_title)
    Labs20232Gr03Theme {
        PersonalDataFormPortrait(context, personalDataFormTitle)
    }
}