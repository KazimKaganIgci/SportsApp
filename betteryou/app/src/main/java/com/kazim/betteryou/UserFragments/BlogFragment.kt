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
import com.kazim.betteryou.Adapter.SupplementAdapter
import com.kazim.betteryou.Data.Blog
import com.kazim.betteryou.Data.SuppData

import com.kazim.betteryou.databinding.FragmentBlogBinding


class BlogFragment : Fragment() {
    private lateinit var binding: FragmentBlogBinding
    private lateinit var fstore: FirebaseFirestore
    private lateinit var blogList:ArrayList<Blog>
    private lateinit var badapter: BlogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding= FragmentBlogBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fstore = Firebase.firestore
        blogList =ArrayList<Blog>()
        fstore = Firebase.firestore

        getData()

        //Send adapter data list and show it in recylerview
        binding.recylerView.layoutManager= LinearLayoutManager(activity)
        badapter= BlogAdapter(blogList)
        binding.recylerView.adapter=badapter
    }



    //getting data from firestore firabase
    private fun getData(){
        fstore.collection("Blog").addSnapshotListener { value, error ->
            if(error!=null){
                Toast.makeText(context,error.toString(), Toast.LENGTH_LONG).show()
            }else{
                if (value !=null){
                    if(!value.isEmpty){
                        val docs =value.documents
                        for (doc in docs) {
                            val title = doc.get("title") as String
                            val content = doc.get("content") as String


                            val post = Blog(title, content)
                            blogList.add(post)
                        }
                        badapter.notifyDataSetChanged() } } }

        }

    }


    }

