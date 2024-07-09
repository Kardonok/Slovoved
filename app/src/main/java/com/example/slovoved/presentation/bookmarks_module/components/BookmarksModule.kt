package com.example.slovoved.presentation.bookmarks_module.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavHostController
import com.example.slovoved.R
import com.example.slovoved.presentation.universal_components.CustomTextField
import com.example.slovoved.presentation.universal_components.DefinitionCard


@Preview(showBackground = true)
@Composable
fun SearchDefinitionsBarPreview()
{
    SearchDefinitionsBar()
}

@Preview(showBackground = true)
@Composable
fun BookmarkModulePreview()
{
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
    BookmarksModule(definitionList=definitionList)
}

@Composable
fun BookmarksModule(definitionList: Array<String>,navController: NavHostController?=null) {
    Scaffold(bottomBar = { SearchDefinitionsBar(navController=navController) },
        content = {padding->
            LazyColumn(contentPadding = PaddingValues(16.dp),
                modifier = Modifier.padding(padding),
                verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(definitionList) { definition ->
                    DefinitionCard(definition = definition, bookmarkIsActive = false, openFullDefinition = { })
                }
            }
        }
    )
}

@Composable
fun SearchDefinitionsBar(navController: NavHostController?=null){

    var isPoppingBackStack by remember { mutableStateOf(false) }

    Row(verticalAlignment = Alignment.CenterVertically,modifier = Modifier.fillMaxWidth().height(80.dp).background(color = Color(0xFF4B4B4B))) {
        Image(painter = painterResource(id = R.drawable.arrow_left_white),
            contentDescription = "go to bookmarks",
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                .size(32.dp)
                .clickable(onClick = { if(!isPoppingBackStack){
                    isPoppingBackStack=true
                    navController?.popBackStack()} })
        )
        CustomTextField(word = "", changeSearchingWord = { },title = R.string.search_for_definitions)
    }
}
