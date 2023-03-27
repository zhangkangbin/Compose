package com.compose.ui.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

class UserBean {

    fun getName(): String {

        return "kang";
    }
}
class SingletonObject{


}
@InstallIn(SingletonComponent::class)
@Module
object  NetWorkToolModule{

    @Singleton
    @Provides
    fun provideNetWorkTool():SingletonObject{

        return SingletonObject();

    }

}