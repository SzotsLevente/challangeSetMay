package com.example.challangesetmay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.challangesetmay.ui.theme.ChallangeSetMayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChallangeSetMayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LessonOverviewSheet(modifier = Modifier.padding(innerPadding))
                    /*SearchableStudyList(
                        modifier = Modifier.padding(innerPadding)
                    )*/
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChallangeSetMayTheme {
        LessonOverviewSheet()
    }
}