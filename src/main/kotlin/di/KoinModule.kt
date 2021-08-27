package di

import org.koin.dsl.module
import view.codeinput.CodeInputViewModel

val viewModelModule = module {
    factory { CodeInputViewModel() }
}