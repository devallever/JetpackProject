package com.allever.app.jetpack.demo07.lifecycle

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.allever.app.jetpack.R
import com.allever.app.jetpack.demo07.lifecycle.lib.LiveDataBus
import com.allever.app.jetpack.demo07.lifecycle.lib.OwnerObserver
import com.allever.app.jetpack.ext.log
import kotlinx.android.synthetic.main.activity_main_demo_07_lifecycle.*

class LifecycleDemoActivity : AppCompatActivity() {

    private lateinit var ownerObserverString: OwnerObserver
    private lateinit var ownerObserverInt: OwnerObserver
    private lateinit var ownerObserverString2: OwnerObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_demo_07_lifecycle)

        ownerObserverString = OwnerObserver(this, Observer<String> {
            log("LifecycleDemoActivity -> LiveDataBus: string = $it")
        })

        ownerObserverString2 = OwnerObserver(this, Observer<String> {
            log("LifecycleDemoActivity -> LiveDataBus: string2 = $it")
        })

        ownerObserverInt = OwnerObserver(this, Observer<Int> {
            log("LifecycleDemoActivity -> LiveDataBus: int = $it")
        })

        btnOpenSecond.setOnClickListener {
            val intent = Intent(this, LifecycleDemoOtherActivity::class.java)
            startActivity(intent)
        }



        LiveDataBus.getIns().register("1", ownerObserverString)

        LiveDataBus.getIns().register("1", ownerObserverString2)

        LiveDataBus.getIns().register("2", ownerObserverInt)

    }

    override fun onDestroy() {
        super.onDestroy()
        LiveDataBus.getIns().unRegister("1", ownerObserverString)
        LiveDataBus.getIns().unRegister("1", ownerObserverString2)
        LiveDataBus.getIns().unRegister("2", ownerObserverInt)
    }




}