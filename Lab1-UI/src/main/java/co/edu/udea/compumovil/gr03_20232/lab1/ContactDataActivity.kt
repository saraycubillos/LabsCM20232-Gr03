package co.edu.udea.compumovil.gr03_20232.lab1

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr03_20232.lab1.composable.AdressTextField
import co.edu.udea.compumovil.gr03_20232.lab1.composable.CityDropdownField
import co.edu.udea.compumovil.gr03_20232.lab1.composable.CountryDropdownField
import co.edu.udea.compumovil.gr03_20232.lab1.composable.EmailTextField
import co.edu.udea.compumovil.gr03_20232.lab1.composable.NameTextField
import co.edu.udea.compumovil.gr03_20232.lab1.composable.PhoneTextField
import co.edu.udea.compumovil.gr03_20232.lab1.composable.TitleText
import co.edu.udea.compumovil.gr03_20232.lab1.ui.theme.Labs20232Gr03Theme

class ContactDataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Labs20232Gr03Theme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    //ContactDataForm("hola");
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDataForm(context: Context, titulo: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        TitleText(titulo);
        Column (
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            PhoneTextField(context,1F, name) {
                    newName -> name = newName
            }
            EmailTextField(context,1F, name) {
                    newName -> name = newName
            }
            CountryDropdownField();
            CityDropdownField();
            AdressTextField(context,1F, name) {
                    newName -> name = newName
            }
        }
    }


        // Aquí puedes agregar los campos y contenido del formulario de datos personales
}

@Preview(showBackground = true)
@Composable
fun ContactDataActivityPreview() {
    val context = LocalContext.current
    Labs20232Gr03Theme {
        val context = LocalContext.current
        val contactDataFormTitle = context.getString(R.string.contact_data_form_title);
        ContactDataForm(context, contactDataFormTitle);
    }
}

