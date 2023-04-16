package com.simphiwe.footballapp.view.players

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simphiwe.footballapp.MainActivity
import com.simphiwe.footballapp.common.UIState
import com.simphiwe.footballapp.databinding.FragmentPlayersBinding
import com.simphiwe.footballapp.data.model.player.PlayersResponse
import com.simphiwe.footballapp.data.model.player.Response
import com.simphiwe.footballapp.view.players.playerlist.PlayersAdapter
import com.simphiwe.footballapp.view.players.viewmodel.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayersFragment : Fragment() {

    private lateinit var playersAdapter : PlayersAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var binding: FragmentPlayersBinding
    private lateinit var playersRecyclerView: RecyclerView
    private lateinit var errorText: TextView
    var team: Int = 50
    var season: Int = 2022


    private val viewModel by viewModels<PlayerViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchPlayers(team, season)
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).mainToolbar.isGone = false
        requireActivity().title = "Players"

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPlayersBinding.inflate(inflater, container, false)
        binding.let {
            playersRecyclerView = it.recyclerView
            progressBar = it.progressBar
            errorText = it.errorText

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        errorText.visibility = View.GONE
        playersAdapter = PlayersAdapter()
        playersAdapter.setOnItemClickListener(object : PlayersAdapter.OnItemClickListener{
            override fun onItemClick(response: Response, position: Int) {
                val dialogBuilder = AlertDialog.Builder(context)

                dialogBuilder.setMessage(
                    "Red Cards: " + response.statistics[0].cards.red
                            + " - Yellow Cards: " + response.statistics[0].cards.yellow
                )
                    .setCancelable(false)
                    .setNegativeButton("Close") { dialog, _ ->
                        dialog.cancel()
                    }

                val alert = dialogBuilder.create()
                alert.setTitle("Cards")
                alert.show()


                println("working onclick")
            }

        })
        playersRecyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        playersRecyclerView.addItemDecoration(decoration)
        playersRecyclerView.adapter = playersAdapter


        viewModel.getPlayerViewState.observe(viewLifecycleOwner) { playerState ->
            when (playerState) {
                is UIState.Success<*> -> {
                    errorText.visibility = View.GONE
                    progressBar.visibility = View.GONE

                    // Safely cast playerState.result to PlayersResponse
                    val playersResponse = playerState.result as? PlayersResponse

                    // Check if playersResponse is not null
                    if (playersResponse != null) {
                        playersAdapter.setUpdatedData(playersResponse.response)
                    } else {
                        // Handle the null case, e.g., show an error message or log the error
                        errorText.visibility = View.VISIBLE
                        errorText.text = "Failed to load player data"
                    }
                }
                is UIState.Failure -> {
                    playerState.errorText
                    progressBar.visibility = View.GONE
                    errorText.visibility = View.VISIBLE
                }
                UIState.Loading -> {
                    errorText.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE
                }
                UIState.Empty -> TODO()
            }
        }
    }
}