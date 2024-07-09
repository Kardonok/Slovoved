package com.example.slovoved.presentation.universal_components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.slovoved.R

val textStyle = TextStyle(
    fontSize = 20.sp,
    textAlign = TextAlign.Left,
    fontFamily = FontFamily(Font(R.font.kramola, FontWeight.Normal)),
    lineHeight = 16.sp,
)

@Preview(showBackground = true)
@Composable
fun CustomTextFieldPreview(){
    //CustomTextField(R.string.search)
}

@Composable
fun CustomTextField(@StringRes title: Int, changeSearchingWord:(String)->Unit, word: String = "") {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 12.dp, end = 16.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "Search",
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            BasicTextField(
                singleLine = true,
                value = word,
                onValueChange = {newWord -> changeSearchingWord(newWord) },
                modifier = Modifier.weight(1f),
                textStyle = textStyle,
            )
            if (word.isEmpty()) {
                Text(
                    text = stringResource(id = title),
                    style = textStyle,
                    color = Color.Gray,
                )
            }
        }
    }
}
