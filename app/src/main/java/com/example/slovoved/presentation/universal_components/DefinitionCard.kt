package com.example.slovoved.presentation.universal_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.slovoved.R

@Preview(showBackground = true)
@Composable
fun DefinitionCardPreview()
{
    val definition = "Android — это операционная система для мобильных устройств, разработанная компанией Google. Она основана на ядре Linux и предоставляет пользователям богатый набор приложений, инструментов и сервисов для создания, развития и распространения мобильных приложений."
    //DefinitionCard(definition=definition, bookmarkIsActive = false)
}

@Composable
fun DefinitionCard(definition: String, bookmarkIsActive: Boolean, openFullDefinition: ()->Unit) {

    val colors = listOf(
        Color(0xFF8B4513), // Saddle Brown (коричневый, напоминающий древесину)
        Color(0xFF556B2F), // Dark Olive Green (темный оливковый зеленый)
        Color(0xFFD2B48C), // Tan (загар, светло-коричневый)
        Color(0xFFCD853F), // Peru (темно-коричневый)
        Color(0xFF8FBC8F), // Dark Sea Green (темно-морской зеленый)
        Color(0xFFA0522D), // Sienna (сенный коричневый)
        Color(0xFFD2691E), // Chocolate (шоколадный)
        Color(0xFFDEB887)  // Burlywood (бежево-коричневый)
    )
    // Выбор случайного цвета из списка
    val randomColor = remember { colors.random() }

    Row(modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)){
        Spacer(modifier = Modifier
            .fillMaxHeight()
            .width(15.dp)
            .clip(shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp))
            .background(color = randomColor)
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(topStart = 8.dp,bottomStart = 8.dp)))
        Spacer(modifier = Modifier
            .width(10.dp))
        Row(modifier = Modifier
            .weight(1f)
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(bottomEnd = 8.dp))
            .clickable(onClick = { openFullDefinition() })){
            Text(text = definition,
                style = textStyle,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 16.sp,
                modifier = Modifier
                    .weight(1f)
                    .align(alignment = Alignment.CenterVertically)
                    .padding(start = 16.dp))
            Row(modifier = Modifier.fillMaxHeight().padding(start = 4.dp, end = 4.dp)){
                Image(painter =  painterResource(id = if(bookmarkIsActive) R.drawable.active_bookmark else R.drawable.inactive_bookmark),
                    contentDescription = "activate a bookmark",
                    modifier = Modifier.size(32.dp)
                        .clickable(onClick = { /*TODO*/ }))
                Icon(painter = painterResource(id = R.drawable.arrow_right_white),
                    contentDescription = "full definition",
                    modifier = Modifier.align(alignment = Alignment.CenterVertically)
                )
            }
        }
    }
}
