package com.yobijoss.designpatterns.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.yobijoss.designpatterns.R
import com.yobijoss.designpatterns.model.Dog
import com.yobijoss.designpatterns.sort.Sort
import com.yobijoss.designpatterns.ui.DogAdapter
import com.yobijoss.designpatterns.viewmodel.DogsViewModel
import kotlinx.android.synthetic.main.activity_dog_adoption_list.*

class DogAdoptionListActivity : AppCompatActivity() {

    val REQUEST_SORT_CODE = 1
    val SORT_OPTION = "sortOption"


    private lateinit var viewModel: DogsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog_adoption_list)

        dogListRecyclerView.layoutManager = LinearLayoutManager(this);

        viewModel = ViewModelProviders.of(this).get(DogsViewModel::class.java);
        viewModel.updateDogList()

        viewModel.dogList.observe(this, Observer { showDogs(it, Sort.NAME) })
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_SORT_CODE && resultCode == Activity.RESULT_OK) {
            val dogList = viewModel.dogList.value
            dogList?.run {
                val sort = data?.getSerializableExtra(SORT_OPTION)
                sort?.run {
                    showDogs(dogList, sort as Sort)
                }
            }
        }
    }

    private fun showDogs(dogList: List<Dog>, sort: Sort) {
        dogListRecyclerView.adapter = DogAdapter(dogList, sort)
    }

}
