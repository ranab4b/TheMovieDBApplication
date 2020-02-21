package com.example.starzplaysamplelibrary.modules

import android.provider.ContactsContract
import com.example.starzplaysamplelibrary.Contracts
import com.example.starzplaysamplelibrary.datarepos.DataRepo
import com.example.starzplaysamplelibrary.datasource.DataSource
import com.example.starzplaysamplelibrary.datasource.DatasourceRS

import org.koin.dsl.module.module

val dataSourceModule = module {
    single<DataSource> {
        DatasourceRS(
            get()
        )
    }
    single<Contracts> {
        DataRepo(
            get()
        )
    }

}