package com.zotiko.spacelaunchnow.ui.main.di

import com.zotiko.spacelaunchnow.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentModule {

    @ContributesAndroidInjector
    abstract fun provideMainFragmentFactory(): MainFragment
}