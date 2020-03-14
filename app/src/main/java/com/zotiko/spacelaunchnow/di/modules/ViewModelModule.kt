package com.zotiko.spacelaunchnow.di.modules

import androidx.lifecycle.ViewModel
import com.zotiko.spacelaunchnow.domain.upcominglaunches.GetUpComingLaunchesUC
import com.zotiko.spacelaunchnow.ui.upcominglaunches.UpComingLaunchesViewModel
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
    @ViewModelKey(UpComingLaunchesViewModel::class)
    fun provideMainViewModel(
        getUpComingLaunchUseCase: GetUpComingLaunchesUC,
        @Named(OBSERVER_ON) observerOn: Scheduler
    ): ViewModel {
        return UpComingLaunchesViewModel(
            getUpComingLaunchUseCase, observerOn
        )
    }
}