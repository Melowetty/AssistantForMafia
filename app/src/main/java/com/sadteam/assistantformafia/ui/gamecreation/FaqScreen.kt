package com.sadteam.assistantformafia.ui.gamecreation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sadteam.assistantformafia.R
import com.sadteam.assistantformafia.ui.components.MainLayout
import com.sadteam.assistantformafia.ui.theme.*
import java.util.*

@Composable
fun FaqScreen(
    navController: NavController,
) {
    val language = Locale.getDefault()
    if(language.language.equals(Locale("ru", "RU").language)) {
        RuFaqScreen(
            navController = navController
        )
    }
    else {
        EnFaqScreen(
            navController = navController
        )
    }
}

private val baseTextStyle = SpanStyle(
    color = Color.White,
    fontSize = 18.sp,
    fontWeight = FontWeight.Bold,
    fontFamily = primaryFontFamily,
)

@Composable
private fun RuFaqScreen(
    navController: NavController,
) {
    BaseFaqScreen(
        navController = navController
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            softWrap = true,
            textAlign = TextAlign.Left,
            text = buildAnnotatedString {
                withStyle(
                    style = baseTextStyle
                ) {
                    append("Число игроков оптимальное: 8-15 человек.")
                    append("\n")
                }
            }
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            softWrap = true,
            textAlign = TextAlign.Left,
            text = buildAnnotatedString {
                withStyle(
                    style = baseTextStyle
                ) {
                    append(
                        "Карты раздаются участникам игры рубашкой вверх. " +
                                "Получая карту, необходимо незаметно для соседей " +
                                "посмотреть её с целью выяснения собственного статуса. " +
                                "Те, кому досталась карта «"
                    )
                    withStyle(
                        style = SpanStyle(
                            color = CommonRoleTextColor
                        )
                    ) {
                        append("Мирный житель")
                    }
                    append(
                        "», составляют команду незнакомых друг с другом " +
                                "«честных жителей города». Игроки с картами «"
                    )
                    withStyle(
                        style = SpanStyle(
                            color = EnemyRoleTextColor
                        )
                    ) {
                        append("Мафия")
                    }
                    append(
                        "» – команду мафии. Обособленно действуют игроки, " +
                                "которым досталась карта «"
                    )
                    withStyle(
                        style = SpanStyle(
                            color = PeacefulRoleTextColor,
                        )
                    ) {
                        append("Комиссар")
                    }
                    append("», «")
                    withStyle(
                        style = SpanStyle(
                            color = EnemyRoleTextColor,
                        )
                    ) {
                        append("Маньяк")
                    }
                    append("», а также наделённые особыми правами «")
                    withStyle(
                        style = SpanStyle(
                            color = PeacefulRoleTextColor,
                        )
                    ) {
                        append("Доктор")
                    }
                    append("», «")
                    withStyle(
                        style = SpanStyle(
                            color = PeacefulRoleTextColor,
                        )
                    ) {
                        append("Любовница")
                    }
                    append(
                        "». Карта «Ведущий» – определяет ведущего игры, " +
                                "если он не был выбран ранее."
                    )
                    append("\n")
                }
            }
        )
        DayAndNightRow()
        Text(
            modifier = Modifier.fillMaxWidth(),
            softWrap = true,
            textAlign = TextAlign.Left,
            text = buildAnnotatedString {
                withStyle(
                    style = baseTextStyle
                ) {
                    append(
                        "Игровой процесс разделён на две фазы – «день» и " +
                                "«ночь». Когда ведущий объявляет в городе фазу ночи, " +
                                "все игроки закрывают глаза и «спят». В первую ночь " +
                                "ведущий позволяет игрокам, которым выпала карта «"
                    )
                    withStyle(
                        style = SpanStyle(
                            color = EnemyRoleTextColor
                        )
                    ) {
                        append("Мафия")
                    }
                    append(
                        "» открыть глаза. Таким образом, в этот момент " +
                                "происходит знакомство всех членов мафиозной " +
                                "группировки. После этого "
                    )
                    withStyle(
                        style = SpanStyle(
                            color = EnemyRoleTextColor
                        )
                    ) {
                        append("мафия")
                    }
                    append(" «засыпает», а ведущий требует проснуться ")
                    withStyle(
                        style = SpanStyle(
                            color = PeacefulRoleTextColor,
                        )
                    ) {
                        append("комиссара")
                    }
                    append(", ")
                    withStyle(
                        style = SpanStyle(
                            color = PeacefulRoleTextColor,
                        )
                    ) {
                        append("доктора")
                    }
                    append(", ")
                    withStyle(
                        style = SpanStyle(
                            color = PeacefulRoleTextColor,
                        )
                    ) {
                        append("любовницу")
                    }
                    append(" и ")
                    withStyle(
                        style = SpanStyle(
                            color = EnemyRoleTextColor,
                        )
                    ) {
                        append("маньяка")
                    }
                    append(
                        " Таким образом, ведущему становятся известны " +
                                "все игроки."
                    )
                    append("\n")
                }
            }
        )

        RoleIcon(
            painter = painterResource(id = R.drawable.innocent)
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            softWrap = true,
            textAlign = TextAlign.Left,
            text = buildAnnotatedString {
                withStyle(
                    style = baseTextStyle
                ) {
                    append("По объявлению фазы дня просыпаются все ")
                    withStyle(
                        style = SpanStyle(
                            color = CommonRoleTextColor)
                    ) {
                        append("жители")
                    }
                    append(". Днём игроки обсуждают, кто из них " +
                            "может быть причастен к ")
                    withStyle(
                        style = SpanStyle(
                            color = EnemyRoleTextColor)
                    ) {
                        append("мафии")
                    }
                    append(". В конце обсуждений ведущим объявляется " +
                            "открытое голосование. Самый подозрительный " +
                            "(по мнению всех участников) ")
                    withStyle(
                        style = SpanStyle(
                            color = CommonRoleTextColor)
                    ) {
                        append("житель")
                    }
                    append(", набравший большее число голосов, формально " +
                            "отправляется в «тюрьму», а фактически " +
                            "выбывает из игры. Ведущий вскрывает его " +
                            "карту и объявляет его игровой статус.")
                    append("\n")
                }
            }
        )

        RoleIcon(
            painter = painterResource(id = R.drawable.mafia)
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            softWrap = true,
            textAlign = TextAlign.Left,
            text = buildAnnotatedString {
                withStyle(
                    style = baseTextStyle
                ) {
                    append("Затем наступает фаза ночи. Ночью просыпается ")
                    withStyle(
                        style = SpanStyle(
                            color = EnemyRoleTextColor)
                    ) {
                        append("мафия")
                    }
                    append(", беззвучно (жестами) совещается и «убивает» " +
                            "одного из игроков, показывая ведущему, " +
                            "кого именно они выбрали. ")
                    withStyle(
                        style = SpanStyle(
                            color = EnemyRoleTextColor)
                    ) {
                        append("Мафия")
                    }
                    append(" засыпает.")
                    append("\n")
                }
            }
        )

        RoleIcon(
            painter = painterResource(id = R.drawable.comissar)
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            softWrap = true,
            textAlign = TextAlign.Left,
            text = buildAnnotatedString {
                withStyle(
                    style = baseTextStyle
                ) {
                    append("После этого ведущий объявляет о том, что ")
                    withStyle(
                        style = SpanStyle(
                            color = EnemyRoleTextColor)
                    ) {
                        append("мафия")
                    }
                    append(" выбрала свою жертву и будит ")
                    withStyle(
                        style = SpanStyle(
                            color = PeacefulRoleTextColor)
                    ) {
                        append("комиссара")
                    }
                    append(". Просыпается ")
                    withStyle(
                        style = SpanStyle(
                            color = PeacefulRoleTextColor)
                    ) {
                        append("комиссар")
                    }
                    append("и выбирает, кого проверить. За один ход " +
                            "он может проверить только одного игрока. " +
                            "Ведущий кивком головы («Да» – ")
                    withStyle(
                        style = SpanStyle(
                            color = EnemyRoleTextColor)
                    ) {
                        append("мафия")
                    }
                    append("; «Нет» – ")
                    withStyle(
                        style = SpanStyle(
                            color = CommonRoleTextColor)
                    ) {
                        append("мирный")
                    }
                    append(") показывает ")
                    withStyle(
                        style = SpanStyle(
                            color = PeacefulRoleTextColor)
                    ) {
                        append("комиссару")
                    }
                    append(", к какому статусу принадлежит выбранный игрок.")
                    append("\n")
                }
            }
        )

        DoctorHarlotManiacRolesRow()
        Text(
            modifier = Modifier.fillMaxWidth(),
            softWrap = true,
            textAlign = TextAlign.Left,
            text = buildAnnotatedString {
                withStyle(
                    style = baseTextStyle
                ) {
                    append("Для большей интриги в расширенную " +
                            "версию игры добавлены персонажи «")
                    withStyle(
                        style = SpanStyle(
                            color = PeacefulRoleTextColor)
                    ) {
                        append("Доктор")
                    }
                    append("», «")
                    withStyle(
                        style = SpanStyle(
                            color = PeacefulRoleTextColor)
                    ) {
                        append("Любовница")
                    }
                    append("» и «")
                    withStyle(
                        style = SpanStyle(
                            color = EnemyRoleTextColor)
                    ) {
                        append("Маньяк")
                    }
                    append("».")
                    append("\n")
                }
            }
        )

        RoleIcon(
            painter = painterResource(id = R.drawable.maniac)
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            softWrap = true,
            textAlign = TextAlign.Left,
            text = buildAnnotatedString {
                withStyle(
                    style = baseTextStyle
                ) {
                    append("«")
                    withStyle(
                        style = SpanStyle(
                            color = EnemyRoleTextColor)
                    ) {
                        append("Маньяк")
                    }
                    append("» – одинокий некомандный стрелок. " +
                            "Играет сам за себя. Ночью убивает " +
                            "одного игрока. Целью ")
                    withStyle(
                        style = SpanStyle(
                            color = EnemyRoleTextColor)
                    ) {
                        append("маньяка")
                    }
                    append(" является убить всех остальных.")
                    append("\n")
                }
            }
        )

        RoleIcon(
            painter = painterResource(id = R.drawable.harlot)
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            softWrap = true,
            textAlign = TextAlign.Left,
            text = buildAnnotatedString {
                withStyle(
                    style = baseTextStyle
                ) {
                    append("«")
                    withStyle(
                        style = SpanStyle(
                            color = PeacefulRoleTextColor)
                    ) {
                        append("Любовница")
                    }
                    append("» ночью может «заморозить» одного " +
                            "игрока на сутки. В течение этих " +
                            "суток игрок не совершает ходов ночью, " +
                            "не голосует днем и вообще не разговаривает.")
                    append("\n")
                }
            }
        )

        RoleIcon(
            painter = painterResource(id = R.drawable.doctor)
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            softWrap = true,
            textAlign = TextAlign.Left,
            text = buildAnnotatedString {
                withStyle(
                    style = baseTextStyle
                ) {
                    append("«")
                    withStyle(
                        style = SpanStyle(
                            color = PeacefulRoleTextColor)
                    ) {
                        append("Доктор")
                    }
                    append("» просыпается ночью после всех, " +
                            "обладает способностью вылечить «случайного» ")
                    withStyle(
                        style = SpanStyle(
                            color = CommonRoleTextColor)
                    ) {
                        append("жителя")
                    }
                    append(", при этом себя не более 1 раза за " +
                            "игру и одного игрока не более 1 раза подряд.")
                    append("\n")
                }
            }
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            softWrap = true,
            textAlign = TextAlign.Left,
            text = buildAnnotatedString {
                withStyle(
                    style = baseTextStyle
                ) {
                    append("Все игроки «просыпаются» и ведущий объявляет " +
                            "игрока, который стал жертвой ")
                    withStyle(
                        style = SpanStyle(
                            color = EnemyRoleTextColor)
                    ) {
                        append("мафии")
                    }
                    append(" этой ночью. Этот игрок выходит " +
                            "из игры, его статус объявляется всем ")
                    withStyle(
                        style = SpanStyle(
                            color = CommonRoleTextColor)
                    ) {
                        append("жителям")
                    }
                    append(".")
                    append("\n")
                }
            }
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            softWrap = true,
            textAlign = TextAlign.Left,
            text = buildAnnotatedString {
                withStyle(
                    style = baseTextStyle
                ) {
                    append("Информация о произошедших событиях " +
                            "используется оставшимися ")
                    withStyle(
                        style = SpanStyle(
                            color = CommonRoleTextColor)
                    ) {
                        append("жителями")
                    }
                    append(" для очередного обсуждения. Игра " +
                            "продолжается до полной победы одной из " +
                            "команд, когда все ")
                    withStyle(
                        style = SpanStyle(
                            color = EnemyRoleTextColor)
                    ) {
                        append("мафиози")
                    }
                    append(" посажены в тюрьму или убиты все ")
                    withStyle(
                        style = SpanStyle(
                            color = CommonRoleTextColor)
                    ) {
                        append("мирные жители")
                    }
                    append(".")
                }
            }
        )
    }
}

