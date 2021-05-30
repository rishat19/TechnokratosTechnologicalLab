package ru.kpfu.itis.ganiev.petchampionship.presentation.addpet

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kpfu.itis.ganiev.petchampionship.domain.interactors.AppInteractor
import ru.kpfu.itis.ganiev.petchampionship.domain.model.Nomination
import ru.kpfu.itis.ganiev.petchampionship.domain.model.Pet
import ru.kpfu.itis.ganiev.petchampionship.presentation.router.Router
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class AddPetViewModel @Inject constructor(
    private val appInteractor: AppInteractor,
    private val coroutineContext: CoroutineContext,
    private val router: Router
) : ViewModel() {

    fun getNominations(): LiveData<List<Nomination>> = appInteractor.getNominations()

    fun addPet(pet: Pet) {
        viewModelScope.launch(coroutineContext) {
            appInteractor.addPet(pet)
            withContext(Dispatchers.Main) {
                router.back()
            }
        }
    }

    fun onBackPressed() = router.back()
}
