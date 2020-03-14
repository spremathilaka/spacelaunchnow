package com.zotiko.spacelaunchnow.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zotiko.spacelaunchnow.R
import com.zotiko.spacelaunchnow.ui.upcominglaunches.list.LaunchEventListFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector,
    LaunchEventListFragment.OnFragmentInteractionListener {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
