package app.alhyatt.center.di



import app.alhyatt.center.data.repositery.AccountRepo
import org.koin.dsl.module


val repositeryModule = module {
    factory { AccountRepo(get()) }
}