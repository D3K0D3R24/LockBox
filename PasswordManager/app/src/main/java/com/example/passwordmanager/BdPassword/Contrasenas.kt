package com.example.passwordmanager.BdPassword

data class Contrasenas (
    var id: Int,
    var image: ByteArray,
    var title: String,
    var url: String,
    var username: String,
    var password: String,
    var nota: String
)//clase que se usara para el histarial de contraseñas