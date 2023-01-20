package com.simphiwe.footballapp.view.fragment.teams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simphiwe.footballapp.MainActivity
import com.simphiwe.footballapp.R
import com.simphiwe.footballapp.common.UIState
import com.simphiwe.footballapp.databinding.FragmentTeamsBinding
import com.simphiwe.footballapp.view.fragment.teams.adapter.TeamsAdapter
import com.simphiwe.footballapp.view.fragment.teams.teamslist.model.TeamsResponse
import com.simphiwe.footballapp.view.fragment.teams.viewmodel.TeamsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamsFragment : Fragment() {

    private lateinit var teamsAdapter: TeamsAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var binding: FragmentTeamsBinding
    private lateinit var teamsRecyclerView: RecyclerView
    private lateinit var errorText: TextView
    var country: String = "eng"



    private val viewModel by viewModels<TeamsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewModel.fetchCountry(country)
        viewModel.fetchTeams(country)
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).mainToolbar.isGone = false
        requireActivity().title = "Teams"

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTeamsBinding.inflate(inflater, container, false)
        binding.let {
            teamsRecyclerView = it.recyclerView
            progressBar = it.progressBar
            errorText = it.errorText

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        errorText.visibility = View.GONE
        teamsAdapter = TeamsAdapter()
        teamsRecyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        teamsRecyclerView.addItemDecoration(decoration)
        teamsRecyclerView.adapter = teamsAdapter


        viewModel.getTeamsViewState.observe(viewLifecycleOwner){ teamsState ->
            when(teamsState){
                is UIState.Success<*> -> {
                    errorText.visibility = View.GONE
                    progressBar.visibility = View.GONE

                    teamsState.result as TeamsResponse
                    if (teamsState.result.results == 0){
                        errorText.text = getString(R.string.noCountry)
                        errorText.visibility = View.VISIBLE
                        Toast.makeText(context,getString(R.string.noCountry),Toast.LENGTH_SHORT).show();
                    } else {
                        teamsAdapter.setUpdatedData(teamsState.result.response)
                    }
                }
                UIState.Empty -> TODO()
                is UIState.Failure -> {
                    teamsState.errorText

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