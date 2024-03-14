package com.example.passwordmanager.Ui.AddPasswordActivity

import android.animation.LayoutTransition
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import com.example.passwordmanager.Data.ClassGenerarPass.ClassGeneratePassword
import com.example.passwordmanager.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.slider.Slider
import com.google.android.material.switchmaterial.SwitchMaterial

class ClassBottomSheetGeneratePassword(private val contexto: Context): BottomSheetDialogFragment(){
    //Controles
    private lateinit var textviewPassword: TextView
    private lateinit var editTextAddChars: EditText
    private lateinit var editTextExcludeChars: EditText
    private lateinit var layoutContaineraddChar: LinearLayout
    private lateinit var layoutContainerExcChar: LinearLayout
    private lateinit var chipGroup: ChipGroup
    private lateinit var chipMayusculas: Chip
    private lateinit var chipMinusculas: Chip
    private lateinit var chipNumeros: Chip
    private lateinit var chipSimbolos: Chip

    private lateinit var switchIncludeChars: SwitchMaterial
    private lateinit var switchExcludeChars: SwitchMaterial

    private lateinit var sliderLengthPassword: Slider
    private lateinit var textViewLengt: TextView
    private lateinit var switchAllOptions: SwitchMaterial

    private lateinit var buttomAceptar: Button
    private lateinit var view: View

