package com.viktoriagavrosh.interview.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.viktoriagavrosh.interview.ui.screens.menu.MenuScreen

/**
 * Composable with navigation between app screens
 *
 * @param modifier the modifier to be applied to the layout
 * @param navController the navController for this host
 */
@Composable
fun InterviewNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = TopicMenu,
    ) {
        composable<TopicMenu> {
            MenuScreen(
                modifier = modifier,
                onGameButtonClick = { navController.navigate(Game(topicId = 0)) },
                onAddButtonClick = { navController.navigate(EditTopic(topicId = 0)) },
                onEditButtonClick = { id -> navController.navigate(EditTopic(topicId = id)) },
                onMenuItemClick = { id -> navController.navigate(QuestionMenu(topicId = id)) },
            )
        }
        composable<QuestionMenu> { backStackEntry ->
            val topicId = backStackEntry.toRoute<QuestionMenu>().topicId
            MenuScreen(
                modifier = modifier,
                topicId = topicId,
                onGameButtonClick = { id -> navController.navigate(Game(topicId = id)) },
                onBackClick = { navController.navigateUp() },
                onAddButtonClick = { navController.navigate(EditQuestion(questionId = 0)) },
                onEditButtonClick = { id -> navController.navigate(EditTopic(topicId = id)) },
                onMenuItemClick = { id -> navController.navigate(QuestionDetail(questionId = id)) },
            )
        }
        composable<Game> {

        }
        composable<QuestionDetail> {

        }
        composable<EditQuestion> {

        }
        composable<EditTopic> {

        }
    }
}
