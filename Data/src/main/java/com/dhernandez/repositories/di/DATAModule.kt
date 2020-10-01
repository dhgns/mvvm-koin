package com.dhernandez.repositories.di

import com.dhernandez.repositories.DataAccessRepository
import com.dhernandez.repositories.IDataAccessRepository
import org.koin.dsl.module

val DATAModule = module {

    factory<IDataAccessRepository> { DataAccessRepository() }

}


