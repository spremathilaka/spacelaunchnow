package com.zotiko.spacelaunchnow.ui.main.di

import com.zotiko.spacelaunchnow.ui.main.MainFragment
import com.zotiko.spacelaunchnow.ui.main.detail.DetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentModule {

    @ContributesAndroidInjector
    abstract fun provideMainFragmentFactory(): MainFragment

    @ContributesAndroidInjector
    abstract fun provideDetailFragmentFactory(): DetailFragment
}