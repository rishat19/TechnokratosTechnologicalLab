package ru.kpfu.itis.ganiev.petchampionship.presentation.pets.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kpfu.itis.ganiev.petchampionship.domain.interactors.AppInteractor
import ru.kpfu.itis.ganiev.petchampionship.domain.model.Pet
import ru.kpfu.itis.ganiev.petchampionship.presentation.router.Router
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class PetDetailsViewModel @Inject constructor(
    private val appInteractor: AppInteractor,
    private val coroutineContext: CoroutineContext,
    private val router: Router
) : ViewModel() {

    private var pet: MutableLiveData<Pet> = MutableLiveData()

    fun getPet(petId: String): LiveData<Pet> {
        viewModelScope.launch(coroutineContext) {
            appInteractor.getPetById(petId)?.let {
                pet.postValue(it)
            } ?: throw IllegalArgumentException("Pet with id $petId not found")
        }
        return pet
    }

    fun back() = router.back()

}
