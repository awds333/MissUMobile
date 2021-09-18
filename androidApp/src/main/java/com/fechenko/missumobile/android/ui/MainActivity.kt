package com.fechenko.missumobile.android.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.fechenko.missumobile.Greeting
import com.fechenko.missumobile.android.ui.component.theme.MissUTheme

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MissUTheme {
                Text(text = greet())
            }
        }
    }
}

@Preview
@Composable
fun TextPreview() {
    Text(text = greet())
}
