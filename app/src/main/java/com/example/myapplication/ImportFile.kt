package com.example.myapplication
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class ImportFile {

    @Composable
    fun FileUploadButton() {
        var selectedFile by rememberSaveable { mutableStateOf<Uri?>(null) }

        val context = LocalContext.current

        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) {
            uri: Uri? ->
            selectedFile = uri
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Button(onClick = {
                launcher.launch("application/pdf")
            }){
                Text("Upload PDF")
            }
        }
        Spacer(modifier =  Modifier.height((16.dp)))

        if (selectedFile != null) {
            Text("Selected: ${selectedFile!!.lastPathSegment}")
        }
    }
}