package com.example.projectmap.uicomponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.LocationTrackingMode
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.compose.rememberFusedLocationSource

@OptIn(ExperimentalPermissionsApi::class, ExperimentalNaverMapApi::class)
@Composable
fun MapMainScreen(modifier: Modifier = Modifier) {
    // 바텀바 선택 여부
    var selectedTab by remember { mutableStateOf(0) }

    // 위치 권한 요청
    val permissionState = rememberMultiplePermissionsState(
        listOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )
    // 처음 실행 시 권한 요청
    LaunchedEffect(Unit) { permissionState.launchMultiplePermissionRequest() }
    val granted = permissionState.permissions.any { it.status.isGranted }

    // 지도 상태
    val cameraPositionState = rememberCameraPositionState()
    val locationSource = rememberFusedLocationSource()

    // 추후 네이버 api로 검색 기능 추가
    var searchText by remember { mutableStateOf("") }

    // 레이아웃
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedIndex = selectedTab,
                onItemSelected = { selectedTab = it }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // 검색바 (현재 그냥 textfield, 추후 naver api 검색 추가)
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text("장소 검색") }
            )
            // 지도
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
            ) {
                if (granted) {
                    NaverMap(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        cameraPositionState = cameraPositionState,
                        locationSource = locationSource,
                        properties = MapProperties(
                            locationTrackingMode = LocationTrackingMode.Face
                        ),
                        uiSettings = MapUiSettings(
                            isLocationButtonEnabled = true
                        )
                    )
                } else {
                    NaverMap(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        cameraPositionState = cameraPositionState
                    )
                }
            }
        }
    }
}

// Preview
@Preview
@Composable
private fun MapMainScreenPreview() {
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedIndex = selectedTab,
                onItemSelected = { selectedTab = it }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            OutlinedTextField(
                value = "Test Search",
                onValueChange = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text("장소 검색") }
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Map Placeholder (NaverMap not rendered in preview)", modifier = Modifier.fillMaxSize())
            }
        }
    }
}