package com.example.ui_controllers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.PubsApplication
import com.example.adapter.PubListAdapter
import com.example.beeranimation.R
import com.example.beeranimation.databinding.FragmentListBinding
import com.example.view_model.PubViewModel
import com.example.view_model.PubViewModelFactory


class ListFragment : Fragment(){
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val pubViewModel: PubViewModel by viewModels {
        PubViewModelFactory((activity?.application as PubsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var sorted = false

        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view?.findViewById<RecyclerView>(R.id.recycler_view)
        binding.apply {
            /*buttonCreateNew.setOnClickListener {
                val direction = ListFragmentDirections.actionListFragmentToFormFragment()
                findNavController().navigate(direction)
            }
            btnSort.setOnClickListener{
                sorted = if (sorted) {
                    JsonData.enterprises?.sortByDescending { it.tags.name }
                    false
                } else {
                    JsonData.enterprises?.sortBy { it.tags.name }
                    true
                }

                if (recyclerView != null) {
                    recyclerView.adapter?.notifyDataSetChanged()
                }
            }*/
        }
        if (recyclerView != null) {
            //recyclerView.adapter = ItemAdapter(requireContext(), JsonData.enterprises)
            val adapter = PubListAdapter()
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context)
            pubViewModel.allPubs.observe(viewLifecycleOwner, Observer { pubs ->
                pubs?.let { adapter.submitList(it) }
            })

        }

        recyclerView?.setHasFixedSize(true)
    }


}