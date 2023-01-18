package com.simphiwe.footballapp.view.fragment.players.playerlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simphiwe.footballapp.R
import com.simphiwe.footballapp.view.fragment.players.playerlist.adapter.RecyclerViewAdapter
import com.simphiwe.footballapp.view.fragment.players.viewmodel.PlayerViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [PlayerListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlayerListFragment : Fragment() {

    private lateinit var recyclerAdapter : RecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_player_list, container, false)
        initViewModel(view)
        initViewModel()
        return view
    }

    private fun initViewModel(view: View){
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)

        recyclerAdapter = RecyclerViewAdapter()
        recyclerView.adapter = recyclerAdapter
    }


    private fun initViewModel() {
        val viewModel = ViewModelProvider(this)[PlayerViewModel::class.java]
        viewModel.getPlayerListObserver().observe(viewLifecycleOwner, Observer {
            if (it != null){
                //recyclerAdapter.setUpdatedData(it.name)
                println(it.response)
            } else{
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall()
    }
    companion object {
        @JvmStatic
        fun newInstance() =
            PlayerListFragment()
    }
}