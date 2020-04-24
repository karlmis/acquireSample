package be.kapture.tdd.acquire;

import java.util.List;

public class Coordinates {

    static final int MAX_X = 12;
    static final int MAX_Y = 9;


    private Coordinates(int x, int y) {

    }

    public static Coordinates create(int x, int y) {
        throw new UnsupportedOperationException();    }


    public List<Coordinates> getNeighbours() {
        throw new UnsupportedOperationException();    }



}
