package com.simphiwe.footballapp

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.simphiwe.footballapp.common.Resource
import com.simphiwe.footballapp.data.api.PlayerApi
import com.simphiwe.footballapp.repository.players.PlayerRepositoryImpl
import com.simphiwe.footballapp.repository.teams.TeamsRepositoryImpl
import com.simphiwe.footballapp.view.players.viewmodel.PlayerViewModel
import com.simphiwe.footballapp.view.teams.viewmodel.TeamsViewModel
import junit.framework.TestCase
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

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class TeamsViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    lateinit var teamsViewModel: TeamsViewModel
    lateinit var teamsRepositoryImpl: TeamsRepositoryImpl

    @Mock
    lateinit var teamsApi: PlayerApi

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        teamsRepositoryImpl = TeamsRepositoryImpl(teamsApi)
        teamsViewModel = TeamsViewModel(teamsRepositoryImpl)

    }

    @Test
    fun getAllPlayersTest() {
        runBlocking {
            Mockito.`when`(teamsRepositoryImpl.getTeams("England"))
                .thenReturn(Resource.Success(teamsRepositoryImpl.getTeams("England").data))
            teamsViewModel.fetchTeams("England")
            val result = teamsViewModel.getCountryList.value
            TestCase.assertEquals(teamsRepositoryImpl.getTeams("England").data, result)
        }
    }
    //Player(21, Birth("England", "1987-01-26", "Place"), "Phil", "167cm", "12", false, "Foden", "Phil", "England", "https:\\/\\/media.api-sports.io\\/football\\/players\\/2825.png", "89kg"))

}