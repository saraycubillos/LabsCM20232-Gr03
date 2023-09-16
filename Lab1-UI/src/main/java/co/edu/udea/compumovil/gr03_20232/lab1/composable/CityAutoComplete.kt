package co.edu.udea.compumovil.gr03_20232.lab1.composable

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import co.edu.udea.compumovil.gr03_20232.lab1.R

@RequiresApi(Build.VERSION_CODES.M)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityAutoComplete(
    context: Context,
    country : MutableState<String>,
    city: MutableState<String>,
    cities: MutableSet<String>
) {
    //TODO la peticion api con country para obtener las ciudades y agregarlas al set
    LaunchedEffect(country.value) {
        //cities.addAll(setOf())
    }

    val heightTextFields by remember {
        mutableStateOf(55.dp)
    }

    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    val interactionSource = remember {
        MutableInteractionSource()
    }

    Column(
        modifier = Modifier
            .padding(30.dp)
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    expanded = false
                }
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier.padding(start = 3.dp, bottom = 2.dp),
            text = context.getString(R.string.city_text_field_hint),
            fontSize = 16.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Medium
        )

        Column(modifier = Modifier.fillMaxWidth(0.7f)) {

            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(heightTextFields)
                        .border(
                            width = 1.8.dp,
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .onGloballyPositioned { coordinates ->
                            textFieldSize = coordinates.size.toSize()
                        },
                    value = city.value,
                    onValueChange = { newValue: String ->
                        city.value = newValue
                        expanded = true
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black
                    ),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    singleLine = true,
                    trailingIcon = {
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Rounded.KeyboardArrowDown,
                                contentDescription = "arrow",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                )
            }

            AnimatedVisibility(visible = expanded) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .width(textFieldSize.width.dp),
                    //elevation = 15.dp,
                    shape = RoundedCornerShape(10.dp)
                ) {

                    LazyColumn(
                        modifier = Modifier.heightIn(max = 150.dp),
                    ) {

                        if (city.value.isNotEmpty()) {
                            items(
                                cities.filter {
                                    it.lowercase()
                                        .contains(city.value.lowercase()) || it.lowercase()
                                        .contains(context.getString(R.string.other))
                                }
                                    .sorted()
                            ) {
                                CategoryItems(
                                    title = it,
                                    category = city,
                                    onExpandedChange = { newExpanded ->
                                        expanded = newExpanded
                                    }
                                )
                            }
                        } else {
                            items(
                                cities.sorted()
                            ) {
                                CategoryItems(
                                    title = it,
                                    category = city,
                                    onExpandedChange = { newExpanded ->
                                        expanded = newExpanded
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

