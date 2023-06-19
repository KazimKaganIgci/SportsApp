package com.kazim.betteryou.UserFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kazim.betteryou.Adapter.MessageAdapter
import com.kazim.betteryou.Adapter.SupplementAdapter
import com.kazim.betteryou.Data.Message
import com.kazim.betteryou.Data.SuppData
import com.kazim.betteryou.R
import com.kazim.betteryou.databinding.FragmentAnswerBinding
import com.kazim.betteryou.databinding.FragmentSupplementBinding


class SupplementFragment : Fragment() {
    private lateinit var binding: FragmentSupplementBinding
    private lateinit var fstore: FirebaseFirestore
    private lateinit var supplementList:ArrayList<SuppData>
    private lateinit var sadapter: SupplementAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =FragmentSupplementBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        supplementList =ArrayList<SuppData>()
        fstore = Firebase.firestore
        getData()

        //Send adapter data list and show it in recylerview
        binding.recylerView.layoutManager= LinearLayoutManager(activity)
        sadapter= SupplementAdapter(supplementList)
        binding.recylerView.adapter=sadapter
    }




    //getting data from firestore firabase
    private fun getData(){
        fstore.collection("Supplement").addSnapshotListener { value, error ->
            if(error!=null){
                Toast.makeText(context,error.toString(), Toast.LENGTH_LONG).show()
            }else{
                if (value !=null){
                    if(!value.isEmpty){
                        val docs =value.documents
                        for (doc in docs) {
                            val name = doc.get("name") as String
                            val content = doc.get("content") as String
                            val post = SuppData(name, content)
                            supplementList.add(post)
                        }
                        sadapter.notifyDataSetChanged() } } }

        }

    }

}