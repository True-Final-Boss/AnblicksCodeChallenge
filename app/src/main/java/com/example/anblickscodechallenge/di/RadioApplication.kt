package com.example.anblickscodechallenge.di

import android.app.Application

class RadioApplication : Application() {
    val applicationComponent: ApplicationComponent = DaggerApplicationComponent.create()
}