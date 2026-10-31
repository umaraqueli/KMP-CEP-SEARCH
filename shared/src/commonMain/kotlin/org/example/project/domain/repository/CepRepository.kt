package org.example.project.domain.repository

import org.example.project.domain.model.Endereco

interface CepRepository {
    suspend fun buscarCep(cep: String): Result<Endereco>

    suspend fun buscarCepOrThrow(cep: String): Endereco = buscarCep(cep).getOrThrow()
}
