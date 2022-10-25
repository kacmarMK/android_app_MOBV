package com.example.beeranimation

import android.animation.Animator
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.airbnb.lottie.LottieAnimationView
import com.example.beeranimation.databinding.FragmentDetailBinding
import com.example.beeranimation.databinding.FragmentPubViewBinding
import com.example.model.JsonData


class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()

    private var name = ""
    private var latitude = ""
    private var longitude = ""
    private var webpage = ""
    private var telNum = ""
    private var openedHrs = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name = args.pubName
        latitude = args.pubLatitude
        longitude = args.pubLongitude
        webpage = args.pubWebpage
        telNum = args.pubTelNum
        openedHrs = args.pubOpenedHrs
        val id = args.pubPos

        binding.textViewPubName.text = "Meno podniku: " + name
        binding.textViewPubLatitude.text = "Zem. šírka: " + latitude
        binding.textViewPubLongitude.text = "Zem. dĺžka: " + longitude
        binding.textViewPubWebpage.text = "Web: " + webpage
        binding.textViewPubTelNum.text = "Tel. číslo: " + telNum
        binding.textViewPubOpenHrs.text = "Otváracie hodiny: " + openedHrs

        binding.apply {
            buttonDelete.setOnClickListener {
                deleteItem(id)
                val direction = DetailFragmentDirections.actionDetailFragmentToListFragment()
                findNavController().navigate(direction)
            }
        }

    }

    private fun deleteItem(position: Int) {
        JsonData.enterprises.removeAt(position)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}