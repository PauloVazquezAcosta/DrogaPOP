package drogapop.entity;

import java.lang.reflect.Field;

public class Entidade {

    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append("Datos de ");
        result.append(this.getClass().getName());

        // Determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        // Print field names paired with their values
        for (Field field : fields) {
            try {
                // Ejecuta field.get para comprobar que se puede acceder
                // Si no se puede acceder, el resto del código no se ejecuta
                field.get(this);
                result.append("  ");
                result.append("  " + field.getName());
                result.append(": ");
                result.append(field.get(this));
            } catch (IllegalAccessException ex) {}
            result.append(newLine);
        }
        return result.toString();
    }
}
