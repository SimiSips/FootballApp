package com.simphiwe.footballapp

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.simphiwe.footballapp.common.Resource
import com.simphiwe.footballapp.data.api.PlayerApi
import com.simphiwe.footballapp.data.model.player.*
import com.simphiwe.footballapp.repository.players.PlayerRepositoryImpl
import com.simphiwe.footballapp.view.players.viewmodel.PlayerViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class PlayerViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    lateinit var playerViewModel: PlayerViewModel
    lateinit var playerRepository: PlayerRepositoryImpl

    @Mock
    lateinit var playerApiService: PlayerApi

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        playerRepository = PlayerRepositoryImpl(playerApiService)
        playerViewModel = PlayerViewModel(playerRepository)

    }

    @Test
    fun getAllPlayersTest() {
        runBlocking {
            Mockito.`when`(playerRepository.getPlayers(39, 2022))
                .thenReturn(Resource.Success(playerRepository.getPlayers(39, 2022).data))
            playerViewModel.fetchPlayers(39, 2022)
            val result = playerViewModel.getPlayerList.value
            assertEquals(playerRepository.getPlayers(39, 2022).data, result)
        }
    }

    @Test
    fun `empty player list test`() {
        runBlocking {
            Mockito.`when`(playerRepository.getPlayers(0, 0))
                .thenReturn(Resource.Error(""))
            playerViewModel.fetchPlayers(0, 0)
            val result = playerViewModel.getPlayerList.value
            assertEquals(playerRepository.getPlayers(0, 0).data, result)
            println(result)
        }
    }
    //Player(21, Birth("England", "1987-01-26", "Place"), "Phil", "167cm", "12", false, "Foden", "Phil", "England", "https:\\/\\/media.api-sports.io\\/football\\/players\\/2825.png", "89kg"))

}