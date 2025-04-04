package com.example.appfirebase

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appfirebase.ui.theme.AppFirebaseTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppFirebaseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppAula()
                }
            }
        }
    }
}


@Composable
fun AppAula() {
    var nome by remember { mutableStateOf("") }
    var sobrenome by remember { mutableStateOf("") }
    var endereco by remember { mutableStateOf("") }
    var cidade by remember { mutableStateOf("") }
    var idade by remember { mutableStateOf("") }

    Column(
        Modifier
            .background(Color.LightGray)
            .fillMaxSize()
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(20.dp),
            Arrangement.Center
        ) {
            Text("App Firebase", fontSize = 30.sp)
        }

        // Campo Nome
        Row(
            Modifier

                .fillMaxWidth()
                .padding(20.dp),
            Arrangement.Center
        ) {
            TextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome:") }
            )
        }

        // Campo Telefone
        Row(
            Modifier
                .fillMaxWidth()
                .padding(20.dp),
            Arrangement.Center
        ) {
            TextField(
                value = sobrenome,
                onValueChange = { sobrenome = it },
                label = { Text("Sobrenome:") }
            )
        }

        // Campo Endereço
        Row(
            Modifier
                .fillMaxWidth()
                .padding(20.dp),
            Arrangement.Center
        ) {
            TextField(
                value = endereco,
                onValueChange = { endereco = it },
                label = { Text("Endereço:") }
            )
        }

        // Campo Cidade
        Row(
            Modifier
                .fillMaxWidth()
                .padding(20.dp),
            Arrangement.Center
        ) {
            TextField(
                value = cidade,
                onValueChange = { cidade = it },
                label = { Text("Cidade:") }
            )
        }

        // Campo Idade
       Row(
            Modifier
                .fillMaxWidth()
                .padding(20.dp),
            Arrangement.Center
        ) {
            TextField(
                value = idade,
                onValueChange = { idade = it },
                label = { Text("Idade:") }
            )
        }

        // Botão Cadastrar
        Row(
            Modifier
                .fillMaxWidth()
                .padding(20.dp),
            Arrangement.Center
        ) {
            Button(
                onClick = {
                    // Ação de cadastrar
                    val db = Firebase.firestore

                    // Create a new user with a first and last name
                    val user = hashMapOf(
                        "nome" to nome,
                        "sobrenome" to sobrenome,
                        "endereco" to endereco,
                        "cidade" to cidade,
                        "idade" to idade
                    )

// Add a new document with a generated ID
                    db.collection("users")
                        .add(user)
                        .addOnSuccessListener { documentReference ->
                            Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error adding document", e)
                        }
                    nome = ""
                    sobrenome = ""
                    endereco = ""
                    idade = ""
                    cidade = ""

                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Cadastrar", fontSize = 25.sp)
            }
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    AppFirebaseTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            AppAula()
        }
    }
}
