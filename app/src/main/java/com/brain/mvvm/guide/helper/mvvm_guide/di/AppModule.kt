package com.brain.mvvm.guide.helper.mvvm_guide.di

import com.brain.mvvm.guide.helper.mvvm_guide.api.data.remote.CoinPaprikaApi
import com.brain.mvvm.guide.helper.mvvm_guide.api.data.repository.CoinRepositoryImpl
import com.brain.mvvm.guide.helper.mvvm_guide.api.domain.repositery.CoinRepository
import com.brain.mvvm.guide.helper.mvvm_guide.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePaprikaApi(): CoinPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaApi): CoinRepository {
        return CoinRepositoryImpl(api)
    }

    /*@Provides
    @Singleton
    fun provideTodoDatabase(app: Application): TodoDatabase {
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            "todo_db"
        ).build()
    }*/

}