    private var listener: OnPasswordGeneratedListener? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.generate_password_layout,container,false)
        return view
    }

    override fun onStart() {
        super.onStart()
        //Agregar o referenciar la clase: ClassGeneratePassword
        val generatePassword = ClassGeneratePassword(view.context)

        //Referencia de controles
        textviewPassword = view.findViewById(R.id.textViewPassword)
        editTextAddChars = view.findViewById(R.id.editTextAddChars)
        editTextExcludeChars = view.findViewById(R.id.editTextExcludeChars)

        layoutContaineraddChar = view.findViewById(R.id.layoutContaineraddChar)
        layoutContainerExcChar = view.findViewById(R.id.layoutContainerExcChar)
        layoutContaineraddChar.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        layoutContainerExcChar.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

        sliderLengthPassword = view.findViewById(R.id.sliderLengthPassword)
        textViewLengt = view.findViewById(R.id.textViewLengt)
        switchAllOptions = view.findViewById(R.id.switchAllOptions)
        chipGroup = view.findViewById(R.id.chipGroup)
        chipMayusculas = view.findViewById(R.id.chipMayusculas)
        chipMinusculas = view.findViewById(R.id.chipMinusculas)
        chipNumeros = view.findViewById(R.id.chipNumeros)
        chipSimbolos = view.findViewById(R.id.chipSimbolos)
        switchIncludeChars = view.findViewById(R.id.switchIncludeChars)
        switchExcludeChars = view.findViewById(R.id.switchExcludeChars)

        buttomAceptar = view.findViewById(R.id.buttomAceptar)

        chipMayusculas.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked)
            {
                textviewPassword.text = generatePassword.generateRandomPassword(sliderLengthPassword.value.toInt(),
                    chipMayusculas.isChecked,
                    chipMinusculas.isChecked,
                    chipNumeros.isChecked,
                    chipSimbolos.isChecked,
                    switchIncludeChars.isChecked,
                    editTextAddChars.text.toString(),
                    switchExcludeChars.isChecked,
                    editTextExcludeChars.text.toString()
                )
            }
            else
            {
                textviewPassword.text = generatePassword.generateRandomPassword(sliderLengthPassword.value.toInt(),
                    chipMayusculas.isChecked,
                    chipMinusculas.isChecked,
                    chipNumeros.isChecked,
                    chipSimbolos.isChecked,
                    switchIncludeChars.isChecked,
                    editTextAddChars.text.toString(),
                    switchExcludeChars.isChecked,
                    editTextExcludeChars.text.toString()
                )
            }
        }
        chipMinusculas.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked)
            {
                textviewPassword.text = generatePassword.generateRandomPassword(sliderLengthPassword.value.toInt(),
                    chipMayusculas.isChecked,
                    chipMinusculas.isChecked,
                    chipNumeros.isChecked,
                    chipSimbolos.isChecked,
                    switchIncludeChars.isChecked,
                    editTextAddChars.text.toString(),
                    switchExcludeChars.isChecked,
                    editTextExcludeChars.text.toString()
                )
            }
            else
            {
                textviewPassword.text = generatePassword.generateRandomPassword(sliderLengthPassword.value.toInt(),
                    chipMayusculas.isChecked,
                    chipMinusculas.isChecked,
                    chipNumeros.isChecked,
                    chipSimbolos.isChecked,
                    switchIncludeChars.isChecked,
                    editTextAddChars.text.toString(),
                    switchExcludeChars.isChecked,
                    editTextExcludeChars.text.toString()
                )
            }
        }
        chipNumeros.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked)
            {
                textviewPassword.text = generatePassword.generateRandomPassword(sliderLengthPassword.value.toInt(),
                    chipMayusculas.isChecked,
                    chipMinusculas.isChecked,
                    chipNumeros.isChecked,
                    chipSimbolos.isChecked,
                    switchIncludeChars.isChecked,
                    editTextAddChars.text.toString(),
                    switchExcludeChars.isChecked,
                    editTextExcludeChars.text.toString()
                )
            }
            else
            {
                textviewPassword.text = generatePassword.generateRandomPassword(sliderLengthPassword.value.toInt(),
                    chipMayusculas.isChecked,
                    chipMinusculas.isChecked,
                    chipNumeros.isChecked,
                    chipSimbolos.isChecked,
                    switchIncludeChars.isChecked,
                    editTextAddChars.text.toString(),
                    switchExcludeChars.isChecked,
                    editTextExcludeChars.text.toString()
                )
            }
        }
        chipSimbolos.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked)
            {
                textviewPassword.text = generatePassword.generateRandomPassword(sliderLengthPassword.value.toInt(),
                    chipMayusculas.isChecked,
                    chipMinusculas.isChecked,
                    chipNumeros.isChecked,
                    chipSimbolos.isChecked,
                    switchIncludeChars.isChecked,
                    editTextAddChars.text.toString(),
                    switchExcludeChars.isChecked,
                    editTextExcludeChars.text.toString()
                )
            }
            else
            {
                textviewPassword.text = generatePassword.generateRandomPassword(sliderLengthPassword.value.toInt(),
                    chipMayusculas.isChecked,
                    chipMinusculas.isChecked,
                    chipNumeros.isChecked,
                    chipSimbolos.isChecked,
                    switchIncludeChars.isChecked,
                    editTextAddChars.text.toString(),
                    switchExcludeChars.isChecked,
                    editTextExcludeChars.text.toString()
                )
            }
        }

        switchAllOptions.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
            {
                chipMayusculas.isChecked = true
                chipMinusculas.isChecked = true
                chipNumeros.isChecked = true
                chipSimbolos.isChecked = true

                textviewPassword.text = generatePassword.generateRandomPassword(sliderLengthPassword.value.toInt(),
                    chipMayusculas.isChecked,
                    chipMinusculas.isChecked,
                    chipNumeros.isChecked,
                    chipSimbolos.isChecked,
                    switchIncludeChars.isChecked,
                    editTextAddChars.text.toString(),
                    switchExcludeChars.isChecked,
                    editTextExcludeChars.text.toString()
                )
            }
            else
            {
                chipMayusculas.isChecked = false
                chipMinusculas.isChecked = false
                chipNumeros.isChecked = false
                chipSimbolos.isChecked = false

                textviewPassword.text = generatePassword.generateRandomPassword(sliderLengthPassword.value.toInt(),
                    chipMayusculas.isChecked,
                    chipMinusculas.isChecked,
                    chipNumeros.isChecked,
                    chipSimbolos.isChecked,
                    switchIncludeChars.isChecked,
                    editTextAddChars.text.toString(),
                    switchExcludeChars.isChecked,
                    editTextExcludeChars.text.toString()
                )
            }
        }
        textViewLengt.text = sliderLengthPassword.value.toInt().toString()
        sliderLengthPassword.addOnChangeListener { slider, value, fromUser ->
            textViewLengt.text = sliderLengthPassword.value.toInt().toString()
            textviewPassword.text = generatePassword.generateRandomPassword(value.toInt(),
                chipMayusculas.isChecked,
                chipMinusculas.isChecked,
                chipNumeros.isChecked,
                chipSimbolos.isChecked,
                switchIncludeChars.isChecked,
                editTextAddChars.text.toString(),
                switchExcludeChars.isChecked,
                editTextExcludeChars.text.toString()
                )
        }

        switchIncludeChars.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked)
            {
                editTextAddChars.isEnabled = true
            }
            else
            {
                editTextAddChars.isEnabled = false
            }
        }

        switchExcludeChars.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
            {
                editTextExcludeChars.isEnabled = true
            }
            else
            {
                editTextExcludeChars.isEnabled = false
            }
        }


        //Esto permite enviar el texto a la activity AddPasswordActivity
        buttomAceptar.setOnClickListener {
            listener?.onPasswordGenerated(textviewPassword.text.toString())
            dismiss()
        }
    }


    //Interface encargada de almacenar el password para obtenerlo desde la activity
    interface OnPasswordGeneratedListener {
        fun onPasswordGenerated(password: String)
    }
    fun setOnPasswordGeneratedListener(listener: OnPasswordGeneratedListener) {
        this.listener = listener
    }
}


