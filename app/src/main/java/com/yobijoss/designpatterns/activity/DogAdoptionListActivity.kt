package com.yobijoss.designpatterns.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.yobijoss.designpatterns.R
import com.yobijoss.designpatterns.model.Dog
import com.yobijoss.designpatterns.sort.Sort
import com.yobijoss.designpatterns.ui.DogAdapter
import com.yobijoss.designpatterns.viewmodel.DogsViewModel
import kotlinx.android.synthetic.main.activity_dog_adoption_list.*
import java.io.File


class DogAdoptionListActivity : AppCompatActivity() {

    val REQUEST_SORT_CODE = 1
    private val WRITE_STORAGE_REQUEST_AUTO_CODE = 123

    private lateinit var viewModel: DogsViewModel

    companion object {
        val SORT_OPTION = "sortOption"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog_adoption_list)

        dogListRecyclerView.layoutManager = LinearLayoutManager(this);

        viewModel = ViewModelProviders.of(this).get(DogsViewModel::class.java);
        viewModel.updateDogList()

        viewModel.dogList.observe(this, Observer { showDogs(it, Sort.NAME) })
        viewModel.pdfFile.observe(this, Observer { sharePDF(it) })
    }

    override fun onStart() {
        super.onStart()
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), WRITE_STORAGE_REQUEST_AUTO_CODE)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.run {
            if (item.itemId == R.id.menu_sort) {
                startActivityForResult(Intent( this@DogAdoptionListActivity, SortSelectionActivity::class.java),
                        REQUEST_SORT_CODE)
            } else if (item.itemId == R.id.menu_export){
                viewModel.requestPdfFile(Environment.getExternalStorageDirectory())
            }
        }

        return super.onOptionsItemSelected(item)
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


    private fun sharePDF(file: File) {
        val fileProvider = getString(R.string.file_provider_authority)
        val uri = FileProvider.getUriForFile(this, fileProvider, file)

        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.type = "application/pdf"
        intent.putExtra(Intent.EXTRA_STREAM, uri)

        startActivity(Intent.createChooser(intent, null))
    }

}
