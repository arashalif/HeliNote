package com.arashaghelifar.ui.components

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.content.ContextCompat

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun CheckNotificationPermission(context: Context, onPermissionGranted: () -> Unit) {
    val permissionState = remember { mutableStateOf(false) }

    // Check if the permission is already granted
    LaunchedEffect(Unit) {
        permissionState.value = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    }

    // Create a launcher for the permission request
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        permissionState.value = isGranted
        if (isGranted) {
            onPermissionGranted()
        }
    }

    if (!permissionState.value) {
        // Request permission
        Button(onClick = {
            launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }) {
            Text(text = "Request Notification Permission")
        }
    } else {
        // Permission already granted, proceed
        onPermissionGranted()
    }
}