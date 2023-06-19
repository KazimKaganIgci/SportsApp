package com.kazim.betteryou.UserFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kazim.betteryou.Adapter.BodyAdapter
import com.kazim.betteryou.Adapter.CardioAdapter
import com.kazim.betteryou.Adapter.FoodAdapter
import com.kazim.betteryou.R
import com.kazim.betteryou.Repository.Status
import com.kazim.betteryou.ViewModels.MainViewModel
import com.kazim.betteryou.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(){
    private lateinit var binding:FragmentHomeBinding
    private val viewModel: MainViewModel by viewModels()
    private var cardioadapter: BodyAdapter?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        binding=FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //Selecting a spinner item
        var list = arrayOf("abdominals","abductors","adductors","biceps","calves","chest","forearms","glutes","hamstrings","lats","lower_back","middle_back","neck","quadriceps","traps","triceps")
        val adapter:ArrayAdapter<CharSequence> = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter=adapter
        viewModel.getMuscleList("chest")

        binding.spinner.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.getMuscleList(list[position])
                adapter.notifyDataSetChanged()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }


        //When clicking the button, different list pull request and show in adapter
        viewModel.getMuscleLiveData.observe(viewLifecycleOwner, Observer {
            when (it.status) {

                Status.SUCCESS -> {
                    cardioadapter = BodyAdapter(it.data!!)
                    Toast.makeText(requireActivity(),"Success", Toast.LENGTH_LONG).show()
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(),it.message ?: "Error", Toast.LENGTH_LONG).show()
                }

                Status.LOADING -> {

                }


            }
            binding.recyler.layoutManager= LinearLayoutManager(activity)
            binding.recyler.adapter=cardioadapter
            adapter.notifyDataSetChanged()
        })


    }





}