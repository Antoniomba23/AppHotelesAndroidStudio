package com.example.apphotel;

import com.google.android.material.textfield.TextInputLayout;
import org.mindrot.jbcrypt.BCrypt;

public class FormUtils {

    // Comprueba si un TextInputLayout está vacío
    public boolean isTILEmpty(TextInputLayout textInputLayout) {
        if (textInputLayout.getEditText() == null) return true;
        return textInputLayout.getEditText().getText().toString().trim().isEmpty();
    }

    // Obtiene el texto del campo
    public String getTILText(TextInputLayout textInputLayout) {
        if (textInputLayout.getEditText() == null) return "";
        return textInputLayout.getEditText().getText().toString().trim();
    }

    // Genera un hash seguro para la contraseña
    public String generateHashedPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Verifica si la contraseña coincide con el hash
    public boolean checkPassword(String candidate, String hashed) {
        return BCrypt.checkpw(candidate, hashed);
    }
}
