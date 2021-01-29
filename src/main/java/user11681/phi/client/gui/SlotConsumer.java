package user11681.phi.client.gui;

@FunctionalInterface
public interface SlotConsumer {
    void accept(int x, int y, ElementSlot slot);
}
