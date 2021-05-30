package ru.kpfu.itis.ganiev.petchampionship.presentation.pets.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.joda.time.DateTime
import ru.kpfu.itis.ganiev.petchampionship.domain.interactors.AppInteractor
import ru.kpfu.itis.ganiev.petchampionship.domain.model.Nomination
import ru.kpfu.itis.ganiev.petchampionship.domain.model.Pet
import ru.kpfu.itis.ganiev.petchampionship.presentation.pets.model.BestPetItem
import ru.kpfu.itis.ganiev.petchampionship.presentation.router.Router
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class PetsViewModel @Inject constructor(
    private val appInteractor: AppInteractor,
    private val coroutineContext: CoroutineContext,
    private val router: Router
) : ViewModel() {

    private val interactorPets = appInteractor.getPets()
    private val pets: MutableLiveData<List<BestPetItem>> = MutableLiveData()

    private var nominationP: (Nomination) -> Boolean = {
        true
    }

    private var datePredicate: (DateTime) -> Boolean = {
        true
    }

    private val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        observePets()
        refreshData()
    }

    fun refreshData() {
        viewModelScope.launch(coroutineContext) {
            isLoading.postValue(true)
            appInteractor.refreshData()
            isLoading.postValue(false)
        }
    }

    fun isLoading(): LiveData<Boolean> = isLoading

    fun itemClicked(petId: String) {
        router.navigateBestPetsToPetsDetails(petId)
    }

    fun getNominations(): LiveData<List<Nomination>> = appInteractor.getNominations()

    fun getPets(): LiveData<List<BestPetItem>> = pets


    fun findWithNomination(nominationId: String) {
        nominationP = {
            it.id == nominationId
        }
        updatePets()
    }

    fun findThisWeek(isPositive: Boolean) {
        datePredicate = if (isPositive) {
            {
                it.isAfter(DateTime().minusDays(7))
            }
        } else {
            {
                true
            }
        }
        updatePets()
    }

    private fun updatePets() {
        interactorPets.value?.let {
            postValue(it)
        }
    }

    private fun observePets() {
        interactorPets.observeForever { list ->
            postValue(list.sortedByDescending {
                it.publicationDate
            }.sortedByDescending {
                it.positiveVotes - it.negativeVotes
            })
        }
    }

    private fun postValue(list: List<Pet>) {
        pets.postValue(list.filter {
            nominationP.invoke(it.nomination) && datePredicate.invoke(it.publicationDate)
        }.also {
            Log.d("RISHAT", "$it")
        }.map {
            BestPetItem.fromPet(it)
        })
    }

    fun addPetClicked() {
        router.navigateToAddPet()
    }

    fun vote(petId: String, isPositive: Boolean) {
        viewModelScope.launch(coroutineContext) {
            appInteractor.vote(petId, isPositive)
        }
    }

}
