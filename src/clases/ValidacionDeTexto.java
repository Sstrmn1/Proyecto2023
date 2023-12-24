package clases;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class ValidacionDeTexto {

    // applyNumericFilter permite caracteres vacíos y números en un JTextField
    public static void applyNumericFilter(JTextField textField) {
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if (string.isEmpty() || string.matches("[0-9]+")) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (text.isEmpty() || text.matches("[0-9]+")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }

    public static void longitudDeTelefono(JTextField textField, int maxLength) {
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if (fb.getDocument().getLength() + string.length() <= maxLength) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                int currentLength = fb.getDocument().getLength();
                int newLength = currentLength - length + text.length();

                if (newLength <= maxLength) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }

    public static boolean esNumero(String texto) {
        try {
            Double.parseDouble(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String generarCodigo(String fFarmaceutica, String descripcion) {
        // Captura el primer caracter de fFarmaceutica
        char primerCaracter = fFarmaceutica.charAt(0);

        // Captura los 4 primeros caracteres de la descripcion
        String primeros4CaracteresDescripcion = descripcion.substring(0, Math.min(4, descripcion.length()));

        // Inicializa la variable concentracion
        String concentracion = "";

        // Si hay números en la descripción, captura hasta 3 caracteres
        for (char c : descripcion.toCharArray()) {
            if (Character.isDigit(c) && concentracion.length() < 3) {
                concentracion += c;
            }
        }

        // Concatena y capitaliza
        String resultado = String.format("%s%s%s", Character.toUpperCase(primerCaracter),
                primeros4CaracteresDescripcion.toUpperCase(), concentracion);

        return resultado;
    }

    public static String generarLoteCodigo(String cadena) {
        // Verifica si la cadena no es nula y tiene al menos dos caracteres
        if (cadena != null && cadena.length() >= 2) {
            // Remueve los dos primeros caracteres
            cadena = cadena.substring(2);
        }

        // Remueve los caracteres "-"
        cadena = cadena.replace("-", "");

        return cadena;
    }

}
