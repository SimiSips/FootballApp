package com.simphiwe.footballapp.view.teams

import android.os.Bundle
import android.view.*
import android.widget.ProgressBar

import androidx.appcompat.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isNotEmpty
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simphiwe.footballapp.MainActivity
import com.simphiwe.footballapp.R
import com.simphiwe.footballapp.common.UIState
import com.simphiwe.footballapp.databinding.FragmentTeamsBinding
import com.simphiwe.footballapp.view.teams.teamslist.TeamsAdapter
import com.simphiwe.footballapp.data.model.teams.TeamsResponse
import com.simphiwe.footballapp.view.teams.viewmodel.TeamsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamsFragment : Fragment() {

    private lateinit var teamsAdapter: TeamsAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var binding: FragmentTeamsBinding
    private lateinit var teamsRecyclerView: RecyclerView
    private lateinit var errorText: TextView
    private lateinit var searchView: SearchView

    private val viewModel by viewModels<TeamsViewModel>()

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
            searchView = it.searchView

        }
        return binding.root
    }


    /*private fun filter(text: String) {
        val filteredlist: ArrayList<Response> = ArrayList()

        for (item in teamsList) {
            if (item.courseName.toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(context, "No Country Found..", Toast.LENGTH_SHORT).show()
        } else {
            teamsAdapter.filterList(filteredlist)
        }
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        errorText.visibility = View.GONE
        progressBar.visibility = View.GONE
        teamsAdapter = TeamsAdapter()
        teamsRecyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        teamsRecyclerView.addItemDecoration(decoration)
        teamsRecyclerView.adapter = teamsAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                    if (query.isNotEmpty()){
                        viewModel.fetchTeams(query)
                        teamsRecyclerView.visibility = View.VISIBLE
                    }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        searchView.setOnCloseListener {
            teamsAdapter.clear()
            teamsRecyclerView.visibility = View.GONE
            errorText.visibility = View.GONE
            false
        }
        searchView.setOnSearchClickListener {
            if (it.findViewById<SearchView>(R.id.searchView).isNotEmpty()){
                errorText.visibility = View.VISIBLE
                errorText.text = getString(R.string.errorSearch)
            }
        }


        viewModel.getTeamsViewState.observe(viewLifecycleOwner){ teamsState ->
            when(teamsState){
                is UIState.Success<*> -> {
                    errorText.visibility = View.GONE
                    progressBar.visibility = View.GONE

                    teamsState.result as TeamsResponse
                    if (teamsState.result.results == 0){
                        errorText.text = getString(R.string.noCountry)
                        errorText.visibility = View.VISIBLE
                        Toast.makeText(context,getString(R.string.noCountry),Toast.LENGTH_SHORT).show()
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
