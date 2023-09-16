package co.edu.udea.compumovil.gr03_20232.lab1

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.lifecycle.MutableLiveData
import co.edu.udea.compumovil.gr03_20232.lab1.api.CitiesResponse
import co.edu.udea.compumovil.gr03_20232.lab1.composable.AddressTextField
import co.edu.udea.compumovil.gr03_20232.lab1.composable.CityAutoComplete
import co.edu.udea.compumovil.gr03_20232.lab1.composable.CountryAutoComplete
import co.edu.udea.compumovil.gr03_20232.lab1.composable.EmailTextField
import co.edu.udea.compumovil.gr03_20232.lab1.composable.NextButtonCenterEnd
import co.edu.udea.compumovil.gr03_20232.lab1.composable.PhoneTextField
import co.edu.udea.compumovil.gr03_20232.lab1.composable.TitleText
import co.edu.udea.compumovil.gr03_20232.lab1.ui.theme.Labs20232Gr03Theme

class ContactDataActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Labs20232Gr03Theme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    ContactDataForm();
                }
            }
        }
    }
}

var phone by mutableStateOf(TextFieldValue(""))
var email by mutableStateOf(TextFieldValue(""))
var address by mutableStateOf(TextFieldValue(""))
var country = mutableStateOf("")
var city = mutableStateOf("")
val cities = MutableLiveData<CitiesResponse>()
val countries = mutableSetOf<String>()

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun ContactDataForm() {
    val context = LocalContext.current
    val personalDataFormTitle = context.getString(R.string.contact_data_form_title)
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            ContactDataFormLandscape(context, personalDataFormTitle)
        }
    }

}

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun ContactDataFormLandscape(context: Context, title: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        TitleText(title);
        Column (
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            PhoneTextField(context,1F, phone) {
                    newPhone -> phone = newPhone
            }
            EmailTextField(context,1F, email) {
                    newEmail -> email = newEmail
            }
            CountryAutoComplete(
                context = context,
                country = country,
                countries = countries
            );
            CityAutoComplete(
                context = context,
                selectedCountry = country,
                city = city,
                cities = cities
            )
            AddressTextField(context,1F, address) {
                    newAddress -> address = newAddress
            }
            NextButtonCenterEnd(
                context = context,
                onClickFunction = {contactDataFormNextButtonOnClick(context)}
            )
        }
    }
}

fun contactDataFormNextButtonOnClick(context: Context) {
    if (isContactDataValid(context)) {
        logcatAllContactData()
        val toastMessage = context.getString(R.string.contact_data_form_finished_toast_message)
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
    }
}

fun logcatAllContactData() {
    val logAllDataMessage = StringBuilder()
    logAllDataMessage.append("Información de contacto: \n")
    logAllDataMessage.append("Teléfono: ${phone.text} \n")
    logAllDataMessage.append("Email: ${email.text} \n")
    logAllDataMessage.append("País: ${country.value} \n")
    if (address.text.isNotEmpty()) {
        logAllDataMessage.append("Dirección: ${address.text} \n")
    }
    if (city.value.isNotEmpty()) {
        logAllDataMessage.append("Cuidad: ${city.value} \n")
    }
    Log.i("PersonalDataActivity", logAllDataMessage.toString())
}

fun isContactDataValid(context: Context): Boolean {
    val toastMessage = StringBuilder()
    if (!phoneIsValid()) {
        toastMessage.append(context.getString(R.string.phone_invalid))
        toastMessage.append(". ")
    }
    if (!emailIsValid()) {
        toastMessage.append(context.getString(R.string.email_invalid))
        toastMessage.append(". ")
    }

    if (!countryIsValid()) {
        toastMessage.append(context.getString(R.string.country_invalid))
        toastMessage.append(". ")
    }

    if (!cityIsValid()) {
        toastMessage.append(context.getString(R.string.city_invalid))
        toastMessage.append(". ")
    }

    if (toastMessage.isNotEmpty()) {
        Toast.makeText(context, toastMessage.toString(), Toast.LENGTH_SHORT).show()
        return false
    }
    return true
}

fun cityIsValid(): Boolean {
    if (countryIsValid()) {
        if (city.value.isNotEmpty()) {
            return cities.value?.data?.contains(city.value) == true
        }
    }
    return true
}

fun countryIsValid(): Boolean {
    if (country.value.isNotEmpty()) {
        if (countries.contains(country.value)) {
            return true
        }
        return false
    }
    return false
}

fun emailIsValid(): Boolean {
    if (email.text.isNotEmpty()) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email.text).matches()
    }
    return false
}

fun phoneIsValid(): Boolean{
    if (phone.text.isNotEmpty()) {
        return phone.text.length == 10
    }
    return false
}

@RequiresApi(Build.VERSION_CODES.M)
@Preview(showBackground = true)
@Composable
fun ContactDataActivityPreview() {
    val context = LocalContext.current
    Labs20232Gr03Theme {
        val context = LocalContext.current
        val contactDataFormTitle = context.getString(R.string.contact_data_form_title);
        ContactDataFormLandscape(context, contactDataFormTitle);
    }
}

