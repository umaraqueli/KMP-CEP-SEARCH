package org.example.project.data.remote

import org.example.project.data.model.EnderecoResponse

interface ViaCepApi {
    suspend fun buscarCep(cep: String): EnderecoResponse
}
