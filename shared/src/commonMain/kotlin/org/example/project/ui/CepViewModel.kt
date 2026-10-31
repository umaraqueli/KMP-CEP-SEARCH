package org.example.project.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.data.repository.CepRepositoryImpl
import org.example.project.domain.repository.CepRepository

class CepViewModel(private val repository: CepRepository = CepRepositoryImpl()) : ViewModel() {
    private val _cep = MutableStateFlow("")
    val cep: StateFlow<String> = _cep.asStateFlow()
    private val _uiState = MutableStateFlow<CepUiState>(CepUiState.Idle)
    val uiState: StateFlow<CepUiState> = _uiState.asStateFlow()

    fun onCepChange(value: String) { _cep.value = value.filter(Char::isDigit).take(8) }

    fun buscarCep() {
        val valor = _cep.value
        if (valor.length != 8) return
        viewModelScope.launch {
            _uiState.value = CepUiState.Loading
            repository.buscarCep(valor)
                .onSuccess { _uiState.value = CepUiState.Success(it) }
                .onFailure { _uiState.value = CepUiState.Error(it.message ?: "Não foi possível consultar o CEP.") }
        }
    }
}
