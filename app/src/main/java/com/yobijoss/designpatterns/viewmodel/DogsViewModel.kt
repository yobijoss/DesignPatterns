package com.yobijoss.designpatterns.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yobijoss.designpatterns.model.Dog
import com.yobijoss.designpatterns.pdf.PDFWriter
import com.yobijoss.designpatterns.repository.DogRepository
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import java.io.File

class DogsViewModel : ViewModel() {

    var dogList = MutableLiveData<List<Dog>>()
    var pdfFile = MutableLiveData<File>()

    var dogRepository = DogRepository()

    fun updateDogList() {
        dogList = dogRepository.getDogList()
    }

    fun requestPdfFile(storageDir: File) {
        launch {
            val dogs = dogList.value
            dogs.run {
                val writter = PDFWriter(storageDir)
                val generatedFile : File = writter.generateFile(dogs)
                withContext(Dispatchers.Main) {
                    pdfFile.value = generatedFile;
                }
            }

        }
    }
}
