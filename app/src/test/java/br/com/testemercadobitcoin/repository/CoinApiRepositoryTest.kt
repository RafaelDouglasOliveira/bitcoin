package br.com.testemercadobitcoin.repository

import br.com.testemercadobitcoin.remote.CoinApi
import br.com.testemercadobitcoin.remote.SafeResponse
import br.com.testemercadobitcoin.remote.repository.CoinApiRepository
import br.com.testemercadobitcoin.ui.list.usecase.mapper.ExchangeMapper.toExchange
import br.com.testemercadobitcoin.utils.MockExchange.listExchangeMock
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import retrofit2.Response
import java.io.IOException

class CoinApiRepositoryTest {

    private val mockApi: CoinApi = mockk(relaxed = true)
    private val repository = CoinApiRepository(mockApi)

    @Test
    fun `sucesso ao retorna os itens da query`(): Unit = runBlocking {

        coEvery {
            mockApi.getExchanges()
        } returns listExchangeMock

        val result = repository.getListExchanges()
        assert(result is SafeResponse.Success)
        assertEquals(listExchangeMock.toExchange(), (result as SafeResponse.Success).value)
        assertEquals(listExchangeMock.toExchange(), result.value)
        coVerify { mockApi.getExchanges() }
    }

    @Test
    fun `Retorna erro generico pull request `(): Unit = runBlocking {
        coEvery { mockApi.getExchanges() } throws IOException("Erro de rede")
        val result = repository.getListExchanges()
        assert(result is SafeResponse.NetworkError)
        coVerify { mockApi.getExchanges() }
    }

    @Test
    fun `Retorna erro de rede pull request`() = runBlocking {
        val errorResponse: Response<*> = mockk {
            every { code() } returns 404
            every { message() } returns "Not Found"
        }
        coEvery {mockApi.getExchanges() } throws retrofit2.HttpException(
            errorResponse
        )
        val result = repository.getListExchanges()
        assert(result is SafeResponse.GenericError)
        val error = result as SafeResponse.GenericError
        Assert.assertEquals(404, error.code)
        Assert.assertEquals(errorResponse, error.error)
        coVerify {mockApi.getExchanges() }
    }
}