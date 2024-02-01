package com.bazuma.businesscard

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bazuma.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                    CreateBizCard()
            }
        }
    }
}

@Composable
fun CreateBizCard(){
    //After Created Content Composable
    val buttonClickedState = remember{
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card (
            modifier= Modifier
                .width(190.dp)
                .height(390.dp)
                .padding(12.dp)
            ,
            shape = RoundedCornerShape(corner = CornerSize(20.dp)),
            colors =CardDefaults.cardColors( containerColor = Color.White),
            elevation = CardDefaults.cardElevation(4.dp)

        ){

            //Add things vertically
            Column(
                modifier=Modifier.height(300.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateProfileImage()
                Divider()
                CreateProfileInfo()
                Button(
                    onClick = {
                        buttonClickedState.value = !buttonClickedState.value
                }) {
                     Text(
                         text = "Porfolio",
                         style = MaterialTheme.typography.labelLarge)
                }
                if (buttonClickedState.value){
                    CardContent()
                }else{
                    Box{}
                }
            }

        }
    }
}


@Composable
fun CardContent(){
  Box(modifier = Modifier
      .fillMaxWidth()
      .fillMaxHeight()
      .padding(5.dp)
  ){
      Surface(
          modifier = Modifier
              .padding(3.dp)
              .fillMaxHeight()
              .fillMaxWidth(),
          shape = RoundedCornerShape(corner= CornerSize(6.dp)),
          border = BorderStroke(width = 2.dp,color=Color.LightGray)
      ) {
          Portfolio(data = listOf(
              "Project 1",
              "Project 2",
              "Project 3",
              "Project 4",
              "Project 5"
              ))
      }
  }
}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn {
        items(data){ item ->
            Card(
                modifier = Modifier
                    .padding(13.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RectangleShape

            ){
                Row(
                    modifier= Modifier
                        .padding(8.dp)
                        .background(MaterialTheme.colorScheme.onSurface)
                        .padding(7.dp)

                ) {
                    CreateProfileImage(modifier=Modifier.size(100.dp))

                    Column(modifier=Modifier.padding(7.dp).align(alignment = Alignment.CenterVertically)) {
                        Text(text = item, fontWeight = FontWeight.Bold)
                        Text(text = "It is a great Project", style = MaterialTheme.typography.bodySmall     )

                    }
                }
            }
        }
    }
}
@Composable
private fun CreateProfileInfo() {
    Column(
        modifier = Modifier.padding(5.dp)
    ) {
        Text(
            text = "Ayubu Mohamed",
            fontSize = 30.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Android Developer",
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.bodyLarge,
        )

        Text(
            text = "@AyubuMoha",
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.bodySmall


        )
    }
}

@Composable
private fun CreateProfileImage(modifier:Modifier = Modifier) {
    Surface(
        modifier = Modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)

    ) {
        Image(
            painter = painterResource(id = R.drawable.ayubu_profile),
            contentDescription = "Profile Image",
            modifier = modifier.size(130.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCardTheme {
        CreateBizCard()
    }
}