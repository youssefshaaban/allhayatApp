package app.alhyatt.center.di

import app.alhyatt.center.ui.login.AuthenticationViewModel
import app.alhyatt.center.ui.profile.ProfileViewModel
import app.alhyatt.center.ui.signup.SignupViewModel
import app.alhyatt.center.utilis.rx.ApplicationSchedulerProvider
import app.alhyatt.center.utilis.rx.SchedulerProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModleModule= module {
    viewModel {  AuthenticationViewModel(get(),get()) }
    viewModel {  SignupViewModel(get(),get()) }
    viewModel {  ProfileViewModel(get(),get()) }
}
val rxModule = module {
    factory { ApplicationSchedulerProvider() as SchedulerProvider }
}
