package com.example.meteo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.meteo.ui.meteo.model.WeatherViewModel
import com.example.meteo.ui.meteo.model.WeatherViewModelState
import com.example.meteo.ui.theme.MeteoTheme
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /*MeteoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //Greeting("Android")
                    WeatherList("meteo")
                }
            }*/
            WeatherList("Lyon")

        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MeteoTheme {
        Greeting("Android")
    }
}

@Composable
fun WeatherList(name: String) {
    val weatherViewModel = getViewModel<WeatherViewModel>()
    val state by remember(weatherViewModel) {
        weatherViewModel.cityChanged(name)
    }.collectAsState(initial = WeatherViewModelState())

    Column() {
        Text(text = "Hello $name!")
        state.items.let {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                items(items = it, itemContent = { item ->
                    Row() {
                        Text(text = "Le ${item.day} ?? ${item.hour}H ==> ")
                        Text(
                            text =
                            when (item.image) {
                                "1" -> "beau"
                                "2" -> "moyen"
                                "3" -> "pluie"
                                else -> ":/"
                            }
                        )
                    }
                })
            }
        }
    }
}
