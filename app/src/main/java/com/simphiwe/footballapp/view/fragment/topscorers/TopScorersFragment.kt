package com.simphiwe.footballapp.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isGone
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simphiwe.footballapp.MainActivity
import com.simphiwe.footballapp.R
import com.simphiwe.footballapp.common.UIState
import com.simphiwe.footballapp.databinding.FragmentPlayersBinding
import com.simphiwe.footballapp.databinding.FragmentTopScorersBinding
import com.simphiwe.footballapp.view.fragment.players.model.PlayersResponse
import com.simphiwe.footballapp.view.fragment.players.model.Response
import com.simphiwe.footballapp.view.fragment.players.playerlist.PlayersAdapter
import com.simphiwe.footballapp.view.fragment.players.viewmodel.PlayerViewModel
import com.simphiwe.footballapp.view.fragment.topscorers.adapter.TopScorersAdapter
import com.simphiwe.footballapp.view.fragment.topscorers.model.TopScorersResponse
import com.simphiwe.footballapp.view.fragment.topscorers.viewmodel.TopScorersViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TopScorersFragment : Fragment() {

    private lateinit var topScorersAdapter: TopScorersAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var binding: FragmentTopScorersBinding
    private lateinit var topScorersRecyclerView: RecyclerView
    private lateinit var errorText: TextView
    var league: Int = 39
    var season: Int = 2022


    private val viewModel by viewModels<TopScorersViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchTopScorers(league, season)
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).mainToolbar.isGone = false
        requireActivity().title = "Top Scorers in Premier Legue"

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTopScorersBinding.inflate(inflater, container, false)
        binding.let {
            topScorersRecyclerView = it.recyclerView
            progressBar = it.progressBar
            errorText = it.errorText

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        errorText.visibility = View.GONE
        topScorersAdapter = TopScorersAdapter()
        topScorersRecyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        topScorersRecyclerView.addItemDecoration(decoration)
        topScorersRecyclerView.adapter = topScorersAdapter


        viewModel.getTopScorersViewState.observe(viewLifecycleOwner){ topScorersState ->
            when(topScorersState){
                is UIState.Success<*> -> {
                    errorText.visibility = View.GONE
                    progressBar.visibility = View.GONE
                    topScorersState.result as TopScorersResponse
                    topScorersAdapter.setUpdatedData(topScorersState.result.response)


                }
                UIState.Empty -> TODO()
                is UIState.Failure -> {
                    topScorersState.errorText
                    progressBar.visibility = View.GONE
                    errorText.visibility = View.VISIBLE
                }
                UIState.Loading -> {
                    errorText.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE
                }
            }

        }
    }
}