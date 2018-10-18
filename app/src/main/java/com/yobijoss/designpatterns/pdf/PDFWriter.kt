package com.yobijoss.designpatterns.pdf

import android.graphics.pdf.PdfDocument
import android.text.Layout
import com.yobijoss.designpatterns.model.Dog
import java.io.File
import java.io.FileOutputStream

class PDFWriter(storageDir: File) {

    var path = storageDir.absolutePath + "/documento.pdf";
    val padding = 16

    fun generateFile(dogs: List<Dog>?): File {
        val document = PdfDocument()
        val width = 612
        val height = 792
        val pageInfo = PdfDocument.PageInfo.Builder(width, height, 1).create()
        val page = document.startPage(pageInfo)
        val pageWidth: Int = page?.getInfo()?.getPageWidth()?: - padding
        val textColor = intArrayOf(33, 33, 33)
        var textSize = 14

        val element = PDFTable(1)


        element.add(PDFPhrase("Es momento de adoptar perritos", textSize,
                Layout.Alignment.ALIGN_NORMAL, padding, textColor))


        val aColumn = PDFTable(1)
        dogs?.forEach {
            paintDog(aColumn, it, textSize, textColor)
        }

        val bColumn = PDFTable(1)

        dogs?.forEach {
            paintDog(bColumn, it, textSize, textColor)
        }

        val firstColumn = PDFTable(2)
        firstColumn.add(aColumn)
        firstColumn.add(bColumn)

        textSize = 18

        val secondColumn = PDFTable(1)
        dogs?.forEach {
            paintDog(secondColumn, it, textSize, textColor)
        }

        val content = PDFTable(2)
        content.add(firstColumn)
        content.add(secondColumn)

        element.add(content)

        element.write(padding, pageWidth, 20, page.getCanvas())

        closeFile(document, page)
        return File(path);
    }

    private fun paintDog(bColumn: PDFTable, it: Dog, textSize: Int, textColor: IntArray) {
        bColumn.add(PDFPhrase(it.name, textSize,
                Layout.Alignment.ALIGN_NORMAL, padding, textColor))
        bColumn.add(PDFPhrase("${it.age}", textSize,
                Layout.Alignment.ALIGN_NORMAL, padding, textColor))
        bColumn.add(PDFPhrase("${it.size}", textSize,
                Layout.Alignment.ALIGN_NORMAL, padding, textColor))
    }

    private fun closeFile(document: PdfDocument, page: PdfDocument.Page) {
        val pdfFile = File(path)
        document.finishPage(page)
        document.writeTo(FileOutputStream(pdfFile))
        document.close()
    }

}