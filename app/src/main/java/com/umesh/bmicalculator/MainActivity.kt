package com.umesh.bmicalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umesh.bmicalculator.ui.theme.BMICalculatorTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMICalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top,
                    ) {
                        var weight by remember {
                            mutableStateOf("")
                        }
                        var height by remember {
                            mutableStateOf("")
                        }
                        var calculatedBMI by remember {
                            mutableStateOf(0.0)
                        }
                        OutlinedTextField(
                            value = weight,
                            onValueChange = { enteredWeight ->
                                weight = enteredWeight
                            },
                            label = {
                                Text(text = "Weight")
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        OutlinedTextField(
                            value = height,
                            onValueChange = { enteredHeight ->
                                height = enteredHeight
                            },
                            label = {
                                Text(text = "Height")
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        Spacer(modifier = Modifier.height(30.dp))

                        Button(
                            enabled = weight.isNotEmpty() && height.isNotEmpty(),
                            onClick = {
                                val weightInKg = weight.toDouble()
                                val heightInMeter = height.toDouble() / 100
                                val totalHeight = heightInMeter * heightInMeter
                                calculatedBMI = weightInKg / totalHeight
                            }
                        ) {
                            Text(
                                text = "Calculate",
                                style = TextStyle(color = Color.White, fontSize = 20.sp)
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "Your BMI is ${String.format("%.2f", calculatedBMI)}",
                            style = TextStyle(color = Color.Black, fontSize = 20.sp)
                        )
                    }
                }
            }
        }
    }
}