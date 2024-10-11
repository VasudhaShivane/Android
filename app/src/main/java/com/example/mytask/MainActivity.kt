package com.example.mytask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mytask.ui.theme.MyTaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTaskTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NumberComparisonApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberComparisonApp(modifier: Modifier = Modifier) {
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFE6E6FA)) // Light purple background
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title Text
        Text(
            text = "Number Comparison",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6A0DAD), // Purple color
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // First number input field
        val containerColor = Color(0xFF9476C9)
        TextField(
            value = number1,
            onValueChange = { number1 = it },
            label = { Text("Enter first number") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                disabledContainerColor = containerColor,
                focusedIndicatorColor = Color(0xFF6A0DAD),
                unfocusedIndicatorColor = Color(0xFF6A0DAD),
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Second number input field
        TextField(
            value = number2,
            onValueChange = { number2 = it },
            label = { Text("Enter second number") },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFF9476C9),
                focusedIndicatorColor = Color(0xFF6A0DAD),
                unfocusedIndicatorColor = Color(0xFF6A0DAD)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Buttons to find smallest and largest
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { result = findSmallest(number1, number2) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A0DAD)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text("Find Smallest", color = Color.White)
            }
            Button(
                onClick = { result = findLargest(number1, number2) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A0DAD)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text("Find Largest", color = Color.White)
            }
        }

        // Result TextView
        Text(
            text = "Result: $result",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF4B0082), // Dark purple
            modifier = Modifier.padding(top = 24.dp)
        )
    }
}

// Function to find the smallest number
fun findSmallest(num1: String, num2: String): String {
    return try {
        val n1 = num1.toDouble()
        val n2 = num2.toDouble()
        "Smallest: ${minOf(n1, n2)}"
    } catch (e: Exception) {
        "Invalid input"
    }
}

// Function to find the largest number
fun findLargest(num1: String, num2: String): String {
    return try {
        val n1 = num1.toDouble()
        val n2 = num2.toDouble()
        "Largest: ${maxOf(n1, n2)}"
    } catch (e: Exception) {
        "Invalid input"
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyTaskTheme {
        NumberComparisonApp()
    }
}
