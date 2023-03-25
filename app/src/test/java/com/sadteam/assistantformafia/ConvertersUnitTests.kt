package com.sadteam.assistantformafia

import com.sadteam.assistantformafia.data.models.Possibility
import com.sadteam.assistantformafia.utils.Converters
import org.junit.Assert.assertEquals
import org.junit.Test

class ConvertersUnitTests {
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
}