package ru.kpfu.itis.ganiev.petchampionship.di.modules

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
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
    fun provideStorage(): StorageReference = FirebaseStorage.getInstance().reference

    @Provides
    @Singleton
    fun provideRemoteApi(
        firestore: FirebaseFirestore,
        auth: FirebaseAuth,
        storageReference: StorageReference
    ): RemoteApi =
        FirebaseRemoteApi(auth, firestore, storageReference)
}
