package com.example.passwordmanager.Ui.LoginActivity

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.example.passwordmanager.GenerarPass.GenerarPass
import com.example.passwordmanager.ListaDeContrasenas
import com.example.passwordmanager.Ui.AddPasswordActivity.AddPasswordActivity

import java.util.concurrent.Executor

class FingerPrint {

    private lateinit var biometricPrompt: androidx.biometric.BiometricPrompt
    private lateinit var prompt: androidx.biometric.BiometricPrompt.PromptInfo
    private lateinit var executor: Executor

    //LO QUE VA A MOSTRAR AL USUARIO
    var promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle("Ingresar")
        .setSubtitle("Coloca tu huella")
        .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)
        .build()

    fun checkDevice( contexto: FragmentActivity){
        //SE COMPRUEBA SI EL DISPOSITIVO CUMPLE PARA LA IDENTIFICACION
        val biometricManager = BiometricManager.from(contexto)
        when(biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)){
            BiometricManager.BIOMETRIC_SUCCESS ->{

                Log.d("MY_APP_TAG","Si es compatible")
                executor = ContextCompat.getMainExecutor(contexto)
                biometricPrompt = androidx.biometric.BiometricPrompt(contexto, executor,
                    object : BiometricPrompt.AuthenticationCallback() {
                        override fun onAuthenticationError(errorCode: Int,
                                                           errString: CharSequence) {
                            super.onAuthenticationError(errorCode, errString)
                            Toast.makeText(contexto,
                                "Authentication error: $errString", Toast.LENGTH_SHORT)
                                .show()
                        }
                        override fun onAuthenticationSucceeded(
                            result: BiometricPrompt.AuthenticationResult) {
                            super.onAuthenticationSucceeded(result)
                            /*SI SE AUTENTICO CORRECTAMENTE*/
                            Toast.makeText(contexto,
                                "Bienvenido!", Toast.LENGTH_SHORT)
                                .show()
                            //Abro la otra Activity
                            //AQUI SE PONE EL CODIGO PARA INGRESAR A LA OTRA ACTIVITY
                            val intent = Intent(contexto, AddPasswordActivity::class.java)
                            contexto.startActivity(intent)
                        }

                        /*SI FALLO LA LECTURA DE LA HUELLA*/
                        override fun onAuthenticationFailed() {
                            super.onAuthenticationFailed()
                            Toast.makeText(contexto, "No mi chavo no puedes",
                                Toast.LENGTH_SHORT)
                                .show()

                        }
                    })
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                Toast.makeText(contexto, "No mi chavo no puedes",
                    Toast.LENGTH_SHORT)
                    .show()
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                Toast.makeText(contexto, "No mi chavo no puedes",
                    Toast.LENGTH_SHORT)
                    .show()
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->{

                //EN CASO DE NO TENER ACTIVADO NADA
                Toast.makeText(contexto, "No mi chavo no puedes",
                    Toast.LENGTH_SHORT)
                    .show()
            }


        }

        biometricPrompt.authenticate(promptInfo)


    }
}