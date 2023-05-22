package com.sadteam.assistantformafia

import com.sadteam.assistantformafia.data.Converters
import com.sadteam.assistantformafia.data.models.Possibility
import com.sadteam.assistantformafia.data.models.RoleType
import com.sadteam.assistantformafia.utils.Utils
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class UnitTests {
    @Test
    fun checkConvertationSingleListPossibilities() {
        val data = listOf(Possibility.NONE)
        assertEquals(Converters().fromPossibilities(data), "NONE")
        assertEquals(Converters().toPossibilities("NONE"), data)
    }

    @Test
    fun checkConvertationNormalListPossibilities() {
        val data = listOf(Possibility.NONE, Possibility.KILL, Possibility.CHECK_ROLE)
        assertEquals(Converters().fromPossibilities(data), "NONE,KILL,CHECK_ROLE")
        assertEquals(Converters().toPossibilities("NONE,KILL,CHECK_ROLE"), data)
    }

    @Test
    fun checkConvertationNullListPossibilities() {
        val data: List<Possibility> = listOf()
        assertEquals(Converters().fromPossibilities(data), "")
        assertEquals(Converters().toPossibilities(""), data)
    }

    @Test
    fun checkConvertingNullTranslateMap() {
        val data = mapOf<Locale, String>(
        )
        assertEquals(Converters().fromTranslatorMap(data), "")
        assertEquals(Converters().toTranslatorMap(""), data)
    }

    @Test
    fun checkConvertingSingleTranslateMap() {
        val data = mapOf<Locale, String>(
            Pair(Locale("ru", "RU"), "Mafia")
        )
        assertEquals(Converters().fromTranslatorMap(data), "ru_RU:Mafia")
        assertEquals(Converters().toTranslatorMap("ru_RU:Mafia"), data)
    }

    @Test
    fun checkConvertingActuallyTranslateMap() {
        val data = mapOf<Locale, String>(
            Pair(Locale("ru", "RU"), "Mafia"),
            Pair(Locale("en", "US"), "Comissar")
        )
        assertEquals(Converters().fromTranslatorMap(data), "ru_RU:Mafia|en_US:Comissar")
        assertEquals(Converters().toTranslatorMap("ru_RU:Mafia|en_US:Comissar"), data)
    }

    @Test
    fun checkConvertingRoleType() {
        val roleType = "COMMON"
        assertEquals(Converters().fromRoleType(roleType = RoleType.COMMON), roleType)
        assertEquals(Converters().toRoleType(roleType), RoleType.COMMON)
    }

    @Test
    fun checkNormalStringForGetColoredMessage() {
        val str = "[Mafia] won"
        val result = Utils.getColoredMessage(str)
        val expected = Utils.Companion.ColoredMessage(colored = "Mafia",
            message = " won")
        assertEquals(expected, result)
    }

    @Test
    fun checkEmptyStringForGetColoredMessage() {
        val str = ""
        val result = Utils.getColoredMessage(str)
        val expected = Utils.Companion.ColoredMessage(colored = "",
            message = "")
        assertEquals(expected, result)
    }
}