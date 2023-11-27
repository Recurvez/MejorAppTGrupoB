package com.example.mejorapptgrupob.screens.firstScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mejorapptgrupob.R

class FirstScreen {
    companion object{

        @Composable
        internal fun FirstLayout(){
            // Fondo que ocupa toda la pantalla
            Image(
                painter = painterResource(id = R.drawable.firstscreen_bg),
                contentDescription = "First screen background",
                alignment = Alignment.BottomCenter,
                contentScale = ContentScale.FillBounds
            )

            // Columna que ocupa toda la pantalla
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ){

                // Icono de arriba a la derecha
                CircularIconButton(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 16.dp),
                    icon = painterResource(id = R.drawable.ic_launcher_foreground),
                    onClick = {/*Por hacer*/}
                )
                
                Spacer(modifier = Modifier.height(80.dp))

                // Título
                Text(
                    text = "Salud Mental",
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Tres tarjetas con icono
                Column{
                    CardWithIcon(
                        icon = painterResource(id = R.drawable.userguide_icon),
                        text = "Guía de usuario",
                        onClick = {/*Por hacer*/}
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    CardWithIcon(
                        icon = painterResource(id = R.drawable.test_icon),
                        text = "Realizar Test",
                        onClick = {/*Por hacer*/}
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    CardWithIcon(
                        icon = painterResource(id = R.drawable.info_icon),
                        text = "Información de interés",
                        onClick = {/*Por hacer*/}
                    )
                }
            }
        }
    }
}

@Composable
fun CircularIconButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    onClick: () -> Unit
){
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(color = Color.Black)

    ){
        Icon(
            painter = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun CardWithIcon(
    icon: Painter,
    text: String,
    onClick: () -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(10.dp, 5.dp, 10.dp, 10.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(painter = icon,
                  contentDescription = null,
                  Modifier.size(60.dp)
            )

            Spacer(modifier = Modifier.width(32.dp))

            Text(
                text = text,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}