package com.example.slovoved.presentation.search_module.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.slovoved.R
import com.example.slovoved.navigation.Screen
import com.example.slovoved.presentation.search_module.Search
import com.example.slovoved.presentation.search_module.SearchStatus
import com.example.slovoved.presentation.search_module.SearchViewModel
import com.example.slovoved.presentation.universal_components.CustomTextField
import com.example.slovoved.presentation.universal_components.DefinitionCard
import com.example.slovoved.presentation.universal_components.textStyle
import kotlinx.coroutines.delay

@Preview(showBackground = true)
@Composable
fun SearchBarPreview()
{
   //SearchBar()
}

@Preview(showBackground = true)
@Composable
fun SearchModulePreview() {
    val definitionList = arrayOf(
        "Android — это операционная система для смартфонов, планшетов и других устройств, разработанная компанией Google. Она основана на ядре Linux и предоставляет пользователям богатый набор приложений, инструментов и сервисов для создания, развития и распространения мобильных приложений.",
        "Android Studio — это интегрированная среда разработки (IDE) для создания приложений под Android. Она предоставляет все необходимые инструменты и поддержку для разработчиков, включая эмуляторы, отладчик, средства профилирования и многое другое.",
        "Android приложения — это программы, которые работают на операционной системе Android. Они могут быть созданы на любом языке программирования, поддерживаемом Android SDK, но наиболее популярными являются Java, Kotlin и Swift.",
        "Android SDK (Software Development Kit) — это набор инструментов, библиотек и документации, необходимых для разработки приложений под Android. Он включает в себя компиляторы, эмуляторы, инструменты для отладки и другие средства, которые помогают разработчикам создавать качественные приложения.",
        "AndroidManifest.xml — это основной конфигурационный файл приложения Android. Он определяет основные характеристики приложения, такие как имя пакета, версия, разрешения, необходимые компоненты и другие параметры.",
        "Activity — это основной компонент приложения Android, который представляет собой отдельный экран с пользовательским интерфейсом. Они обрабатывают ввод пользователя, отображают и изменяют содержимое экрана и управляют жизненным циклом приложения.",
        "Service — это компонент приложения Android, который выполняет операции в фоновом режиме, не требуя взаимодействия с пользовательским интерфейсом. Они могут быть использованы для загрузки медиафайлов, выполнения фоновых задач и других длительных операций.",
        "Broadcast Receiver — это компонент приложения Android, который позволяет приложениям реагировать на системные события или сообщения других приложений. Они могут использоваться для реализации таких функций, как уведомления, автоматическая синхронизация и других.",
        "Content Provider — это компонент приложения Android, который предоставляет общий доступ к данным приложения или другим приложениям. Они могут использоваться для реализации таких функций, как обмен контактами, календарь и другие.",
        "Intent — это объект, используемый для запуска компонентов приложения Android, передачи сообщений между ними и выполнения различных действий. Они могут использоваться для запуска активностей, служб, широковещательных сообщений и других операций.",
        "SQLite — это встроенная база данных SQL для приложений Android. Она предоставляет простой и эффективный способ хранения и извлечения данных локально на устройстве. Она поддерживает большинство стандартных операций SQL и может использоваться для реализации таких функций, как хранение пользовательских настроек, локальных списков и других.",
        "RecyclerView — это компонент Android, предоставляющий эффективный способ отображения больших списков или гетерогенных наборов данных. Он использует паттерн адаптер-виджет для отображения элементов списка и может быть использован для реализации таких функций, как отображение списка контактов, новостей и других.",
        "View — это основной компонент пользовательского интерфейса в приложениях Android. Они представляют собой элементы, которые пользователь видит и с которыми может взаимодействовать. Например, TextView, EditText, Button, ImageView и другие.",
        "Layout — это контейнер, который используется для организации и отображения компонентов пользовательского интерфейса в приложениях Android. Они позволяют создавать сложные и удобные для использования пользовательские интерфейсы. Например, LinearLayout, RelativeLayout, ConstraintLayout и другие.",
        "Fragment — это компонент пользовательского интерфейса в приложениях Android, который может быть использован для создания модульных и переиспользуемых экранов. Они могут быть добавлены в активности и управляться с помощью менеджера фрагментов. Они предоставляют более гибкий и управляемый способ создания пользовательского интерфейса, чем использование одиночных активностей.",
        "Material Design — это набор рекомендаций и компонентов пользовательского интерфейса, которые помогают создавать красивые, удобные и современные приложения Android. Он включает в себя шрифты, цвета, тени, пространство и другие элементы, которые помогают сделать приложения более привлекательными и удобными для использования.",
        "Google Play Services — это набор библиотек и сервисов, предоставляемых Google, которые помогают разработчикам интегрировать различные функции и сервисы в свои приложения Android. Они включают в себя такие сервисы, как Google Maps, Firebase, Google Fit и другие.",
        "Android Jetpack — это набор библиотек и инструментов, предоставляемых Google, которые помогают разработчикам создавать более качественные, надежные и современные приложения Android. Он включает в себя такие библиотеки, как Lifecycle, Navigation, Room и другие.",
        "Kotlin — это статически типизированный язык программирования, разработанный компанией JetBrains. Он предоставляет более высокую производительность, более безопасный код и более удобный синтаксис, чем Java. Kotlin поддерживается как основной язык для разработки приложений под Android."
    )
    //SearchModule()
}




