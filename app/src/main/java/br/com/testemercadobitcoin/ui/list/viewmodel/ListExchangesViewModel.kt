package br.com.testemercadobitcoin.ui.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.testemercadobitcoin.data.ExchangeResponse
import br.com.testemercadobitcoin.remote.SafeResponse
import br.com.testemercadobitcoin.ui.list.usecase.ListExchangesUseCase
import kotlinx.coroutines.launch

class ListExchangesViewModel(private val listExchangesUseCase: ListExchangesUseCase) : ViewModel() {
    private val _errorState = MutableLiveData<String>()
    val errorState: LiveData<String> = _errorState
    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> = _loadingState
    private val _list = MutableLiveData<List<ExchangeResponse>>()
    val list: LiveData<List<ExchangeResponse>> = _list

    fun getListExchanges() {
        _loadingState.value = true
        viewModelScope.launch {
            when (val result = listExchangesUseCase.execute()) {
                is SafeResponse.Success -> {
                    _list.value = result.value
                    _loadingState.value = false
                }

                is SafeResponse.GenericError -> {
                    _errorState.value = result.error.toString()
                    _loadingState.value = false
                }

                SafeResponse.NetworkError -> {
                    _errorState.value = "Verifique sua conexão com a internet"
                    _loadingState.value = false
                }
            }

        }
    }
}