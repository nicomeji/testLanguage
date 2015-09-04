package parser.sentence;

import lombok.Data;

@Data
public class SimpleSentence {
    private final String returnedType;

    // TODO - La sentencia puede tener un tipo: COMPARACION, ASIGNACION, ACCION
    // Esto puede ser útil para el for:
    // for (ASIGNACION; COMPARACION; ACCION)
    
    public SimpleSentence (String returnedType) {
        this.returnedType = returnedType;
    }
}
