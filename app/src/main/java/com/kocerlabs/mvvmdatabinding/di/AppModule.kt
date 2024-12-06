package com.kocerlabs.mvvmdatabinding.di

import android.content.Context
import com.kocerlabs.mvvmdatabinding.data.database.AppDatabase
import com.kocerlabs.mvvmdatabinding.data.network.MyApi
import com.kocerlabs.mvvmdatabinding.data.network.model.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
// Module'ü nereye kurmak istediğimizi soruyor.
// Component hierarşisinin en üst seviyesi Singleton'dur.
object AppModule {


    /**
     * Önemli not: Aşağıdaki fonksiyondan bir Interface döndürdüğüme dikkat et,
     * NOT: Bu interface'yi tanımlamam için bana "networkConnectionInterceptor: NetworkConnectionInterceptor" gerekti.
     * Bunu da tanımlamak için Hilt'e bunun nasıl oluşturulacağını anlatmam lazım.
     * Eğer bu bir class ise inject constructor ile inject edebiliriz.
     * Eğer başka bir interface ise yine onun da bağımlılığını buradan sağlamamız gerekli.
     */
    @Provides
    fun provideMyApi(networkConnectionInterceptor: NetworkConnectionInterceptor): MyApi{
        return MyApi.invoke(networkConnectionInterceptor)
    }

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.invoke(context)
    }

    /**
     * Bu fonksiyonu örnek olarak yazdım. Eğer bir interface'in nesnesine ihtiyaç duyarsam ve o da context'e ihtiyaç duyarsa buradan verebiliyorum.
     */
    /*
      @Provides
      fun provideNeededContext(@ApplicationContext context: Context, remoteDataSource: RemoteDataSource): MyApi{
        return remoteDataSource.buildApi(AuthApi::class.java,context)
      }
     */

}