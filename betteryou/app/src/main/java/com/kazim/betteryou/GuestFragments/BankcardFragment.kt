package com.kazim.betteryou.GuestFragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kazim.betteryou.ActivityPages.SecondActivity
import com.kazim.betteryou.databinding.FragmentBankcardBinding


class BankcardFragment : Fragment() {
    private lateinit var binding:FragmentBankcardBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var fstore: FirebaseFirestore
    lateinit var name:String
    lateinit var surname:String
    lateinit var password:String
    lateinit var email:String
    lateinit var user:String
    lateinit var cardnumber:String
    lateinit var month:String
    lateinit var year:String
    lateinit var cvv:String
    lateinit var cardholderName:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding=FragmentBankcardBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        fstore = Firebase.firestore
        val currentUser =auth.currentUser

        //retrieving user data from firebase
        if (currentUser!=null){
            val ref =fstore.collection("Users").document(currentUser.uid)
            ref.get().addOnSuccessListener {
                if (it !=null){
                    name = it.data!!["name"].toString()
                    surname  = it.data!!["surname"].toString()
                    password = it.data!!["password"].toString()
                    email = it.data!!["email"].toString()
                }
            }
        }




        //Saving bankcard data with firabase firestore
        binding.btn.setOnClickListener {
            if (binding.nameEdittext.text.isNotEmpty()){
                cardholderName=binding.nameEdittext.text.toString()
            }

            if (binding.et1.text.isNotEmpty()&&binding.et2.text.isNotEmpty()&&binding.et3.text.isNotEmpty()&&binding.et4.text.isNotEmpty()){
                cardnumber ="${binding.et1.text}${binding.et2.text}${binding.et3.text}${binding.et4.text}"
            }
            if (binding.monthEdittext.text.isNotEmpty()){
                month=binding.monthEdittext.text.toString()
            }
            if (binding.yearEdittext.text.isNotEmpty()){
                year=binding.yearEdittext.text.toString()
            }
            if (binding.cvvEdittext.text.isNotEmpty()){
                cvv=binding.cvvEdittext.text.toString()
            }

            if (cvv.isNotEmpty() &&year.isNotEmpty()&&year.isNotEmpty()&&cardnumber.isNotEmpty()&&cardholderName.isNotEmpty()){
                val userInfo:HashMap<String,Any> = hashMapOf("name" to name,"surname" to surname.toString(),"email" to email,"password" to password,"user" to "Member")
                if (currentUser!=null){
                    val ref =fstore.collection("Users").document(currentUser.uid)
                        .update(userInfo)
                    val cardInfo =fstore.collection("CardNumber").document(currentUser.uid)
                    val card:HashMap<String,Any> = hashMapOf("cardNumber" to cardnumber,"month" to month,"year" to year,"cvv" to cvv," cardholderName" to  cardholderName)
                    cardInfo.set(card)
                    Toast.makeText(context, "Üye oldunuz Tebrikler :)", Toast.LENGTH_SHORT).show()

                    val intent = Intent(requireContext(), SecondActivity::class.java)
                    startActivity(intent)
                }
            }else{
                Toast.makeText(context, "Bilgileri eksik girdiniz.", Toast.LENGTH_SHORT).show()

            }

            }




        }

    }


