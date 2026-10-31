package org.example.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.ui.CepUiState
import org.example.project.ui.CepViewModel
import org.example.project.ui.theme.ProjectTheme

@Composable
@Preview
fun App() {
    val viewModel: CepViewModel = viewModel { CepViewModel() }
    val cep by viewModel.cep.collectAsState()
    val state by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(state) {
        if (state is CepUiState.Error) {
            snackbarHostState.showSnackbar((state as CepUiState.Error).mensagem)
        }
    }
    ProjectTheme {
        Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { innerPadding ->
        Column(Modifier.fillMaxSize().padding(innerPadding).padding(top = 24.dp)) {
            OutlinedTextField(
                value = cep,
                onValueChange = viewModel::onCepChange,
                label = { Text("Digite o CEP") },
                singleLine = true,
                enabled = state !is CepUiState.Loading,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(12.dp))
            ElevatedButton(
                onClick = viewModel::buscarCep,
                enabled = cep.length == 8 && state !is CepUiState.Loading,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            ) {
                if (state is CepUiState.Loading) {
                    CircularProgressIndicator()
                } else {
                    Text("Buscar")
                }
            }
            Spacer(Modifier.height(12.dp))
            val endereco = (state as? CepUiState.Success)?.endereco
            ElevatedCard(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(Modifier.padding(16.dp)) {
                    Resultado("CEP", endereco?.cep.orEmpty())
                    Resultado("Logradouro", endereco?.logradouro.orEmpty())
                    Resultado("Bairro", endereco?.bairro.orEmpty())
                    Resultado("Localidade", endereco?.cidade.orEmpty())
                    Resultado("UF", endereco?.uf.orEmpty())
                }
            }
            }
        }
    }
}

@Composable
private fun Resultado(label: String, valor: String) {
    Text("$label: $valor", modifier = Modifier.padding(vertical = 5.dp))
}
