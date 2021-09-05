package di

import data.WarshipRepository
import org.koin.dsl.module
import view.codeinput.CodeInputViewModel

val viewModelModule = module {
    factory { CodeInputViewModel() }
}

val repositoryModule = module {
    single { WarshipRepository() }
}