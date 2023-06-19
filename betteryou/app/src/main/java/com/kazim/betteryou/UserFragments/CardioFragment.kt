package com.kazim.betteryou.UserFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kazim.betteryou.Adapter.CardioAdapter
import com.kazim.betteryou.R
import com.kazim.betteryou.Repository.Status
import com.kazim.betteryou.ViewModels.MainViewModel
import com.kazim.betteryou.databinding.FragmentCardioBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardioFragment : Fragment() {
    private lateinit var binding:FragmentCardioBinding
    private val viewModel: MainViewModel by viewModels()
    private var adapter: CardioAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentCardioBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //find request from viewmodel
        viewModel.getCardioList("cardio","beginner")



        //Send adapter data list and show it in recylerview
        viewModel.getCardioLiveData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    adapter = CardioAdapter(it.data!!)
                    Toast.makeText(requireActivity(),"Success", Toast.LENGTH_LONG).show()
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(),it.message ?: "Error", Toast.LENGTH_LONG).show()
                }

                Status.LOADING -> {

                }


            }
            binding.cardirecyler.layoutManager= LinearLayoutManager(activity)
            binding.cardirecyler.adapter=adapter
        })


        //When clicking the button, different list pull request and show in adapter
        binding.beginnerBtn.setOnClickListener {
            viewModel.getCardioList("cardio","beginner")
            adapter?.notifyDataSetChanged()

        }

        //When clicking the button, different list pull request and show in adapter
        binding.intermediateBtn.setOnClickListener {
            viewModel.getCardioList("cardio","intermediate")
            adapter?.notifyDataSetChanged()
        }
    }
}