package com.example.presentation.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.Resource
import com.example.domain.models.Drink
import com.example.domain.usecases.GetCockTailsUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CockTailViewModel(
    private val getCockTailsUseCase: GetCockTailsUseCase

) : ViewModel() {
    private val _cockTailStatus = MutableLiveData<Resource<List<Drink>>>()
    val cockTailStatus: LiveData<Resource<List<Drink>>> = _cockTailStatus

    fun getCockTails(cocktail: String) {
        viewModelScope.launch {
            getCockTailsUseCase.invoke(cocktail).onStart {
                _cockTailStatus.value = Resource.loading(null)
            }.catch {
                _cockTailStatus.value = Resource.error("Check your internet connection", null)
            }.collect {
                _cockTailStatus.value = Resource.success(it)
            }
        }
    }
}
