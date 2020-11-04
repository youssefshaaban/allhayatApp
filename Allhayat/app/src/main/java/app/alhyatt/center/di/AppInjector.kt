package app.alhyatt.center.di




import app.alhyatt.center.MyApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

@SuppressWarnings("unchecked")
object AppInjector {

    fun init(app: MyApp) {
                startKoin {
            androidLogger(Level.DEBUG)
            androidContext(app)
            modules(listOf(
                retrofitClient,
                rxModule,
                viewModleModule,
                repositeryModule
            ))
        }
    }

}