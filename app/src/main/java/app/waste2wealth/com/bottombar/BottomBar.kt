package app.waste2wealth.com.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import app.waste2wealth.com.navigation.Screens
import app.waste2wealth.com.ui.theme.appBackground
import app.waste2wealth.com.ui.theme.monteSB
import app.waste2wealth.com.ui.theme.textColor

@Composable
fun BottomBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination
    Card(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = appBackground,
        elevation = 5.dp,
        shape = RoundedCornerShape(17.dp)
    ) {
        BottomNavigation(
            modifier = Modifier
                .padding(12.dp, 0.dp, 12.dp, 0.dp)
                .height(80.dp),
            elevation = 0.dp,
            backgroundColor = appBackground
        ) {
            items.forEach {
                val isSelected = currentRoute?.hierarchy?.any { nav ->
                    nav.route == it.route
                } == true
                BottomNavigationItem(
                    icon = {
                        it.icon?.let { icon ->
                            Icon(
                                painter = painterResource(id = icon),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(35.dp)
                                    .padding(bottom = 5.dp),
                                tint = if (isSelected) Color.White else textColor
                            )
                        }
                    },
                    label = {
                        it.title?.let { title ->
                            Text(
                                text = title,
                                color = if (isSelected) Color.White else textColor,
                                softWrap = true,
                                fontFamily = monteSB,
                                fontSize = 10.sp
                            )
                        }
                    },
                    selected = isSelected,
                    selectedContentColor = Color.Yellow,
                    unselectedContentColor = Color.White.copy(alpha = 0.4f),
                    modifier = Modifier
                        .background(if (isSelected) textColor else Color.White)
                        .clip(RoundedCornerShape(17.dp)),
                    onClick = {
                        it.route?.let { route ->
                            navController.navigate(route) {
                                popUpTo(Screens.Dashboard.route) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    }
}