@Composable
private fun EnFaqScreen(
    navController: NavController,
) {
    BaseFaqScreen(
        navController = navController
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            softWrap = true,
            textAlign = TextAlign.Left,
            text = buildAnnotatedString {
                withStyle(
                    style = baseTextStyle
                ) {
                    append("The cards are distributed to each " +
                            "player face-down. After receiving " +
                            "a card players have to look at their " +
                            "cards to see their role but keep them " +
                            "hidden from the other players. Those " +
                            "who got «")
                    withStyle(
                        style = SpanStyle(
                            color = CommonRoleTextColor)
                    ) {
                        append("Innocent")
                    }
                    append("» cards are in the team of honest " +
                            "townspeople unfamiliar with each " +
                            "other. Players with «")
                    withStyle(
                        style = SpanStyle(
                            color = EnemyRoleTextColor)
                    ) {
                        append("Mafia")
                    }
                    append("» cards are in the mafia team. " +
                            "Players who got «")
                    withStyle(
                        style = SpanStyle(
                            color = PeacefulRoleTextColor)
                    ) {
                        append("Detective")
                    }
                    append("»  and «")
                    withStyle(
                        style = SpanStyle(
                            color = EnemyRoleTextColor)
                    ) {
                        append("Maniac")
                    }
                    append("»  cards act separately from " +
                            "mafia and so do players with «")
                    withStyle(
                        style = SpanStyle(
                            color = PeacefulRoleTextColor)
                    ) {
                        append("Doctor")
                    }
                    append("» and «")
                    withStyle(
                        style = SpanStyle(
                            color = PeacefulRoleTextColor)
                    ) {
                        append("Harlot")
                    }
                    append("» cards who also have special abilities. " +
                            "The card “Moderator” appoints someone " +
                            "to mediate the game if such a person " +
                            "wasn’t chosen beforehand.")
                    append("\n")
                }
            }
        )

        DayAndNightRow()

        Text(
            modifier = Modifier.fillMaxWidth(),
            softWrap = true,
            textAlign = TextAlign.Left,
            text = buildAnnotatedString {
                withStyle(
                    style = baseTextStyle
                ) {
                    append("The game process is divided " +
                            "into two cycles: «day» and " +
                            "«night». When the " +
                            "moderator announces the night " +
                            "cycle all players close their " +
                            "eyes and «sleep». During the " +
                            "first night the moderator lets " +
                            "players with «")
                    withStyle(
                        style = SpanStyle(
                            color = EnemyRoleTextColor)
                    ) {
                        append("Mafia")
                    }
                    append("» cards open their eyes. At this " +
                            "moment all players in the mafia " +
                            "team get to see who their teammates " +
                            "are. After that, ")
                    withStyle(
                        style = SpanStyle(
                            color = EnemyRoleTextColor)
                    ) {
                        append("mafia")
                    }
                    append(" «falls asleep» and the moderator instructs ")
                    withStyle(
                        style = SpanStyle(
                            color = PeacefulRoleTextColor)
                    ) {
                        append("the detective")
                    }
                    append(", ")
                    withStyle(
                        style = SpanStyle(
                            color = PeacefulRoleTextColor)
                    ) {
                        append("doctor")
                    }
                    append(", ")
                    withStyle(
                        style = SpanStyle(
                            color = PeacefulRoleTextColor)
                    ) {
                        append("harlot")
                    }
                    append(" and ")
                    withStyle(
                        style = SpanStyle(
                            color = EnemyRoleTextColor)
                    ) {
                        append("maniac")
                    }
                    append(" to open their eyes one by one. " +
                            "That way the moderator learns " +
                            "which role each player has.")
                    append("\n")
                }
            }
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            softWrap = true,
            textAlign = TextAlign.Left,
            text = buildAnnotatedString {
                withStyle(
                    style = baseTextStyle
                ) {
                    append("After the day cycle has been declared, " +
                            "all players wake up. During daytime," +
                            " the players discuss who might be in ")
                    withStyle(
                        style = SpanStyle(
                            color = EnemyRoleTextColor)
                    ) {
                        append("the mafia")
                    }
                    append(" team. After the discussion " +
                            "the moderator announces an " +
                            "open voting. The most " +
                            "suspicious (according to " +
                            "what other players think) player " +
                            "who gets the most votes " +
                            "technically goes to jail and " +
                            "in fact gets eliminated. The " +
                            "moderator shows the eliminated " +
                            "player’s card and declares " +
                            "their role. ")
                    append("\n")
                }
            }
        )

        RoleIcon(
            painter = painterResource(id = R.drawable.mafia)
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            softWrap = true,
            textAlign = TextAlign.Left,
            text = buildAnnotatedString {
                withStyle(
                    style = baseTextStyle
                ) {
                    append("After that, the night cycle begins. " +
                            "During the night ")
                    withStyle(
                        style = SpanStyle(
                            color = EnemyRoleTextColor)
                    ) {
                        append("mafia")
                    }
                    append(" members wake up, soundlessly " +
                            "(using gestures) confer and " +
                            "«kill» one player, pointing " +
                            "out their choice to the " +
                            "moderator. ")
                    withStyle(
                        style = SpanStyle(
                            color = EnemyRoleTextColor)
                    ) {
                        append("Mafia")
                    }
                    append(" falls asleep. ")
                    append("\n")
                }
            }
        )

        RoleIcon(
            painter = painterResource(id = R.drawable.comissar)
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            softWrap = true,
            textAlign = TextAlign.Left,
            text = buildAnnotatedString {
                withStyle(
                    style = baseTextStyle
                ) {
                    append("Next, the moderator announces that ")
                    withStyle(
                        style = SpanStyle(
                            color = EnemyRoleTextColor)
                    ) {
                        append("the mafia")
                    }
                    append(" has chosen their victim and wakes up the ")
                    withStyle(
                        style = SpanStyle(
                            color = PeacefulRoleTextColor)
                    ) {
                        append("detective")
                    }
                    append(". ")
                    withStyle(
                        style = SpanStyle(
                            color = PeacefulRoleTextColor)
                    ) {
                        append("The detective")
                    }
                    append(" wakes up and chooses a player to " +
                            "check. Only one player can be " +
                            "checked during the detective’s " +
                            "turn. The moderator nods if the " +
                            "player is a mafia member and " +
                            "shakes their head if the guess " +
                            "was incorrect indicating whether " +
                            "the chosen player was a killer or not. ")
                    append("\n")
                }
            }
        )

        DoctorHarlotManiacRolesRow()
        Text(
            modifier = Modifier.fillMaxWidth(),
            softWrap = true,
            textAlign = TextAlign.Left,
            text = buildAnnotatedString {
                withStyle(
                    style = baseTextStyle
                ) {
                    append("To make the process more " +
                            "intriguing, to the extended " +
                            "version of the game were added " +
                            "such roles as the «")
                    withStyle(
                        style = SpanStyle(
                            color = PeacefulRoleTextColor)
                    ) {
                        append("Doctor")
                    }
                    append("», «")
                    withStyle(
                        style = SpanStyle(
                            color = PeacefulRoleTextColor)
                    ) {
                        append("Harlot")
                    }
                    append("» and «")
                    withStyle(
                        style = SpanStyle(
                            color = EnemyRoleTextColor)
                    ) {
                        append("Maniac")
                    }
                    append("».")
                    append("\n")
                }
            }
        )

        RoleIcon(
            painter = painterResource(id = R.drawable.maniac)
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            softWrap = true,
            textAlign = TextAlign.Left,
            text = buildAnnotatedString {
                withStyle(
                    style = baseTextStyle
                ) {
                    append("The «")
                    withStyle(
                        style = SpanStyle(
                            color = EnemyRoleTextColor
                        )
                    ) {
                        append("Maniac")
                    }
                    append("» is a lone solo player. " +
                            "He decides who to kill on his " +
                            "own. During the night the maniac " +
                            "kills one player. His goal is to " +
                            "kill all the other players.")
                    append("\n")
                }
            }
        )

        RoleIcon(
            painter = painterResource(id = R.drawable.harlot)
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            softWrap = true,
            textAlign = TextAlign.Left,
            text = buildAnnotatedString {
                withStyle(
                    style = baseTextStyle
                ) {
                    append("The «")
                    withStyle(
                        style = SpanStyle(
                            color = PeacefulRoleTextColor
                        )
                    ) {
                        append("Harlot")
                    }
                    append("» can block one player for one " +
                            "turn. During that turn the " +
                            "chosen player cannot use their " +
                            "abilities at night and cannot " +
                            "vote or even talk during the day. " +
                            "The player also cannot be voted for.")
                    append("\n")
                }
            }
        )

        RoleIcon(
            painter = painterResource(id = R.drawable.doctor)
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            softWrap = true,
            textAlign = TextAlign.Left,
            text = buildAnnotatedString {
                withStyle(
                    style = baseTextStyle
                ) {
                    append("The «")
                    withStyle(
                        style = SpanStyle(
                            color = PeacefulRoleTextColor
                        )
                    ) {
                        append("Doctor")
                    }
                    append("» wakes up last and has the " +
                            "ability to heal any player. " +
                            "However, the doctor cannot heal " +
                            "himself more than once per game " +
                            "and cannot heal another player " +
                            "twice in a row.")
                    append("\n")
                }
            }
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            softWrap = true,
            textAlign = TextAlign.Left,
            text = buildAnnotatedString {
                withStyle(
                    style = baseTextStyle
                ) {
                    append("All players wake up and the " +
                            "moderator declares the victim of ")
                    withStyle(
                        style = SpanStyle(
                            color = EnemyRoleTextColor
                        )
                    ) {
                        append("the mafia")
                    }
                    append(" who has been killed that " +
                            "night. The victim gets eliminated " +
                            "and their role is announced to " +
                            "the other players.")
                    append("\n")
                }
            }
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            softWrap = true,
            textAlign = TextAlign.Left,
            text = buildAnnotatedString {
                withStyle(
                    style = baseTextStyle
                ) {
                    append("The information obtained " +
                            "after the night cycle " +
                            "is used by the surviving " +
                            "players for another discussion. " +
                            "The game continues until all ")
                    withStyle(
                        style = SpanStyle(
                            color = EnemyRoleTextColor
                        )
                    ) {
                        append("the mafia")
                    }
                    append(" members are put in jail " +
                            "or until all ")
                    withStyle(
                        style = SpanStyle(
                            color = CommonRoleTextColor
                        )
                    ) {
                        append("the innocent")
                    }
                    append(" townspeople are killed. ")
                    append("\n")
                }
            }
        )
    }
}

@Composable
private fun BaseFaqScreen(
    navController: NavController,
    content: @Composable () -> Unit,
) {
    MainLayout(
        navController = navController,
        title = stringResource(id = R.string.faq),
        backgroundColor = FaqBackgroundColor,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}

@Composable
private fun DayAndNightRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 50.dp,
                end = 50.dp,
                bottom = 25.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.35f),
            painter = painterResource(id = R.drawable.sun_large),
            contentDescription = ""
        )
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f),
            painter = painterResource(id = R.drawable.moon),
            contentDescription = ""
        )
    }
}

@Composable
private fun DoctorHarlotManiacRolesRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 30.dp,
                end = 30.dp,
                bottom = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        RoleIcon(
            painter = painterResource(id = R.drawable.doctor)
        )
        RoleIcon(
            painter = painterResource(id = R.drawable.harlot)
        )
        RoleIcon(
            painter = painterResource(id = R.drawable.maniac)
        )
    }
}

@Composable
private fun RoleIcon(
    painter: Painter
) {
    Image(
        painter = painter,
        modifier = Modifier
            .size(64.dp)
            .padding(bottom = 15.dp),
        contentDescription = null,
    )
}