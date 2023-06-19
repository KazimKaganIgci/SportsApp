package com.kazim.betteryou.GuestFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kazim.betteryou.ActivityPages.SecondActivity
import com.kazim.betteryou.ActivityPages.ThirdActivity
import com.kazim.betteryou.R
import com.kazim.betteryou.databinding.FragmentGuestSubscriptionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GuestSubscriptionFragment : Fragment() {
   private lateinit var binding:FragmentGuestSubscriptionBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var fstore: FirebaseFirestore
    lateinit var name:String
    lateinit var surname:String
    lateinit var password:String
    lateinit var email:String
    lateinit var user:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding=FragmentGuestSubscriptionBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        val currentUser =auth.currentUser

        val gold = binding.goldBox
        val dia =binding.diabox
        val plat=binding.platbox

        //the algorithm of selecting 1 of the checkboxes
        gold.setOnClickListener {
            if (gold.isChecked){
                dia.isChecked =false
                plat.isChecked=false
            }

        }
        plat.setOnClickListener {
            if (plat.isChecked){
                dia.isChecked =false
                gold.isChecked=false
            }

        }
        dia.setOnClickListener {
            if (dia.isChecked){
                gold.isChecked =false
                plat.isChecked=false
            }

        }

        //Redirect to bankcardfragment
        binding.premiumBtn.setOnClickListener {
            if (gold.isChecked ||plat.isChecked ||dia.isChecked){

                if (currentUser!=null){
                    findNavController().navigate(GuestSubscriptionFragmentDirections.actionGuestSubscriptionFragmentToBankcardFragment2())
                }
            }else{
                Toast.makeText(context, "Bir üyelik seçin", Toast.LENGTH_SHORT).show()
            }


        }







    }



}