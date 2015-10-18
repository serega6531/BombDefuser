package ru.serega6531.bombefuser.solvers;

import ru.serega6531.bombefuser.enums.WireColor;

public class SimpleWiresSolver implements Solver {

    private WireColor[] wires;

    public SimpleWiresSolver(WireColor[] wires) {
        this.wires = wires;
    }

    @Override
    public String solve() {
        switch (wires.length){
            case 3:
                if(noWires(WireColor.RED))
                    return "Cut the first fire";

                if(wires[2] == WireColor.WHITE)
                    return "Cut the last wire";

                if(hasWires(WireColor.BLUE, 2))
                    return "Cut the last BLUE wire";

                return "Cut the last wire";
            case 4:

            case 5:

            case 6:

        }

        return "Inner error";
    }

    private boolean noWires(WireColor color){
        for(WireColor wire : wires)
            if(wire == color) return false;

        return true;
    }

    private boolean hasWire(WireColor color){
        for(WireColor wire : wires)
            if(wire == color) return true;

        return false;
    }

    private boolean hasWires(WireColor color, int need){
        int amount = 0;

        for(WireColor wire : wires) {
            if (wire == color){
                amount++;
            }

            if(amount == need)
                return true;
        }

        return false;
    }

}
