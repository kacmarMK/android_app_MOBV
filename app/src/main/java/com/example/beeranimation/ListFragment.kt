package com.example.beeranimation

import android.content.ClipData.Item
import android.content.res.AssetManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adapter.ItemAdapter
import com.example.adapter.PubNameClickListener
import com.example.beeranimation.databinding.ActivityMainBinding
import com.example.beeranimation.databinding.FragmentFormBinding
import com.example.beeranimation.databinding.FragmentListBinding
import com.example.model.Enterprises
import com.example.model.Pub
import com.google.gson.Gson
import java.util.Collections


class ListFragment : Fragment(){
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!


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
        super.onViewCreated(view, savedInstanceState)

        val jsonStr = context?.assets?.readFile("pubs.json")
        val dataset = Gson().fromJson(jsonStr, Enterprises::class.java)



        val recyclerView = view?.findViewById<RecyclerView>(R.id.recycler_view)
        binding.apply {
            buttonCreateNew.setOnClickListener {
                val direction = ListFragmentDirections.actionListFragmentToFormFragment()
                findNavController().navigate(direction)
            }
            btnSort.setOnClickListener{
                dataset.elements?.sortBy { it.tags.name }
                if (recyclerView != null) {
                    recyclerView.adapter?.notifyDataSetChanged()
                }
            }
        }
        if (recyclerView != null) {
            recyclerView.adapter = context?.let { dataset.elements?.let { it1 -> ItemAdapter(it, it1) } }
        }

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true)
        }




    }
    fun AssetManager.readFile(fileName: String) = open(fileName)
        .bufferedReader()
        .use { it.readText() }


}