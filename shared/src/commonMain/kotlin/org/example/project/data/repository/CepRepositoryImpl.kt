package org.example.project.data.repository

import org.example.project.data.remote.ViaCepApi
import org.example.project.data.remote.ViaCepApiImpl
import org.example.project.domain.model.Endereco
import org.example.project.domain.repository.CepRepository

class CepRepositoryImpl(private val api: ViaCepApi) : CepRepository {
    constructor() : this(ViaCepApiImpl())

    override suspend fun buscarCep(cep: String): Result<Endereco> = runCatching {
        val response = api.buscarCep(cep)
        if (response.erro.equals("true", ignoreCase = true)) {
            throw NoSuchElementException("CEP não encontrado.")
        }
        response.toDomain()
    }
}
