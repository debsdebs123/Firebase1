package com.example.appfirestore

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.appfirestore.ui.theme.AppFirestoreTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.FirebaseFirestore


// Obter instância do Firestore
private val db = FirebaseFirestore.getInstance()

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {


            MaterialTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    val nome = remember { mutableStateOf("") }
                    val endereco = remember { mutableStateOf("") }
                    val bairro = remember { mutableStateOf("") }
                    val cep = remember { mutableStateOf("") }
                    val cidade = remember { mutableStateOf("") }
                    val estado = remember { mutableStateOf("") }


                    Text(
                        text = "CADASTRAR CLIENTES",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFB39DDB)
                        ),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )


                    OutlinedTextField(
                        value = nome.value,
                        onValueChange = { nome.value = it },
                        label = { Text("Nome") },
                        placeholder = { Text("Digite o nome") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )

                    OutlinedTextField(
                        value = endereco.value,
                        onValueChange = { endereco.value = it },
                        label = { Text("Endereço") },
                        placeholder = { Text("Digite o endereço") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )

                    OutlinedTextField(
                        value = bairro.value,
                        onValueChange = { bairro.value = it },
                        label = { Text("Bairro") },
                        placeholder = { Text("Digite o bairro") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )

                    OutlinedTextField(
                        value = cep.value,
                        onValueChange = { cep.value = it },
                        label = { Text("CEP") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    OutlinedTextField(
                        value = cidade.value,
                        onValueChange = { cidade.value = it },
                        label = { Text("Cidade") },
                        placeholder = { Text("Digite a cidade") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )

                    OutlinedTextField(
                        value = estado.value,
                        onValueChange = { estado.value = it },
                        label = { Text("Estado") },
                        placeholder = { Text("Digite o estado") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )

                    Button(
                        onClick = {
                            val data = hashMapOf(
                                "nome" to nome.value,
                                "endereco" to endereco.value,
                                "bairro" to bairro.value,
                                "cep" to cep.value,
                                "cidade" to cidade.value,
                                "estado" to estado.value
                            )

                            db.collection("registros")
                                .add(data)
                                .addOnSuccessListener {

                                    nome.value = ""
                                    endereco.value = ""
                                    bairro.value = ""
                                    cep.value = ""
                                    cidade.value = ""
                                    estado.value = ""
                                }
                                .addOnFailureListener {

                                }
                        },
                        modifier = Modifier.padding(vertical = 16.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFFB39DDB	))
                    ) {
                        Text("Criar Registro", style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }
    }
}