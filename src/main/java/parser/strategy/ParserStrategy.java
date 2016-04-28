package parser.strategy;

import java.util.HashMap;
import java.util.Map;

public class ParserStrategy {

    private final Map<String, Strategy> strategies;

    public ParserStrategy(Map<String, Strategy> strategies) {
        this.strategies = new HashMap<String, Strategy>() {
            private static final long serialVersionUID = 1L;

            // TODO - Ver q hacer cuando aparece algo q no esta contemplado.
            // Se tira eception? puede venir una palabra q ya esta siendo vista
            // por otro parser.
            @Override
            public Strategy get(Object key) {
                return super.get(key);
            }
        };
    }

    public Strategy getStrategy(String source) {
        return strategies.get(source);
    }
}

class Strategy {
    
};
