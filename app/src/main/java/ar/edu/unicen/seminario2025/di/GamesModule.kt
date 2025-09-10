package ar.edu.unicen.seminario2025.di

import android.content.Context
import ar.edu.unicen.seminario2025.BuildConfig
import ar.edu.unicen.seminario2025.ddl.data.remote.api.GamesApi
import ar.edu.unicen.seminario2025.utils.AvailableFiltersPreferences
import ar.edu.unicen.seminario2025.utils.FiltersPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class GamesModule {
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit
              .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    fun providesGamesApi(
        retrofit: Retrofit
    ) : GamesApi {
        return retrofit.create(GamesApi::class.java)
    }
    @Provides
    fun provideFiltersPreferences(@ApplicationContext context: Context): FiltersPreferences {
        return FiltersPreferences(context)
    }
    @Provides
    fun provideAvailableFiltersPreferences(@ApplicationContext context: Context): AvailableFiltersPreferences {
        return AvailableFiltersPreferences(context)
    }
}