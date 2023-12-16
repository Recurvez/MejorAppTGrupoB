package com.example.mejorapptgrupob.screens.infoScreen

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mejorapptgrupob.R
import com.example.mejorapptgrupob.screens.infoScreen.ui.theme.MejorAppTGrupoBTheme

class InfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Bloqueo de orientación
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setContent {
            MaterialTheme(
                colorScheme = MaterialTheme.colorScheme
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    InfoLayout()
                }
            }
        }
    }
}

@Composable
internal fun InfoLayout() {
    val mContext = LocalContext.current

    Image(
        painter = painterResource(R.drawable.infoscreen_bg),
        contentDescription = "fondo de la pantalla de información",
        contentScale = ContentScale.FillBounds,
        alignment = Alignment.BottomCenter
    )

    Column (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column( modifier = Modifier.fillMaxWidth(),

            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Spacer(modifier = Modifier.height(110.dp))
            Text(
                text = "INFORMACIÓN DE",

                style = MaterialTheme.typography.titleLarge, fontSize = 25.sp, fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.padding(5.dp))

            Text(
                text = "INTERÉS",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge,fontSize = 25.sp,fontWeight = FontWeight.Bold,
            )

        }

        Spacer(modifier = Modifier.padding(15.dp))

        Column {
            CardWithIcon(
                icon = painterResource(id = R.drawable.orien_icon),
                text = "DPTO DE ORIENTACION",
                text1 = "orientacion@iesgregorioprieto.com",
                background = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFADD8E6),
                        Color(0xFF9370DB),
                        Color(0xFFBA55D3)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset.Infinite
                )
            )

            Spacer(modifier = Modifier.height(30.dp))


            CardWithIcon(
                icon = painterResource(id = R.drawable.phone_icon),
                text = "TELÉFONO DEL MENOR",
                text1 = "116111",
                background = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFADD8E6),
                        Color(0xFF9370DB),
                        Color(0xFFBA55D3)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset.Infinite
                )
            )

            Spacer(modifier = Modifier.height(30.dp))

            CardWithIcon(
                icon = painterResource(id = R.drawable.phone_icon),
                text = "TELÉFONO CONTRA EL BULLYING",
                text1 = "900 018 018",
                background = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFADD8E6),
                        Color(0xFF9370DB),
                        Color(0xFFBA55D3)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset.Infinite
                )
            )

        }
    }
}

@Composable
fun CardWithIcon(
    icon: Painter,
    text: String,
    text1:String,
    background: Brush
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(20.dp, 5.dp, 20.dp, 10.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    )
    {
        Box(
            modifier = Modifier
                .background(background)
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

                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)

                    Spacer(modifier = Modifier.padding(5.dp))

                    Text(
                        text = text1,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White

                    )
                }
            }
        }

    }
}