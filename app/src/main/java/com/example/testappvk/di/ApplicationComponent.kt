package com.example.testappvk.di

import com.example.testappvk.data.network.NetworkModule
import com.example.testappvk.presentation.MainActivity
import dagger.Component

@Component(modules = [NetworkModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
}
