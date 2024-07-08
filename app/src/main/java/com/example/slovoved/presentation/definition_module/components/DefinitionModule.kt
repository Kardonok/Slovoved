package com.example.slovoved.presentation.definition_module.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.slovoved.R
import com.example.slovoved.presentation.search_module.SearchViewModel
import com.example.slovoved.presentation.universal_components.textStyle

@Preview(showBackground = true)
@Composable
fun FunctionalBarPreview() {
    FunctionalBar(word="Android",bookmarkIsActive = true)
}

@Preview(showBackground = true)
@Composable
fun DefinitionModulePreview()
{
    //val definition = "Android это операционная система разработанная компанией Google и предназначенная для мобильных устройств таких как смартфоны и планшеты С момента своего запуска в 2008 году Android прошел долгий путь эволюционируя от относительно простой платформы до мощной и многофункциональной системы которая используется миллиардами людей по всему миру Основой Android является ядро Linux что обеспечивает высокую производительность и надежность системы Одним из ключевых факторов успеха Android является его открытая архитектура позволяющая разработчикам создавать приложения и адаптировать операционную систему под свои нужды Магазин приложений Google Play предоставляет пользователям доступ к миллионам приложений на любой вкус и потребность от социальных сетей и игр до инструментов для работы и обучения Это делает Android невероятно гибкой и многофункциональной платформой которую можно настроить под конкретные задачи и предпочтения пользователей Со временем Android получил множество обновлений и улучшений каждое из которых привносило новые функции и возможности Например введение многозадачности улучшение производительности батареи повышение безопасности и интеграция с облачными сервисами Одним из значимых этапов в развитии Android стала интеграция с голосовым помощником Google Assistant что позволило пользователям управлять устройством с помощью голосовых команд С течением времени Android стал основой не только для смартфонов и планшетов но и для множества других устройств таких как умные часы Android Wear телевизоры Android TV и автомобильные системы Android Auto Это свидетельствует о гибкости и универсальности платформы которая способна адаптироваться под различные сценарии использования Важной частью экосистемы Android являются обновления безопасности и защиты данных Google регулярно выпускает патчи безопасности чтобы защитить пользователей от угроз и уязвимостей С каждым новым обновлением система становится более защищенной и устойчивой к различным видам атак что особенно важно в современном мире где кибербезопасность имеет первостепенное значение Большую роль в развитии Android играют производители устройств такие как Samsung Huawei LG и другие которые используют эту операционную систему в своих продуктах Благодаря их вкладу Android получает разнообразие аппаратных решений и инновационных функций что делает его еще более привлекательным для пользователей Еще одной важной особенностью Android является возможность кастомизации Пользователи могут менять интерфейс устройства устанавливать различные лаунчеры темы и виджеты чтобы адаптировать внешний вид и функциональность системы под свои потребности Эта гибкость позволяет создавать уникальный пользовательский опыт который отличает Android от других операционных систем Разработчики приложений также играют ключевую роль в экосистеме Android Они создают миллионы приложений которые расширяют функциональность устройства и делают его использование более удобным и интересным В свою очередь Google предоставляет разработчикам мощные инструменты и ресурсы такие как Android Studio и библиотека Android Jetpack для создания качественных и производительных приложений Важным аспектом Android является его глобальное распространение Операционная система используется в различных странах и культурах и поддерживает множество языков и локализаций Это делает Android доступным и удобным для пользователей по всему миру независимо от их местоположения или языка В последние годы Android стал активно развиваться в области искусственного интеллекта и машинного обучения Интеграция с Google AI позволяет устройствам на базе Android использовать возможности машинного обучения для улучшения производительности и предоставления пользователям новых функциональных возможностей Например функции такие как автоматическое распознавание объектов на фотографиях интеллектуальные уведомления и прогнозирование действий пользователя значительно улучшают пользовательский опыт Android также активно используется в образовательных и профессиональных сферах Устройства на базе этой операционной системы используются в школах университетах и на рабочих местах предоставляя доступ к образовательным ресурсам и инструментам для повышения производительности Это еще раз подчеркивает универсальность и многофункциональность платформы В заключение можно сказать что Android прошел долгий путь от своей первоначальной версии до современной многофункциональной операционной системы которая используется миллиардами людей по всему миру Благодаря своей гибкости и открытости Android продолжает развиваться и адаптироваться под новые технологии и потребности пользователей что делает его одной из самых значимых и влиятельных платформ в мире"
    //DefinitionModule(word="Android", definition = definition, bookmarkIsActive = false)
}

@Composable
fun DefinitionModule(searchViewModel: SearchViewModel, bookmarkIsActive: Boolean,navController: NavHostController?=null) {

    val searchState by searchViewModel.searchState.collectAsState()

    Scaffold(bottomBar = { FunctionalBar(word=searchState.word,bookmarkIsActive=bookmarkIsActive,navController=navController) },
        content = {padding->
            LazyColumn(contentPadding = padding) {
                item {
                    Text(text = searchState.definitionList[searchState.definitionIndex], style = textStyle, modifier = Modifier.padding(16.dp))
                }
                //второй вариант прокруткитекста, тк я еще не заню как
                //будет предствлен текст определения, решил что
                //лучше написать сразу же два варианта, вывода текста
                /*
                items(definition.lines()) { line ->
                    Text(text = line, style = textStyle)
                }
                */
            }
        }
    )
}



@Composable
fun FunctionalBar(word:String ,bookmarkIsActive: Boolean, navController: NavHostController?=null) {

    var isPoppingBackStack by remember { mutableStateOf(false) }

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(color = Color(0xFF4B4B4B))) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .matchParentSize())
        {
            Image(painter = painterResource(id = R.drawable.arrow_left_white),
                contentDescription = "back to search",
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .size(32.dp)
                    .clickable(onClick = { if(!isPoppingBackStack){
                        isPoppingBackStack=true
                        navController?.popBackStack()} })
            )
            Row(modifier = Modifier.fillMaxHeight().padding(start = 8.dp, end = 8.dp),
                verticalAlignment = Alignment.CenterVertically)
            {
                Image(painter = painterResource(id = if(bookmarkIsActive) R.drawable.active_bookmark else R.drawable.inactive_bookmark),
                    contentDescription = "go to bookmarks",
                    modifier = Modifier
                        .size(32.dp)
                        .align(alignment = Alignment.Top)
                        .clickable(onClick = { /*TODO*/ })
                )
                Image(painter = painterResource(id = R.drawable.copy),
                    contentDescription = "copy definition",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable(onClick = { /*TODO*/ })
                )
            }
        }
        Text(text = word,
            style = textStyle,
            color = Color.White,
            fontSize = 26.sp)
    }
}