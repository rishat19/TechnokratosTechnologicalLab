package ru.kpfu.itis.ganiev.petchampionship.di.modules

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import ru.kpfu.itis.ganiev.petchampionship.data.network.FirebaseRemoteApi
import ru.kpfu.itis.ganiev.petchampionship.data.network.RemoteApi
import javax.inject.Singleton

@Module
class FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideRemoteApi(auth: FirebaseAuth, firestore: FirebaseFirestore): RemoteApi = FirebaseRemoteApi(auth, firestore)
}