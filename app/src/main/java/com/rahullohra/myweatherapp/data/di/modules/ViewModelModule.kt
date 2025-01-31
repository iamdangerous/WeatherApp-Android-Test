package com.rahullohra.myweatherapp.data.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rahullohra.myweatherapp.data.di.scopes.ActivityScope
import com.rahullohra.myweatherapp.data.di.scopes.AppScope
import com.rahullohra.myweatherapp.presentation.viewmodels.ViewModelFactory
import com.rahullohra.myweatherapp.presentation.viewmodels.ViewModelKey
import com.rahullohra.myweatherapp.presentation.viewmodels.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @ActivityScope
    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    internal abstract fun targetPromotionsDialogVM(viewModel: WeatherViewModel): ViewModel

}