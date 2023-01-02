package com.example.apprickymorty.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.apprickymorty.R
import com.example.apprickymorty.databinding.FragmentListBinding
import com.example.apprickymorty.repository.Repository
import com.example.apprickymorty.view.adapter.CharacterAdapter
import com.example.apprickymorty.viewmodel.SharedViewModel
import com.example.apprickymorty.viewmodel.SharedViewModelFactory

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel:SharedViewModel by activityViewModels { SharedViewModelFactory(
        Repository()
    )  }
    private var adapter = CharacterAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getCharactersFromViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
        sharedViewModel.listCharacters.observe(viewLifecycleOwner, {response ->
            if(response.isSuccessful){
                adapter.setCharacters(response.body()!!.results)
                txtApiError.visibility = View.GONE
                recycleview.visibility = View.VISIBLE
//                Log.d("Result", response.body()!!.results.toString())
            }else{
                txtApiError.text = getString(R.string.text_error,response.code())
                txtApiError.visibility = View.VISIBLE
                recycleview.visibility = View.INVISIBLE
//                Log.d("ResultError", response.code().toString())
            }
        })

            recycleview.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            recycleview.adapter = adapter

            btnFilter.setOnClickListener {
                findNavController().navigate(R.id.action_listFragment_to_filterFragment)
            }
            sharedViewModel.isFilter.observe(viewLifecycleOwner, {
                titleActionReset.visibility = if (it) View.VISIBLE else View.INVISIBLE
            })

            titleActionReset.setOnClickListener {
                getCharactersFromViewModel()
                sharedViewModel.filterValue.value = arrayOf(0,0)
            }
        }

        getNameSearchView()

    }

    private fun getNameSearchView(){
        binding.apply {
            searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                sharedViewModel.getByName(query.toString())
                searchview.setQuery("", false)
                searchview.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    } }

    private fun getCharactersFromViewModel() {
        sharedViewModel.getCharacters(1)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}