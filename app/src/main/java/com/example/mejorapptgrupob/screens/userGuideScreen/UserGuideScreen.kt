package com.example.mejorapptgrupob.screens.userGuideScreen

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mejorapptgrupob.R

import com.example.mejorapptgrupob.screens.userGuideScreen.ItemsContent.*

// Idea de aquí -> https://www.youtube.com/watch?v=5rnQ_kBSFBU
// Y efecto del siguiente vídeo -> https://www.youtube.com/watch?v=TMaIMctI7oo
class UserGuideScreen {
    companion object {
        @Composable
        internal fun UserGuideLayout() {
            Image(
                painter = painterResource(id = R.drawable.userguidescreen),
                contentDescription = "background for user guide screen",
                alignment = Alignment.BottomCenter,
                // El fondo no se llegaba a ajustar bien a la anchura del dispositivo
                contentScale = ContentScale.FillBounds
            )

            Column {

                Column(
                    Modifier
                        .padding(start = 10.dp, end = 10.dp, top = 5.dp)

                ) {

                    val itemsList = listOf(
                        Item1,
                        Item2,
                        Item3
                    )

                    LazyColumn(
                        contentPadding = PaddingValues(10.dp)
                    ){
                        items(itemsList){ item ->
                            if(itemsList[0] == item){
                                Title()
                            }
                            ListItemRow(item)
                        }
                    }
                }
            }
        }

        @Composable
        private fun Title(){
            Spacer(modifier = Modifier.height(50.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Guía de usuario",
                    color = Color.White,
                    fontSize = 35.sp,
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
        }

        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
        private fun ListItemRow(item: ItemsContent){
            // https://stackoverflow.com/questions/66612571/remember-list-item-states-while-navigating-in-jetpack-compose
            var isCardOpen by rememberSaveable(item) { mutableStateOf(false) }

            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .heightIn(min = 90.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp,
                ),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                ),
                onClick = {
                    isCardOpen = !isCardOpen
                }
            ) {
                Column(
                    Modifier
                        .padding(
                            start = 15.dp,
                            end = 15.dp,
                            top = 10.dp,
                            bottom = 10.dp
                    )
                        .animateContentSize(
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioMediumBouncy,
                                stiffness = Spring.StiffnessLow
                            )
                        )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,

                    ) {
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.titleLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .weight(1f)
                        )

                        Icon(
                            imageVector = if (isCardOpen) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                            contentDescription = "Más información"
                        )

                    }
                    if(isCardOpen){
                        Row(
                        ){
                            Text(text = item.details)
                        }
                    } else {
                        Row(Modifier.padding(end = 45.dp)) {
                            Text(
                                text = item.details,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
        }

    }
}