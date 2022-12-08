package com.example.quote.core.di

import com.example.quote.data.UserRepositoryImpl
import com.example.quote.data.remote.UserRemoteDataSource
import com.example.quote.data.remote.UserRemoteDataSourceImpl
import com.example.quote.domain.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UserRepositoryModule {

    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl):
            UserRepository

    @Binds
    abstract fun bindUserRemoteDataSource(userRemoteDataSourceImpl: UserRemoteDataSourceImpl):
            UserRemoteDataSource


}
