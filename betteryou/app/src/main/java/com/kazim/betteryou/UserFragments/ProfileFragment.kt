package com.kazim.betteryou.UserFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kazim.betteryou.ActivityPages.MainActivity
import com.kazim.betteryou.ActivityPages.SecondActivity
import com.kazim.betteryou.ActivityPages.ThirdActivity
import com.kazim.betteryou.R
import com.kazim.betteryou.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

class ProfileFragment : Fragment() {
    private lateinit var binding:FragmentProfileBinding
    private lateinit var fstore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
       binding=FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fstore = Firebase.firestore
        auth= Firebase.auth


        //get data from firebase from firestore
        val ref =fstore.collection("Users").document(auth.currentUser!!.uid)
        ref.get().addOnSuccessListener {
           binding.nametext.text= "${it.getString("name").toString()+" "+it.getString("surname")}"
            binding.imageView.setImageResource(R.drawable.build)
        }



        //Redirecting to other pages with buttons
        binding.exitbtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent =Intent(context,MainActivity::class.java)
            startActivity(intent)
        }
        binding.answerBtn.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToAnswerFragment())
        }
        binding.supplementbtn.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToSupplementFragment())

        }
        binding.foodBtn.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToChickenFragment())
        }
        binding.blogBtn.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToBlogFragment())

        }
        binding.noteBtn.setOnClickListener {
            findNavController().navigate((ProfileFragmentDirections.actionProfileFragmentToNoteFragment()))
        }




    }




}