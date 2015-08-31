package parser.operators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Priority {
    private List<Operator> operators;
    
    public Priority () {
        operators = new ArrayList<Operator>();
    }
    
    public List<Operator> getOperators () {
        return Collections.unmodifiableList(operators);
    }
}
