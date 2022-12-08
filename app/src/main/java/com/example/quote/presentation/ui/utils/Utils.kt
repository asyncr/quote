package com.example.quote.presentation.ui.utils

import android.app.Activity
import android.content.Context
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.quote.R
import com.example.quote.domain.model.QuoteModel
import com.example.quote.presentation.view.MainActivity

class Utils {
    companion object {
        fun showAlert(cont: Context, title: String, message: String):AlertDialog {
            val builder = AlertDialog.Builder(cont)
            builder.setTitle(title)
            builder.setMessage(message)
            return builder.create()
        }

        fun isValidField(edt: EditText): Boolean {
            with(edt.text.toString()) {
                return this.isNotEmpty() && this.isNotBlank()
            }
        }

        fun isValidNumberField(edt: EditText): Boolean {
            with(edt.text.toString()) {
                return this.isNotEmpty() && this.isNotBlank() && this.toIntOrNull() != null
            }
        }

        fun removeStrangeCharacters(str: String): String {
            //Remover los saltos de linea y reemplazarlos por un espacio en blanco
            var withoutBreakLine = str.replace("\r", " ")
            //Reeplazar por un espacio en blanco los espacios mas de uno
            withoutBreakLine = withoutBreakLine.replace(" +".toRegex(), " ")
            //Agregar un punto al final de la cadena si no lo tiene
            if (withoutBreakLine.last() != '.') {
                withoutBreakLine += "."
            }
            return withoutBreakLine
        }

        fun quoteToString(quote: QuoteModel): String {
            return "ID: ${quote.id} \nCita: ${quote.quote} \nAutor: ${quote.author}"
        }






    }
}

enum class CustomMessages(val message: String) {
    INVALID_ID("Ingresa un id valido"),
    INVALID_QUOTE("Ingresa una cita valida"),
    INVALID_AUTHOR("Ingresa un autor valido"),
    INVALID_USERNAME("Usuario no valido"),
    INVALID_PASSWORD("Contraseña incorrecta"),
    ERROR_NETWORK("Error de red"),
    QUOTE_SAVED("Cita agregada"),
    LOGIN_SUCCESS("Login exitoso"),
    CONFIRM_SAVE("¿Deseas guardar los cambios?"),
}