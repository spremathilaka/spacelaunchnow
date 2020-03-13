package com.zotiko.spacelaunchnow.ui.di

import com.zotiko.spacelaunchnow.ui.main.MainActivity
import com.zotiko.spacelaunchnow.ui.main.di.MainFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    /// @ActivityScope
    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    abstract fun bindMainActivity(): MainActivity
}