package com.kazim.betteryou.UserFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kazim.betteryou.Data.Food
import com.kazim.betteryou.Data.Message
import com.kazim.betteryou.R
import com.kazim.betteryou.databinding.FragmentFoodBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

class FoodFragment : Fragment() {
    private lateinit var binding: FragmentFoodBinding
    private lateinit var fstore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var breakfastList:ArrayList<Food>
    private lateinit var lunchList:ArrayList<Food>
    private lateinit var dinnerList:ArrayList<Food>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
       binding =FragmentFoodBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fstore = Firebase.firestore
        auth= Firebase.auth
        breakfastList =ArrayList<Food>()
        lunchList=ArrayList<Food>()
        dinnerList =ArrayList<Food>()

        getBreakfast()
        getLunch()
        getDinner()

        //realization of button functions
        btn1()
        btn2()
        btn3()
        btn4()
        btn5()

        CoroutineScope(Dispatchers.Main).launch {
            delay(200)
            getList(0)
        }



    }

    private fun btn1() {
        binding.btn1.setOnClickListener {
            getList(0)
        }
    }

    private fun btn2() {
        binding.btn2.setOnClickListener {
            getList(1)
        }
    }

    private fun btn3() {
        binding.btn3.setOnClickListener {
            getList(2)
        }
    }

    private fun btn4() {
        binding.btn4.setOnClickListener {
            getList(3)
        }
    }

    private fun btn5() {
        binding.btn5.setOnClickListener {
            binding.btn5.setOnClickListener {
                getList(4)
            }
        }
    }
    //get data from firebase firestore
    private fun getBreakfast(){
        fstore.collection("Breakfast").addSnapshotListener { value, error ->
            if(error!=null){
                Toast.makeText(context,error.toString(), Toast.LENGTH_LONG).show()
            }else{
                if (value !=null){
                    if(!value.isEmpty){
                        breakfastList.clear()

                        val docs =value.documents
                        for (doc in docs) {
                            val name = doc.get("name") as String
                            val content = doc.get("content") as String
                            val imageUrl = doc.get("image") as String

                            val post = Food(name,content,imageUrl)
                            breakfastList.add(post)

                        }
                    }


                }
            }

        }

    }


    //get data from firebase firestore
    private fun getLunch(){
        fstore.collection("Lunch").addSnapshotListener { value, error ->
            if(error!=null){
                Toast.makeText(context,error.toString(), Toast.LENGTH_LONG).show()
            }else{
                if (value !=null){
                    if(!value.isEmpty){
                        lunchList.clear()

                        val docs =value.documents
                        for (doc in docs) {
                            val name = doc.get("name") as String
                            val content = doc.get("content") as String
                            val imageUrl = doc.get("image") as String



                            val post = Food(name,content,imageUrl)
                            lunchList.add(post)

                        }
                    }


                }
            }

        }

    }
    //get data from firebase firestore
    private fun getDinner(){
        fstore.collection("Dinner").addSnapshotListener { value, error ->
            if(error!=null){
                Toast.makeText(context,error.toString(), Toast.LENGTH_LONG).show()
            }else{
                if (value !=null){
                    if(!value.isEmpty){
                        dinnerList.clear()

                        val docs =value.documents
                        for (doc in docs) {
                            val name = doc.get("name") as String
                            val content = doc.get("content") as String
                            val imageUrl = doc.get("image") as String



                            val post = Food(name,content,imageUrl)
                            dinnerList.add(post)

                        }
                    }


                }
            }

        }

    }
    //displaying the data in the list on the screen
    private fun getList(number:Int){
        Glide.with(this).load(breakfastList[number].imageUrl).into(binding.image1)
        Glide.with(this).load(lunchList[number].imageUrl).into(binding.image2)
        Glide.with(this).load(dinnerList[number].imageUrl).into(binding.image3)
        binding.name1.text =breakfastList[number].name
        binding.name2.text =lunchList[number].name
        binding.name3.text =dinnerList[number].name
        binding.content1.text=breakfastList[number].content
        binding.content2.text=lunchList[number].content
        binding.content3.text=dinnerList[number].content
        binding.daytext.text="${number+1}.Gün"


    }


}