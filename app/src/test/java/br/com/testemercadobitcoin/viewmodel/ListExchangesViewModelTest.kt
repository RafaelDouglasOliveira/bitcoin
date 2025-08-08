package br.com.testemercadobitcoin.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.testemercadobitcoin.remote.SafeResponse
import br.com.testemercadobitcoin.ui.list.model.ExchangeItem
import br.com.testemercadobitcoin.ui.list.usecase.ListExchangesUseCase
import br.com.testemercadobitcoin.ui.list.usecase.mapper.ExchangeMapper.toExchange
import br.com.testemercadobitcoin.ui.list.viewmodel.ListExchangesViewModel
import br.com.testemercadobitcoin.utils.MockExchange.listExchangeMock
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class ListExchangesViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val mockUseCase: ListExchangesUseCase = mockk()
    private lateinit var viewModel: ListExchangesViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ListExchangesViewModel(mockUseCase)
    }

    @After
    fun tearDown() {
        unmockkAll()
        Dispatchers.resetMain()
    }

    @Test
    fun ` retorno de sucesso `() = runTest {
        coEvery { mockUseCase.execute() } returns SafeResponse.Success(listExchangeMock.toExchange())
        val listObserver = mockk<Observer<List<ExchangeItem>>>(relaxed = true)
        viewModel.list.observeForever(listObserver)
        viewModel.getListExchanges()
        testDispatcher.scheduler.advanceUntilIdle()
        assert(viewModel.list.value != null)
        verify { listObserver.onChanged(listExchangeMock.toExchange()) }
        viewModel.list.removeObserver(listObserver)
    }

    @Test
    fun ` network error`() = runTest {
        coEvery { mockUseCase.execute() } returns SafeResponse.NetworkError
        viewModel.getListExchanges()
        testDispatcher.scheduler.advanceUntilIdle()
        assertEquals("Verifique sua conexão com a internet", viewModel.errorState.value)
    }

    @Test
    fun ` generic error`() = runTest {
        coEvery { mockUseCase.execute() } returns SafeResponse.GenericError(404, null)
        viewModel.getListExchanges()
        testDispatcher.scheduler.advanceUntilIdle()
        assertEquals("Erro ao buscar dados.", viewModel.errorState.value)
    }

}