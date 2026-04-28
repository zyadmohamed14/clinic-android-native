package com.careline.clinicapp.feature.auth.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.careline.clinicapp.core.constants.DrawableResources
import com.careline.clinicapp.core.ui.components.AppHeightSpace
import com.careline.clinicapp.core.ui.components.CustomIcon
import com.careline.clinicapp.core.ui.components.HeadlineText
import com.careline.clinicapp.feature.auth.presentation.viewmodel.LoginViewModel

@Composable
fun AuthScreen() {

    var selectedTabIndex by remember { mutableStateOf(0) }

    val tabs = listOf("Login", "Sign Up")
   val  viewModel: LoginViewModel = hiltViewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
           // .padding(16.dp),
                ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomIcon(source = DrawableResources.appLogo.toString(), isImage = true, size = 150f)
        HeadlineText(text = "Welcome to CareLine")
        AppHeightSpace()
       // CustomIcon(source = DrawableResources.appLogo.toString(), isImage = true, size = 150f)
        // 🔥 Tabs
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(title) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🔥 Content
        when (selectedTabIndex) {
            0 -> LoginTab(viewModel)
            1 -> SignUpTab()
        }
    }
}
@Composable
fun SignUpTab(){
    Text(text = "Sign Up Tab")

}