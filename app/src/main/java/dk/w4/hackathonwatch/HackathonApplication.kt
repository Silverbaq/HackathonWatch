package dk.w4.hackathonwatch

import android.app.Application
import dk.w4.hackathonwatch.data.HackathonAPIService
import dk.w4.hackathonwatch.data.db.HackathonDatabase
import dk.w4.hackathonwatch.data.network.ConnectivityIntercepter
import dk.w4.hackathonwatch.data.network.ConnectivityIntercepterImpl
import dk.w4.hackathonwatch.data.network.HackerthonNetworkDataSource
import dk.w4.hackathonwatch.data.network.HackerthonNetworkDataSourceImpl
import dk.w4.hackathonwatch.data.repository.HackathonRepository
import dk.w4.hackathonwatch.data.repository.HackathonRepositoryImpl
import dk.w4.hackathonwatch.ui.hackathon.HackathonViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


class HackathonApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@HackathonApplication))

        bind() from singleton { HackathonDatabase(instance()) }
        bind() from singleton { instance<HackathonDatabase>().hackathonDao() }
        bind<ConnectivityIntercepter>() with singleton { ConnectivityIntercepterImpl(instance()) }
        bind() from singleton { HackathonAPIService(instance()) }
        bind<HackerthonNetworkDataSource>() with singleton { HackerthonNetworkDataSourceImpl(instance()) }
        bind<HackathonRepository>() with singleton { HackathonRepositoryImpl(instance(), instance()) }
        bind() from provider { HackathonViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        //AndroidThreeTen.init()
    }
}