@Composable
fun SearchModule(searchViewModel: SearchViewModel, navController: NavHostController?=null) {

    val searchState by searchViewModel.searchState.collectAsState()

    Scaffold(bottomBar = { SearchBar(searchState = searchState, searchViewModel = searchViewModel,navController) },
        content = { padding ->
            when (searchState.searchStatus) {
                is SearchStatus.Success -> SuccessScreen(searchState = searchState,searchViewModel = searchViewModel,modifier = Modifier.padding(padding), navController = navController)
                is SearchStatus.Error -> ErrorScreen(modifier = Modifier.padding(padding))
                is SearchStatus.Loading -> LoadingScreen(searchState = searchState,modifier = Modifier.padding(padding))
                is SearchStatus.Waiting -> WaitingScreen(modifier = Modifier.padding(padding))
            }
        }
    )
}

@Composable
fun SuccessScreen(searchState: Search, searchViewModel: SearchViewModel, modifier: Modifier = Modifier, navController: NavHostController?=null)
{
    LazyColumn(contentPadding = PaddingValues(16.dp),
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)) {
        itemsIndexed(searchState.definitionList) {index, definition ->
            DefinitionCard(definition = definition,
                bookmarkIsActive = false,
                openFullDefinition = { searchViewModel.changeDefinitionIndex(index=index)
                navController?.navigate(Screen.Definition.route)})
        }
    }
}

@Composable
fun LoadingScreen(searchState: Search, modifier: Modifier = Modifier) {
    var rotation by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(Unit) {
        while (searchState.searchStatus == SearchStatus.Loading) {
            delay(80)
            rotation += 45f
            if (rotation >= 360f) {
                rotation = 0f
            }
        }
    }
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.loading),
            contentDescription = "Загрузка",
            modifier = Modifier.rotate(rotation)
        )
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Определений не найдено", style = textStyle)
    }
}

@Composable
fun WaitingScreen(modifier: Modifier = Modifier)
{
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Введите запрос", style = textStyle)
    }
}

@Composable
fun SearchBar(searchState: Search,searchViewModel: SearchViewModel,navController: NavHostController?=null){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)
        .background(color = Color(0xFF4B4B4B))) {
        Image(painter = painterResource(id = R.drawable.active_bookmark),
            contentDescription = "go to bookmarks",
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .size(32.dp)
                .clickable(onClick = { navController?.navigate(Screen.Bookmarks.route) })
        )
        CustomTextField(title = R.string.search, changeSearchingWord = searchViewModel::changeQueryWord, word = searchState.word)
    }
}