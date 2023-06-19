package com.kazim.betteryou.UserFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kazim.betteryou.databinding.FragmentMessageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessageFragment : Fragment() {
    private lateinit var binding: FragmentMessageBinding
    private lateinit var fstore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var message:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding=FragmentMessageBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fstore = Firebase.firestore
        auth=Firebase.auth

        buttonClick()
    }

    //saving firestore message data
    private fun buttonClick() {
        binding.messageBtn.setOnClickListener {
            message = binding.messageText.text.toString()
            val messageInfo: HashMap<String, Any> = hashMapOf("message" to message, "answer" to "Cevaplanmadı")
                val ref = fstore.collection("Posts").add(messageInfo).addOnSuccessListener {
                    Toast.makeText(context, "Mesaj gönderildi", Toast.LENGTH_SHORT).show()
                    binding.messageText.setText("")
                }.addOnFailureListener {
                    Toast.makeText(context, "Mesaj gönderilemedi", Toast.LENGTH_SHORT).show()
                } } }


}