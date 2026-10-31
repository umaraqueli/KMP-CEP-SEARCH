package org.example.project.data.model

import kotlinx.serialization.Serializable
import org.example.project.domain.model.Endereco

@Serializable
data class EnderecoResponse(
    val cep: String? = null,
    val logradouro: String? = null,
    val bairro: String? = null,
    val localidade: String? = null,
    val uf: String? = null,
    val erro: String? = null
) {
    fun toDomain() = Endereco(
        cep = cep.orEmpty(),
        logradouro = logradouro.orEmpty(),
        bairro = bairro.orEmpty(),
        cidade = localidade.orEmpty(),
        uf = uf.orEmpty()
    )
}
