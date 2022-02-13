package drogapop.entity;

import java.lang.reflect.Field;

public class Entidade {

    /**
     * Da formato a la información de la base de datos para que se muestre ordenado
     *
     * @return result.toString()
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append(this.getClass().getName());
        result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        //print field names paired with their values
        for (Field field : fields) {
            result.append("  ");
            try {
                result.append(field.getName());
                result.append(": ");
                //requires access to private field:
                result.append(field.get(this));
            } catch (IllegalAccessException ex) {
                // System.out.println(ex);
            }
            result.append(newLine);
        }

        return result.toString();
    }
}
