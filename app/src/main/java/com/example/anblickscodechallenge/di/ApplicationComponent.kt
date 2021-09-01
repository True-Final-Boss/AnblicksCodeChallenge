package com.example.anblickscodechallenge.di

import com.example.anblickscodechallenge.model.repository.Repository
import com.example.anblickscodechallenge.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton

@Component
interface ApplicationComponent {
    fun getRepository(): Repository

    fun inject(activity: MainActivity)
}