package com.zotiko.spacelaunchnow.ui.main.di

import com.zotiko.spacelaunchnow.ui.upcominglaunches.detail.DetailFragment
import com.zotiko.spacelaunchnow.ui.upcominglaunches.list.LaunchEventListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentModule {

    @ContributesAndroidInjector
    abstract fun provideMainFragmentFactory(): LaunchEventListFragment

    @ContributesAndroidInjector
    abstract fun provideDetailFragmentFactory(): DetailFragment
}