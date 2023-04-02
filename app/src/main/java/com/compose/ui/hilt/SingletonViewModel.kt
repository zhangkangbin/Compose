package com.compose.ui.hilt

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SingletonViewModel @Inject constructor():ViewModel() {

    @Inject
    lateinit var print: Println

    fun postData(){

        print.print("--------SingletonViewModel test--------------")
    }

}

@Module
@InstallIn(ActivityComponent::class)
object PrintlnModule{

    @Provides
    fun getPrintln(): Println {
        return Println()
    }

}
