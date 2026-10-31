package org.example.project.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.example.project.data.model.EnderecoResponse

class ViaCepApiImpl(private val client: HttpClient = KtorClient.client) : ViaCepApi {
    override suspend fun buscarCep(cep: String): EnderecoResponse =
        client.get("https://viacep.com.br/ws/$cep/json").body()
}
