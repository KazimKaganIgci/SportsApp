package com.kazim.betteryou.GuestFragments

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
import com.kazim.betteryou.Adapter.MessageAdapter
import com.kazim.betteryou.Data.Message
import com.kazim.betteryou.Data.SportResponse
import com.kazim.betteryou.R
import com.kazim.betteryou.Repository.Status
import com.kazim.betteryou.ViewModels.MainViewModel
import com.kazim.betteryou.databinding.FragmentGuestExampleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GuestExampleFragment : Fragment() {
   private lateinit var binding:FragmentGuestExampleBinding
    private val viewModel: MainViewModel by viewModels()
    private var adapter:CardioAdapter ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding =FragmentGuestExampleBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTypeList("cardio")



        //Send cardio data list to adapter and list in recylerview
        viewModel.getTypeLiveData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    adapter = CardioAdapter(it.data!!)
                    Toast.makeText(requireActivity(),"Success",Toast.LENGTH_LONG).show()
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(),it.message ?: "Error",Toast.LENGTH_LONG).show()
                }

                Status.LOADING -> {

                }


            }
            binding.recylerView.layoutManager=LinearLayoutManager(activity)
            binding.recylerView.adapter=adapter
        })


    }

}