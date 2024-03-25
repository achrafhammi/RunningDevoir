package com.example.racetracker.ui

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Test


class RaceParticipantTest{
    private val raceParticipant =
        RaceParticipant(name = "Player 1", progressIncrement = 1)
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun raceParticipant_RaceStarted_ProgressUpdate() = runTest {
        val expectedProgress = 1
        launch {raceParticipant.run()}
        advanceTimeBy(raceParticipant.progressDelayMillis)
        runCurrent()
        assertEquals(expectedProgress, raceParticipant.currentProgress)
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun raceParticipant_RaceFinished_ProgressUpdated() = runTest {
        launch{ raceParticipant.run() }
        advanceTimeBy(raceParticipant.progressDelayMillis * raceParticipant.maxProgress)
        runCurrent()
        assertEquals(100, raceParticipant.currentProgress)

    }
}