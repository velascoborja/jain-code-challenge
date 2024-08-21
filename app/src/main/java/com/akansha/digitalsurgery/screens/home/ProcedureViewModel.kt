package com.akansha.digitalsurgery.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akansha.digitalsurgery.model.ProcedureDetailCard
import com.akansha.digitalsurgery.model.ProcedureDetailResult
import com.akansha.digitalsurgery.model.ProcedureItem
import com.akansha.digitalsurgery.model.ProcedureListResult
import com.akansha.digitalsurgery.repository.IProcedureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProcedureViewModel @Inject constructor(private val repository: IProcedureRepository) :
    ViewModel() {

    private val _procedureLiveData = MutableLiveData<List<ProcedureItem>>()
    val procedureLiveData: LiveData<List<ProcedureItem>> get() = _procedureLiveData

    private val _procedureDetailLiveData = MutableLiveData<ProcedureDetailCard>()
    val procedureDetailLiveData: LiveData<ProcedureDetailCard> get() = _procedureDetailLiveData

    fun getProcedures() {
        viewModelScope.launch {
            val result = repository.getProcedures().value
            _procedureLiveData.value = result?.let {
                when (result) {
                    is ProcedureListResult.Success -> result.procedures
                    is ProcedureListResult.Failure -> emptyList()
                }
            } ?: emptyList()
        }
    }

    fun getProcedureDetails(procedureId: String) {
        viewModelScope.launch {
            val result = repository.getProcedureDetails(procedureId).value
            _procedureDetailLiveData.value = result?.let {
                when (result) {
                    is ProcedureDetailResult.Success -> result.details
                    is ProcedureDetailResult.Failure -> ProcedureDetailCard()
                }
            } ?: ProcedureDetailCard()
        }
    }

    fun onFavouriteStateUpdate(procedureId: String, state: Boolean) {}
}