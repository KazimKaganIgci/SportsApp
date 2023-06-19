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
import com.kazim.betteryou.Adapter.FoodAdapter
import com.kazim.betteryou.Repository.Status
import com.kazim.betteryou.ViewModels.MainViewModel
import com.kazim.betteryou.databinding.FragmentChickenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChickenFragment : Fragment() {
    private lateinit var binding: FragmentChickenBinding
    private val viewModel: MainViewModel by viewModels()
    private var adapter: FoodAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        binding =FragmentChickenBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //When clicking the button, different list pull request and show in adapter
        viewModel.getChickenList()
        viewModel.chickenMealLivedata.observe(viewLifecycleOwner, Observer {

        })

        viewModel.chickenMealLivedata.observe(viewLifecycleOwner, Observer {
            when (it.status) {

                Status.SUCCESS -> {
                    adapter = FoodAdapter(it.data!!.meals)
                    Toast.makeText(requireActivity(),"Success", Toast.LENGTH_LONG).show()
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(),it.message ?: "Error", Toast.LENGTH_LONG).show()
                }

                Status.LOADING -> {

                }


            }
            binding.recylerView.layoutManager= LinearLayoutManager(activity)
            binding.recylerView.adapter=adapter
            adapter?.notifyDataSetChanged()
        })
    }
}