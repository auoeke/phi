package user11681.phi.program.element;

import java.util.Map;

public class Input {
    private final Map<String, Object> arguments;

    public Input(Map<String, Object> arguments) {
        this.arguments = arguments;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String name) {
        return (T) this.arguments.get(name);
    }
}
