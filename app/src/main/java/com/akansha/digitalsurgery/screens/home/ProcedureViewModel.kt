package com.akansha.digitalsurgery.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akansha.digitalsurgery.addFavouritesInfo
import com.akansha.digitalsurgery.datastorage.ProcedureDao
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
    private val dao: ProcedureDao,
) :
    ViewModel() {

    private val _procedureLiveData = MutableLiveData<List<ProcedureItem>>()
    val procedureLiveData: LiveData<List<ProcedureItem>> get() = _procedureLiveData

    private val _procedureDetailLiveData = MutableLiveData<ProcedureDetailCard>()
    val procedureDetailLiveData: LiveData<ProcedureDetailCard> get() = _procedureDetailLiveData

    private val _favouritesLiveData = MutableLiveData<List<ProcedureItem>>()
    val favouritesLiveData: LiveData<List<ProcedureItem>> get() = _favouritesLiveData

    fun getProcedures() {
        viewModelScope.launch {
            getFavourites()
            val result = repository.getProcedures().value
            _procedureLiveData.value = result?.let {
                when (result) {
                    is ProcedureListResult.Success -> favouritesLiveData.value?.let { favorites ->
                        result.procedures.addFavouritesInfo(favorites)
                    }

                    is ProcedureListResult.Failure -> emptyList()
                }
            }
        }
    }

    fun getProcedureDetails(procedureId: String) {
        viewModelScope.launch {
            val result = repository.getProcedureDetails(procedureId).value
            _procedureDetailLiveData.value = result?.let {
                when (result) {
                    is ProcedureDetailResult.Success -> favouritesLiveData.value?.let { favorites ->
                        result.details.addFavouritesInfo(favorites)
                    }

                    is ProcedureDetailResult.Failure -> ProcedureDetailCard()
                }
            }
        }
    }

    fun getFavourites() {
        viewModelScope.launch {
            _favouritesLiveData.value = dao.getFavouriteProcedures()
        }
    }

    fun onFavouriteStateUpdate(procedureId: String, isFavourite: Boolean) {
        viewModelScope.launch {
            val procedure = _procedureLiveData.value?.first { it.id == procedureId }
                ?: _favouritesLiveData.value?.first {
                    it.id == procedureId
                }
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
        dao.saveAsFavourite(procedure)
        val updatedFavourites = _favouritesLiveData.value.orEmpty().toMutableList()
        if (!updatedFavourites.contains(procedure)) {
            updatedFavourites.add(procedure)
        }
        _favouritesLiveData.value = updatedFavourites
    }

    private suspend fun removeFromFavourites(procedure: ProcedureItem) {
        dao.removeAsFavourite(procedure)
        _favouritesLiveData.value =
            _favouritesLiveData.value?.filterNot { it == procedure }?.toMutableList()
    }

    fun updateProcedureItems(procedureId: String, isFavourite: Boolean) {
        _procedureLiveData.value?.let { currentList ->
            val updatedList = currentList.map { procedure ->
                if (procedure.id == procedureId) {
                    procedure.copy(isFavourite = isFavourite)
                } else {
                    procedure
                }
            }
            _procedureLiveData.value = updatedList
        }
    }
}