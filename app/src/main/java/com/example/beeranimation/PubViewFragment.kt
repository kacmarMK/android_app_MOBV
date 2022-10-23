package com.example.beeranimation


import android.animation.Animator
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.airbnb.lottie.LottieAnimationView
import com.example.beeranimation.databinding.FragmentPubViewBinding

class PubViewFragment : Fragment() {
    private var _binding: FragmentPubViewBinding? = null
    private val binding get() = _binding!!
    private val args: PubViewFragmentArgs by navArgs()
    private var name = "Meno"
    private var company = "Podnik"

    var SEARCH_PREFIX = "https://www.google.com/maps/search/?api=1&query="


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPubViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        name = args.name
        company = args.company

        binding.textViewName.text = name
        binding.textViewCompany.text = company


        val animation: LottieAnimationView = view.findViewById(R.id.animationView)
        animation.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {}
            override fun onAnimationEnd(animator: Animator) {
                animation.progress = 0F
            }
            override fun onAnimationCancel(animator: Animator) {}
            override fun onAnimationRepeat(animator: Animator) {}
        })

        animation.setOnClickListener {
            animation.playAnimation()
        }

        val btn: Button = view.findViewById(R.id.buttonConfirm)
        btn.setOnClickListener{
            val queryUrl: Uri = Uri.parse(SEARCH_PREFIX + args.latitude + "%2C" + args.longitude)
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            context?.startActivity(intent)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}