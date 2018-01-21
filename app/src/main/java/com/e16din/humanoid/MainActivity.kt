package com.e16din.humanoid

import android.app.Activity
import android.os.Bundle
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        I.am().humanoid().logH()

        "You can logging strings like there!".logI()
        "Add your own tag if you need".logI("HumanoidYellow")

        disableHumanoidLogs()

        "To disable logs in release version you can use disableHumanoidLogs() function".logE()

        enableHumanoidLogs()

        "To enable logs again, just call enableHumanoidLogs()".logI()
        "When you need to get link to code, just use function logLink()".logI()
        logLink()

        vHelloButton.setOnClickListener {
            I.click().on().the().button(vHelloButton).logH()
        }

        window.decorView.setOnTouchListener({ v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    I.touch().on("x: ${event.x}, y: ${event.y}").logH()
                }
            }

            true
        })
    }

    override fun onPause() {
        logLink()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        logLink()
    }
}
