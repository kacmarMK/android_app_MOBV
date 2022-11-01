package com.example.beeranimation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.adapter.ItemAdapter
import com.example.beeranimation.databinding.FragmentListBinding
import com.example.model.JsonData
import com.example.model.PubViewModel


class ListFragment : Fragment(){
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PubViewModel by viewModels()

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
            }*/
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
            }
        }
        if (recyclerView != null) {
            recyclerView.adapter = ItemAdapter(requireContext(), JsonData.enterprises)
        }

        recyclerView?.setHasFixedSize(true)
    }


}