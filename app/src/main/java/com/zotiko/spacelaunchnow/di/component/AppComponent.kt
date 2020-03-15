package com.zotiko.spacelaunchnow.di.component

import android.app.Application
import com.zotiko.spacelaunchnow.SpaceLaunchApplication
import com.zotiko.spacelaunchnow.di.modules.NetModule
import com.zotiko.spacelaunchnow.di.modules.RepositoryModule
import com.zotiko.spacelaunchnow.di.modules.RxJavaModule
import com.zotiko.spacelaunchnow.di.modules.UrlModule
import com.zotiko.spacelaunchnow.di.modules.UseCaseModule
import com.zotiko.spacelaunchnow.di.modules.ViewModelModule
import com.zotiko.spacelaunchnow.ui.di.ActivityBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        UrlModule::class,
        NetModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        UseCaseModule::class,
        RxJavaModule::class,
        ActivityBuilderModule::class]
)
//NOTE add this "AndroidInjector<SpaceLaunchApplication>" to fix
// error kotlin.UninitializedPropertyAccessException: late-init property
// dispatchingAndroidInjector has not been initialized
interface AppComponent : AndroidInjector<SpaceLaunchApplication> {

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }

    fun inject(application: Application)
}