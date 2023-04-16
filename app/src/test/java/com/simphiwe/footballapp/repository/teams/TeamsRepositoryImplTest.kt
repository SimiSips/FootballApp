import com.simphiwe.footballapp.common.Resource
import com.simphiwe.footballapp.data.api.PlayerApi
import com.simphiwe.footballapp.data.model.search.Paging
import com.simphiwe.footballapp.data.model.search.Parameters
import com.simphiwe.footballapp.data.model.search.Response
import com.simphiwe.footballapp.data.model.search.SearchResponse
import com.simphiwe.footballapp.data.model.teams.Team
import com.simphiwe.footballapp.data.model.teams.TeamsResponse
import com.simphiwe.footballapp.data.model.teams.Venue
import com.simphiwe.footballapp.repository.teams.TeamsRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
class TeamsRepositoryImplTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var api: PlayerApi

    private lateinit var repository: TeamsRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = TeamsRepositoryImpl(api)
    }

    @Test
    fun `test getCountry success`() = runBlocking {
        val country = "England"
        val responseList = listOf(
            Response(code = "ENG", flag = "https://flag.url", name = "England")
        )
        val searchResponse = SearchResponse(
            errors = emptyList(),
            get = "countries",
            paging = Paging(current = 1, total = 1),
            parameters = Parameters(country),
            response = responseList,
            results = 1
        )

        val response = retrofit2.Response.success(searchResponse)

        `when`(api.getCountry(country)).thenReturn(response)

        val result = repository.getCountry(country)

        Assert.assertTrue(result is Resource.Success && searchResponse == result.data)
    }

    @Test
    fun `test getTeams success`() = runBlocking(testDispatcher) {
        val search = "Manchester United"
        val team = Team(
            code = "MUN",
            country = "England",
            founded = 1878,
            id = 1,
            logo = "https://logo.url",
            name = "Manchester United",
            national = false
        )
        val venue = Venue(
            address = "Old Trafford",
            capacity = 75000,
            city = "Manchester",
            id = 1,
            image = "https://venue.url",
            name = "Old Trafford",
            surface = "Grass"
        )
        val responseList = listOf(
            com.simphiwe.footballapp.data.model.teams.Response(team = team, venue = venue)
        )
        val teamsResponse = TeamsResponse(
            errors = emptyList(),
            get = "teams",
            paging = com.simphiwe.footballapp.data.model.teams.Paging(current = 1, total = 1),
            parameters = com.simphiwe.footballapp.data.model.teams.Parameters(search = search),
            response = responseList,
            results = 1
        )
        val response = retrofit2.Response.success(teamsResponse)
        `when`(api.getTeams(search)).thenReturn(response)
        val result = repository.getTeams(search)

        Assert.assertTrue(result is Resource.Success && teamsResponse == result.data)
    }
}

//TODO: Add cases for error
