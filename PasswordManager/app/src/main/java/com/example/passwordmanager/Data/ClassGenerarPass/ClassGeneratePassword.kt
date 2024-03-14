package com.example.passwordmanager.Data.ClassGenerarPass

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.*
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import com.google.android.material.switchmaterial.SwitchMaterial
import java.security.SecureRandom

class ClassGeneratePassword(private val context: Context) {

    public fun generateRandomPassword(
        length: Int,
        includeUppercase: Boolean,
        includeLowercase: Boolean,
        includeNumbers: Boolean,
        includeSimbolsChars: Boolean,
        includeSpecialChars: Boolean,
        caracteresIncluidos: String?,
        excluircaracter: Boolean,
        excludecaracter: String?

    ): String {
        val charset = StringBuilder()
        if (!includeUppercase && !includeLowercase && !includeNumbers && !includeSimbolsChars && !includeSpecialChars) {
           charset.append("abcdefghijklmnopqrstuvwxyz!@#//$%^&*()_-+=<>?")
        } else {
            // Al menos una opción está seleccionada
            // Haz lo que necesites hacer en este caso
            println("Al menos una opción está seleccionada")
        }

        if (includeUppercase) charset.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ")
        if (includeLowercase) charset.append("abcdefghijklmnopqrstuvwxyz")
        if (includeNumbers) charset.append("0123456789")
        //Utiliza este apartado para eliminar los caracteres en el editext
        if (includeSimbolsChars) charset.append("!@#$%^&*()_-+=<>?")
        if(caracteresIncluidos.toString()!=excludecaracter.toString()) {
            if (excluircaracter) {
                charset.append(excludecaracter)
            }

            val customChars = caracteresIncluidos
            if (includeSpecialChars) {
                if (customChars!!.isNotEmpty()) {
                    charset.append(customChars)
                } else {
                    charset.append("!@#$%^&*()_-+=<>?")
                }
            }
        }else{
            println("Al menos una opción está seleccionada")
        }

        val filteredCharset = StringBuilder(charset.toString()).filterNot { excludecaracter?.contains(it) ?:false }

        val random = SecureRandom()
        val password = StringBuilder()
        if (filteredCharset.isNotEmpty()){
            //Toast.makeText(context, filteredCharset, Toast.LENGTH_SHORT).show()
            repeat(length) {
            password.append(filteredCharset[random.nextInt(filteredCharset.length)])
            }
        }
        caracteresIncluidos?.let { incluidos ->
            val incluidosList = incluidos.toList()
            val incluidosLength = incluidosList.size
            repeat(incluidosLength) { _ ->
                val randomIndex = random.nextInt(incluidosLength)
                val randomCharacter = incluidosList[randomIndex]
                // Reemplazar un carácter aleatorio en la contraseña con uno de caracteresIncluidos
                password[random.nextInt(password.length)] = randomCharacter
            }
        }

        return password.toString()
    }
}
