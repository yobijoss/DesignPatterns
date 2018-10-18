package com.yobijoss.designpatterns.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.yobijoss.designpatterns.R
import com.yobijoss.designpatterns.sort.Sort
import kotlinx.android.synthetic.main.activity_sort_selection.*

class SortSelectionActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sort_selection)

        radioSortGroup.setOnCheckedChangeListener(this)
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        val sort: Sort = when (checkedId) {
            R.id.radio_age -> Sort.AGE
            R.id.radio_name -> Sort.NAME
            R.id.radio_nature -> Sort.NATURE
            R.id.radio_size -> Sort.SIZE
            else -> Sort.NAME
        }

        setResult(Activity.RESULT_OK, Intent().putExtra(DogAdoptionListActivity.SORT_OPTION, sort))
        finish()
    }

}
