package com.kazim.betteryou.GuestFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.kazim.betteryou.ActivityPages.MainActivity
import com.kazim.betteryou.R
import com.kazim.betteryou.databinding.FragmentGuestHomeBinding
import dagger.hilt.android.AndroidEntryPoint

class GuestHomeFragment : Fragment() {
    private lateinit var binding:FragmentGuestHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding=FragmentGuestHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //Guest check-out
        binding.btn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent =Intent(requireContext(),MainActivity::class.java)
            startActivity(intent)
        }
    }



}