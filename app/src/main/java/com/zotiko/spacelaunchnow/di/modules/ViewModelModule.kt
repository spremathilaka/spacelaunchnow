package com.zotiko.spacelaunchnow.di.modules

import androidx.lifecycle.ViewModel
import com.zotiko.spacelaunchnow.domain.base.upcominglaunches.GetUpComingLaunchesUC
import com.zotiko.spacelaunchnow.ui.main.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Provider

@Module
class ViewModelModule {

    @Provides
    fun providesViewModelFactory(
        viewModelMap: Map<Class<out ViewModel>,
            @JvmSuppressWildcards Provider<ViewModel>>
    ): ViewModelFactory {
        return ViewModelFactory(viewModelMap)
    }

    @Provides
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun provideMainViewModel(
        getUpComingLaunchUseCase: GetUpComingLaunchesUC,
        @Named(OBSERVER_ON) observerOn: Scheduler
    ): ViewModel {
        return MainViewModel(
            getUpComingLaunchUseCase, observerOn
        )
    }
}