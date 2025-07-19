package com.example.myapplication

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.text.PDFTextStripper

class ImportFile {
    @Composable
    fun FileUploadButton() {
        var selectedFile by rememberSaveable { mutableStateOf<Uri?>(null) }
        var extractedText by rememberSaveable { mutableStateOf("") }

        val context = LocalContext.current

        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            selectedFile = uri
        }



        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                launcher.launch("application/pdf")
            }) {
                Text("Upload PDF")
            }

            Spacer(modifier = Modifier.height(16.dp))

            selectedFile?.let {
                Text("Selected: ${it.lastPathSegment}")
            }
            // ✅ This launches after `selectedFile` changes
            LaunchedEffect(selectedFile) {
                selectedFile?.let { uri ->
                    extractedText = readPdfTextFromUri(context, uri)
                }
            }
            if (extractedText.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Extracted Text:\n$extractedText")
            }
        }
    }
}


/**
 * Reads and extracts text from a PDF file Uri using PDFBox.
 */
fun readPdfTextFromUri(context: Context, uri: Uri): String {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri)
        val document = PDDocument.load(inputStream)
        val stripper = PDFTextStripper()
        val text = stripper.getText(document)
        document.close()
        text
    } catch (e: Exception) {
        "Error reading PDF: ${e.message}"
    }
}
