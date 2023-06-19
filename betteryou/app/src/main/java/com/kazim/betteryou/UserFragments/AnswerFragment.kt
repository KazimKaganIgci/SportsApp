package com.kazim.betteryou.UserFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kazim.betteryou.Adapter.MessageAdapter
import com.kazim.betteryou.Data.Message
import com.kazim.betteryou.databinding.FragmentAnswerBinding


class AnswerFragment : Fragment() {
    private lateinit var binding:FragmentAnswerBinding
    private lateinit var fstore: FirebaseFirestore
    private lateinit var messageList:ArrayList<Message>
    private lateinit var adapter: MessageAdapter
  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding =FragmentAnswerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fstore = Firebase.firestore
        messageList =ArrayList<Message>()

        getData()


        //Send adapter data list and show it in recylerview
        binding.recylerView.layoutManager= LinearLayoutManager(activity)
        adapter= MessageAdapter(messageList)
        binding.recylerView.adapter=adapter





    }
    //getting data from firestore firabase
    private fun getData(){
        fstore.collection("Posts").addSnapshotListener { value, error ->
            if(error!=null){
                Toast.makeText(context,error.toString(), Toast.LENGTH_LONG).show()
            }else{
                if (value !=null){
                    if(!value.isEmpty){
                        messageList.clear()

                        val docs =value.documents
                        for (doc in docs) {
                            val message = doc.get("message") as String
                            val answer = doc.get("answer") as String


                            val post = Message(message, answer)
                            messageList.add(post)

                        }
                        adapter.notifyDataSetChanged()


                    }


                }
            }

        }

    }

}