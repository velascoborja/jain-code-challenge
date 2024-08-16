package com.akansha.digitalsurgery.screens.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akansha.digitalsurgery.model.Result
import com.akansha.digitalsurgery.repository.IProcedureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProcedureViewModel @Inject constructor(private val repository: IProcedureRepository) :
    ViewModel() {

    private val _procedureLiveData = MutableLiveData<Result>()
    val procedureLiveData: LiveData<Result> get() = _procedureLiveData

    fun getProcedures() {
        viewModelScope.launch {
            try {
                val result = repository.getProcedures().value
                _procedureLiveData.value = result?.let {
                    result
                } ?: Result.Failure
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
                _procedureLiveData.value = Result.Failure
            }
        }
    }
}