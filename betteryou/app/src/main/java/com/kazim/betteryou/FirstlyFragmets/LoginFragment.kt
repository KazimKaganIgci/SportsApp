package com.kazim.betteryou.FirstlyFragmets

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
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
import com.kazim.betteryou.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var fstore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        fstore = Firebase.firestore
        val currentUser =auth.currentUser


        //Transfer user to member or guest page
       if (currentUser!=null){
            val id =auth.currentUser!!.uid
            val ref =fstore.collection("Users").document(id)
            ref.get().addOnSuccessListener {
                if (it !=null){
                    if(it.getString("user") =="Guest"){
                        val intent =Intent(context,ThirdActivity::class.java)
                        startActivity(intent)

                    }
                    if(it.getString("user")=="Member"){
                        val intent =Intent(context,SecondActivity::class.java)
                        startActivity(intent)
                    }

                }

            }

        }




        //redirect to registration page
        binding.registerbtn.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        //Firebase sign in
        binding.loginbtn.setOnClickListener {
            val email = binding.emailtext.text.toString()
            val password =binding.passwordText.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                    checkUserAccessLevel(it.user!!.uid)
                    Toast.makeText(context, "Giriş yapılıyor", Toast.LENGTH_SHORT).show()

                }.addOnFailureListener {
                    Toast.makeText(context, it.localizedMessage, Toast.LENGTH_LONG)
                        .show()
                }
            }else{
                Toast.makeText(context, "Enter E-mail and Password", Toast.LENGTH_LONG).show()

            }
        }

    }

    //Transfer user to member or guest page
    private fun checkUserAccessLevel(uid: String) {
        val rf =fstore.collection("Users").document(uid)
        rf.get().addOnSuccessListener {
            if (it !=null){
                if(it.getString("user") =="Guest"){
                    val intent =Intent(context,ThirdActivity::class.java)
                    startActivity(intent)

                }
                if(it.getString("user")=="Member"){
                    val intent =Intent(context,SecondActivity::class.java)
                    startActivity(intent)
                }

            }

        }
    }
}







