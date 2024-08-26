package com.akansha.digitalsurgery.screens.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akansha.digitalsurgery.addFavouritesInfo
import com.akansha.digitalsurgery.model.ProcedureDetailCard
import com.akansha.digitalsurgery.model.ProcedureDetailResult
import com.akansha.digitalsurgery.model.ProcedureItem
import com.akansha.digitalsurgery.model.ProcedureListResult
import com.akansha.digitalsurgery.repository.IProcedureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProcedureViewModel @Inject constructor(
    private val repository: IProcedureRepository,
) : ViewModel() {

    private val _procedureLiveData = MutableLiveData<List<ProcedureItem>>()
    val procedureLiveData: LiveData<List<ProcedureItem>> get() = _procedureLiveData

    private val _procedureDetailLiveData = MutableLiveData<ProcedureDetailCard>()
    val procedureDetailLiveData: LiveData<ProcedureDetailCard> get() = _procedureDetailLiveData

    private val _favouritesLiveData = MutableLiveData<List<ProcedureItem>>()
    val favouritesLiveData: LiveData<List<ProcedureItem>> get() = _favouritesLiveData

    fun getProcedures() {
        viewModelScope.launch {
            getFavourites()
            val procedureListResult = repository.getProcedures().value
            _procedureLiveData.value = procedureListResult?.let {
                when (procedureListResult) {
                    is ProcedureListResult.Success -> favouritesLiveData.value?.let { favorites ->
                        procedureListResult.procedures.addFavouritesInfo(favorites)
                    } ?: procedureListResult.procedures

                    is ProcedureListResult.Failure -> emptyList()
                }
            }
        }
    }

    fun getProcedureDetails(procedureId: String) {
        viewModelScope.launch {
            val procedureDetailResult = repository.getProcedureDetails(procedureId).value
            _procedureDetailLiveData.value = procedureDetailResult?.let {
                when (procedureDetailResult) {
                    is ProcedureDetailResult.Success -> favouritesLiveData.value?.let { favorites ->
                        procedureDetailResult.details.addFavouritesInfo(favorites)
                    } ?: procedureDetailResult.details

                    is ProcedureDetailResult.Failure -> ProcedureDetailCard()
                }
            }
        }
    }

    fun getFavourites() {
        viewModelScope.launch {
            val procedureListResult = repository.getFavouriteProcedures().value
            _favouritesLiveData.value = procedureListResult?.let {
                when (procedureListResult) {
                    is ProcedureListResult.Success -> procedureListResult.procedures
                    is ProcedureListResult.Failure -> emptyList()
                }
            }
        }
    }

    fun onFavouriteStateUpdate(procedureId: String, isFavourite: Boolean) {
        viewModelScope.launch {
            val procedure = _procedureLiveData.value?.first { it.id == procedureId }
                ?: _favouritesLiveData.value?.first { it.id == procedureId }

            procedure?.let {
                if (isFavourite) {
                    addToFavourites(it)
                } else {
                    removeFromFavourites(it)
                }
            }
        }
    }

    private suspend fun addToFavourites(procedure: ProcedureItem) {
        repository.saveFavouriteProcedure(procedure)

        val updatedFavourites = _favouritesLiveData.value.orEmpty().toMutableList()
        if (!updatedFavourites.contains(procedure)) {
            updatedFavourites.add(procedure)
        }
        _favouritesLiveData.value = updatedFavourites
    }

    private suspend fun removeFromFavourites(procedure: ProcedureItem) {
        repository.removeFavouriteProcedure(procedure)

        val updatedFavourites =
            _favouritesLiveData.value?.filterNot { it == procedure }.orEmpty().toMutableList()
        _favouritesLiveData.value = updatedFavourites
    }

    fun updateProcedureItems(procedureId: String, isFavourite: Boolean) {
        _procedureLiveData.value?.let { currentProcedureList ->
            val updatedProcedureList = currentProcedureList.map { procedure ->
                if (procedure.id == procedureId) {
                    procedure.copy(isFavourite = isFavourite)
                } else {
                    procedure
                }
            }
            _procedureLiveData.value = updatedProcedureList
        }
    }
}