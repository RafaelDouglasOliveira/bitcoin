package br.com.testemercadobitcoin.usecase

import br.com.testemercadobitcoin.remote.SafeResponse
import br.com.testemercadobitcoin.remote.repository.CoinApiRepository
import br.com.testemercadobitcoin.ui.list.usecase.ListExchangesUseCase
import br.com.testemercadobitcoin.ui.list.usecase.mapper.ExchangeMapper.toExchange
import br.com.testemercadobitcoin.utils.MockExchange.listExchangeMock
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ListExchangesUseCaseTest {
    private val coinApiRepositoryMock: CoinApiRepository = mockk()
    private val useCase = ListExchangesUseCase(coinApiRepositoryMock)

    @Test
    fun `chamada do usecase com sucesso`(): Unit = runBlocking {

        coEvery { coinApiRepositoryMock.getListExchanges()} returns SafeResponse.Success(
            listExchangeMock.toExchange()
        )

        val response = useCase.execute()
        assert(response is SafeResponse.Success)
        assertEquals(listExchangeMock.toExchange(), (response as SafeResponse.Success).value)
        assertEquals(listExchangeMock.toExchange(), response.value)
    }

    @Test
    fun `chamada do usecase error generico`() = runBlocking {
        coEvery { coinApiRepositoryMock.getListExchanges() } returns SafeResponse.GenericError(404, null)
        val response = useCase.execute()

        assert(response is SafeResponse.GenericError)
        val error = response as SafeResponse.GenericError
        TestCase.assertEquals(404, error.code)
        TestCase.assertEquals(null, error.error)

        coVerify { coinApiRepositoryMock.getListExchanges() }

    }
}