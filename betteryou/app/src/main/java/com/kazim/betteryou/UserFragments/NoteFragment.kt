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
import com.kazim.betteryou.Adapter.BlogAdapter
import com.kazim.betteryou.Adapter.NoteAdapter
import com.kazim.betteryou.Data.Blog
import com.kazim.betteryou.Data.Note
import com.kazim.betteryou.R
import com.kazim.betteryou.databinding.FragmentBlogBinding
import com.kazim.betteryou.databinding.FragmentNoteBinding


class NoteFragment : Fragment() {
    private lateinit var binding: FragmentNoteBinding
    private lateinit var fstore: FirebaseFirestore
    private lateinit var noteList:ArrayList<Note>
    private lateinit var nadapter: NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentNoteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fstore = Firebase.firestore


        noteList =ArrayList<Note>()
        fstore = Firebase.firestore
        getData()



        //Send adapter data list and show it in recylerview
        binding.recylerView.layoutManager= LinearLayoutManager(activity)
        nadapter= NoteAdapter(noteList)
        binding.recylerView.adapter=nadapter
    }




    //getting data from firestore firabase
    private fun getData(){
        fstore.collection("Not").addSnapshotListener { value, error ->
            if(error!=null){
                Toast.makeText(context,error.toString(), Toast.LENGTH_LONG).show()
            }else{
                if (value !=null){
                    if(!value.isEmpty){
                        val docs =value.documents
                        for (doc in docs) {
                            val content = doc.get("content") as String
                            val post = Note(content)
                            noteList.add(post)
                        }
                        nadapter.notifyDataSetChanged() } } }

        }

    }


}