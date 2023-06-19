package com.kazim.betteryou.FirstlyFragmets

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
import com.kazim.betteryou.ActivityPages.MainActivity
import com.kazim.betteryou.ActivityPages.ThirdActivity
import com.kazim.betteryou.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var fstore: FirebaseFirestore
    private var valid:Boolean = false
    private lateinit var binding:FragmentRegisterBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

      }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding=FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth =Firebase.auth
        fstore =Firebase.firestore


        //Sign up with Firebase
        binding.registerbtn.setOnClickListener {
            val email = binding.emailtext.text.toString()
            val password =binding.passwordText.text.toString()
            val name =binding.nametext.text.toString()
            val surname =binding.surnametext.text.toString()
                    if (email.isNotEmpty()&&password.isNotEmpty()&&name.isNotEmpty()&&surname.isNotEmpty()){
                        auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                            val user =auth.currentUser
                            if (user !=null){
                                val reference =fstore.collection("Users").document(user.uid)
                                val userInfo:HashMap<String,Any> = hashMapOf("name" to name,"surname" to surname.toString(),"email" to email,"password" to password,"user" to "Guest")
                                reference.set(userInfo)
                                Toast.makeText(context, "Giriş yapılıyor", Toast.LENGTH_SHORT).show()

                                val intent =Intent(context,ThirdActivity::class.java)
                                startActivity(intent)

                            }
                        }.addOnFailureListener {
                            Toast.makeText(context, "Lutfen boş yer bırakmayiniz!", Toast.LENGTH_SHORT).show()
                        }
                    }

                }







        //redirect to login page
        binding.loginbtn.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }















    }






}