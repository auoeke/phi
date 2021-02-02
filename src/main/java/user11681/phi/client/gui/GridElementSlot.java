package user11681.phi.client.gui;

import user11681.phi.program.Program;

public class GridElementSlot extends ElementSlot {
    private ElementGrid grid;

    private int row;
    private int column;

    public GridElementSlot grid(ElementGrid grid) {
        this.grid = grid;

        return this;
    }

    public GridElementSlot index(int row, int column) {
        this.row = row;
        this.column = column;

        return this;
    }

    public GridElementSlot right() {
        if (this.column == Program.SIZE - 1) {
            return null;
        }

        return this.grid.get(this.column + 1);
    }

    public GridElementSlot up() {
        if (this.row == 0) {
            return null;
        }

        return this.grid.get(this.row - 1);
    }

    public GridElementSlot left() {
        if (this.column == 0) {
            return null;
        }

        return this.grid.get(this.column - 1);
    }

    public GridElementSlot down() {
        if (this.row == Program.SIZE - 1) {
            return null;
        }

        return this.grid.get(this.row + 1);
    }
}
