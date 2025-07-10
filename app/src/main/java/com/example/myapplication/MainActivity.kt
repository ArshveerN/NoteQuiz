package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.googlefonts.GoogleFontProvider
import androidx.compose.ui.text.googlefonts.rememberGoogleFont
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                GradientBackground("0xFF121212", "0xFF1F1F1F") {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Custom Font
                        val font = rememberGoogleFont(
                            name = "JetBrains Mono",
                            provider = GoogleFont.Provider(
                                authority = "com.google.android.gms.fonts",
                                packageName = "com.google.android.gms",
                                certificates = R.array.com_google_android_gms_fonts_certs
                            )
                        )

                        // Title Text
                        Text(
                            text = "Start Practicing",
                            fontSize = 30.sp,
                            color = Color.White,
                            fontWeight = FontWeight.ExtraBold,
                            textAlign = TextAlign.Center,
                            fontFamily = font,
                            modifier = Modifier.padding(bottom = 32.dp)
                        )

                        // Bordered Box around button
                        Box(
                            modifier = Modifier
                                .border(
                                    width = 2.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .padding(24.dp)
                        ) {
                            ImportFile().FileUploadButton()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GradientBackground(colorString1: String, colorString2: String, content: @Composable () -> Unit) {
    val color1 = parseInputColor(colorString1)
    val color2 = parseInputColor(colorString2)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(colors = listOf(color1, color2))
            )
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

fun parseInputColor(colorString: String): Color {
    return try {
        when (colorString.lowercase()) {
            "red" -> Color.Red
            "blue" -> Color.Blue
            "green" -> Color.Green
            "yellow" -> Color.Yellow
            "black" -> Color.Black
            "white" -> Color.White
            else -> Color(colorString.toColorInt())
        }
    } catch (e: Exception) {
        Color.Gray
    }
}
