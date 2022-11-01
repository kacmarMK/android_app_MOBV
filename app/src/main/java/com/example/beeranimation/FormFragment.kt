package com.example.beeranimation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.beeranimation.databinding.FragmentFormBinding


class FormFragment : Fragment()  {
    private var _binding: FragmentFormBinding? = null
    private val binding get() = _binding!!
    var navc: NavController ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            buttonConfirm.setOnClickListener {
                val direction = FormFragmentDirections.actionFormFragmentToMainViewFragment(editTextName.text.toString(),
                                                                                            editTextCompany.text.toString(),
                                                                                            editTextLatitude.text.toString(),
                                                                                            editTextLongitude.text.toString())



                findNavController().navigate(direction)
            }
        }
    }





}