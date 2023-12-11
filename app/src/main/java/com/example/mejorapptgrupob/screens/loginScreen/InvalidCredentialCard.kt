package com.example.mejorapptgrupob.screens.loginScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mejorapptgrupob.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun InvalidCredentialCard(): Boolean{
    var isOpenDialog by remember { mutableStateOf(true) }

    AlertDialog(
        onDismissRequest = { isOpenDialog = false },
    ){
        ElevatedCard(
            Modifier.height(350.dp)
        ) {
            Column(
                Modifier
                    .weight(7f)
                    .fillMaxSize()
                    .padding(start = 20.dp, top = 20.dp, end = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    Modifier.weight(6f).fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painter = painterResource(id = R.drawable.account_not_found) , contentDescription = "Account not found")

                }
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Cuenta inválida :(")
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            Divider(
                Modifier
                    .height(1.dp)
                    .fillMaxWidth()
            )

            Column(
                Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .clickable {
                        isOpenDialog = false
                    }
                    .padding(top = 10.dp, bottom = 10.dp)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Continuar",
                        color = MaterialTheme.colorScheme.primary
                    )

                }
            }
        }
    }
    return isOpenDialog
}