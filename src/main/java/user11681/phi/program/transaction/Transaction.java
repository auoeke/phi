package user11681.phi.program.transaction;

import java.util.Map;

public class Transaction<O> {
    private final Map<String, Object> arguments;

    O output;

    public Transaction(Map<String, Object> arguments) {
        this.arguments = arguments;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String name) {
        return (T) this.arguments.get(name);
    }

    public void output(O output) {
        this.output = output;
    }
}
