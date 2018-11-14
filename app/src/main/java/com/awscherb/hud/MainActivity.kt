package com.awscherb.hud

import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var last = -1
    private var longPress = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fm = supportFragmentManager
            fm.beginTransaction().apply {
                add(R.id.containerOne, NumberFragment.newInstance(1), null)
                add(R.id.containerTwo, NumberFragment.newInstance(2), null)
                add(R.id.containerThree, NumberFragment.newInstance(3), null)
                add(R.id.containerFour, NumberFragment.newInstance(4), null)

                commit()
            }
        }

        containerOne.setOnLongClickListener { longPress(R.id.containerOne); true }
        containerTwo.setOnLongClickListener { longPress(R.id.containerTwo); true }
        containerThree.setOnLongClickListener { longPress(R.id.containerThree); true }
        containerFour.setOnLongClickListener { longPress(R.id.containerFour); true }
    }

    override fun onBackPressed() {
        if (longPress) {
            clear()
        } else {
            super.onBackPressed()
        }
    }

    private fun longPress(container: Int) {
        if (container == last) {
            last = -1
            longPress = false
            return
        }

        if (longPress) {
            val fm = supportFragmentManager
            val first = fm.findFragmentById(last)
            val second = fm.findFragmentById(container)

            fm.beginTransaction().apply {
                remove(first!!)
                remove(second!!)
                commit()
            }
            fm.executePendingTransactions()
            fm.beginTransaction().apply {
                add(container, first!!)
                add(last, second!!)
                commit()
            }

            findViewById<FrameLayout>(last).foreground = null
            last = -1
        } else {
            findViewById<FrameLayout>(container).foreground = ColorDrawable(ContextCompat.getColor(this, R.color.colorHighlight))
            last = container
        }

        longPress = !longPress
    }

    private fun clear() {
        findViewById<FrameLayout>(last)?.foreground = null
        longPress = false
        last = -1
    }
}