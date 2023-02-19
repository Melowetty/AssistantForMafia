package com.sadteam.assistantformafia

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.sadteam.assistantformafia.ui.MainActivity
import com.sadteam.assistantformafia.ui.gamecreation.GameCreationScreen
import com.sadteam.assistantformafia.ui.gamecreation.SelectPlayersCountTags
import org.junit.Rule
import org.junit.Test

class GameCreationScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()


    @Test
    fun testPlayersCountSelect() {
        composeTestRule.setContent {
            GameCreationScreen()
        }

        composeTestRule.onNode(hasTestTag(SelectPlayersCountTags.OPENING_BUTTON), useUnmergedTree = true)
            .assertIsDisplayed()
            .performClick()

        composeTestRule.onNode(hasTestTag(SelectPlayersCountTags.BOX), useUnmergedTree = true)
            .assertExists()

        composeTestRule.onNode(hasTestTag(SelectPlayersCountTags.VALUE), useUnmergedTree = true)
            .assertExists()
            .assertTextEquals("1")

        composeTestRule.onNode(hasTestTag(SelectPlayersCountTags.ADD), useUnmergedTree = true)
            .assertExists()
            .performClick()
            .performClick()

        composeTestRule.onNode(hasTestTag(SelectPlayersCountTags.VALUE), useUnmergedTree = true)
            .assertExists()
            .assertTextEquals("3")

        composeTestRule.onNode(hasTestTag(SelectPlayersCountTags.REMOVE), useUnmergedTree = true)
            .assertExists()
            .performClick()
            .performClick()
            .performClick()
            .performClick()

        composeTestRule.onNode(hasTestTag(SelectPlayersCountTags.VALUE), useUnmergedTree = true)
            .assertExists()
            .assertTextEquals("1")

        composeTestRule.onNode(hasTestTag(SelectPlayersCountTags.SAVE), useUnmergedTree = true)
            .assertExists()
            .performClick()

        composeTestRule.onNode(hasTestTag(SelectPlayersCountTags.BOX), useUnmergedTree = true)
            .assertDoesNotExist()

    }
}