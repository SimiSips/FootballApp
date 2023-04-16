package com.simphiwe.footballapp.repository.players
import com.simphiwe.footballapp.common.Resource
import com.simphiwe.footballapp.data.api.PlayerApi
import com.simphiwe.footballapp.data.model.player.*
import com.simphiwe.footballapp.data.model.topscorers.TopScorersResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import retrofit2.Response

class PlayerRepositoryImplTest {

    private lateinit var playerApi: PlayerApi
    private lateinit var playerRepository: PlayerRepositoryImpl

    @Before
    fun setUp() {
        playerApi = Mockito.mock(PlayerApi::class.java)
        playerRepository = PlayerRepositoryImpl(playerApi)
    }

    @Test
    fun `test getPlayers success`() = runBlocking {
        val team = 1
        val season = 2023
        val errors = listOf<Any>()
        val get = "players"
        val paging = Paging(current = 1, total = 1)
        val parameters = Parameters(id = "1", season = season.toString())

        val player = Player(
            21,
            Birth("England", "1987-01-26", "Place"),
            "Phil",
            "167cm",
            12,
            false,
            "Foden",
            "Phil",
            "England",
            "https://media.api-sports.io/football/players/2825.png",
            "89kg"
        )

        val statistic = Statistic(
            Cards(red = 0, yellow = 2, yellowred = 0),
            Dribbles(attempts = 25, past = 15, success = 20),
            Duels(total = 50, won = 30),
            Fouls(committed = 10, drawn = 5),
            Games(
                appearences = 20,
                captain = false,
                lineups = 18,
                minutes = 1500,
                number = 47,
                position = "Midfielder",
                rating = "7.5"
            ),
            Goals(assists = 5, conceded = 0, saves = 0, total = 10),
            League(
                country = "England",
                flag = "https://media.api-sports.io/flags/gb.svg",
                id = 1,
                logo = "https://media.api-sports.io/football/leagues/1.png",
                name = "Premier League",
                season = season
            ),
            Passes(accuracy = 85, key = 50, total = 1000),
            Penalty(commited = 1, missed = 1, saved = 0, scored = 2, won = 2),
            Shots(on = 30, total = 60),
            Substitutes(bench = 2, inX = 2, out = 0),
            Tackles(blocks = 10, interceptions = 15, total = 25),
            Team(id = 1, logo = "https://media.api-sports.io/football/teams/1.png", name = "Team A")
        )

        val responseList = listOf(Response(player, listOf(statistic)))

        val results = responseList.size
        val expectedResponse =
            PlayersResponse(errors, get, paging, parameters, responseList, results)
        val response = Response.success(expectedResponse)

        `when`(playerApi.getPlayers(team, season)).thenReturn(response)

        val result = playerRepository.getPlayers(team, season)

        assertTrue(result is Resource.Success && expectedResponse == result.data)
    }

    @Test
    fun `test getTopScorers success`() = runBlocking {
        val league = 1
        val season = 2023
        val errors = listOf<Any>()
        val get = "topscorers"
        val paging = Paging(current = 1, total = 1)
        val parameters = Parameters(id = "1", season = season.toString())

        val player = Player(
            21,
            Birth("England", "1987-01-26", "Place"),
            "Phil",
            "167cm",
            12,
            false,
            "Foden",
            "Phil",
            "England",
            "https://media.api-sports.io/football/players/2825.png",
            "89kg"
        )
        val statistic = Statistic(
            Cards(red = 0, yellow = 2, yellowred = 0),
            Dribbles(attempts = 25, past = 15, success = 20),
            Duels(total = 50, won = 30),
            Fouls(committed = 10, drawn = 5),
            Games(
                appearences = 20,
                captain = false,
                lineups = 18,
                minutes = 1500,
                number = 47,
                position = "Midfielder",
                rating = "7.5"
            ),
            Goals(assists = 5, conceded = 0, saves = 0, total = 10),
            League(
                country = "England",
                flag = "https://media.api-sports.io/flags/gb.svg",
                id = 1,
                logo = "https://media.api-sports.io/football/leagues/1.png",
                name = "Premier League",
                season = season
            ),
            Passes(accuracy = 85, key = 50, total = 1000),
            Penalty(commited = 0, missed = 1, saved = 0, scored = 2, won = 3),
            Shots(on = 30, total = 60),
            Substitutes(bench = 2, inX = 2, out = 0),
            Tackles(blocks = 10, interceptions = 15, total = 25),
            Team(id = 1, logo = "https://media.api-sports.io/football/teams/1.png", name = "Team A")
        )
        val responseList = listOf(Response(player, listOf(statistic)))

        val results = responseList.size
        val expectedResponse =
            TopScorersResponse(errors, get, paging, parameters, responseList, results)
        val response = Response.success(expectedResponse)

        `when`(playerApi.getTopScorers(league, season)).thenReturn(response)

        val result = playerRepository.getTopScorers(league, season)

        assertTrue(result is Resource.Success && expectedResponse == result.data)
    }
}

    //TODO: Add cases for error
