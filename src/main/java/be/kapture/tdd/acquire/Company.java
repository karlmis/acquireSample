package be.kapture.tdd.acquire;

import java.util.List;

public class Company {

    private Company(CompanyId companyId, List<Coordinates> coordinatesList) {
    }

    public static Company create(CompanyId companyId, List<Coordinates> coordinatesList) {
        throw new UnsupportedOperationException();
    }


    public int getPrice() {
        throw new UnsupportedOperationException();
    }

    public boolean isNextTo(Coordinates coordinates) {
        throw new UnsupportedOperationException();
    }

    public int getSize() {
        throw new UnsupportedOperationException();
    }

    public void addTile(Coordinates coordinates) {
        throw new UnsupportedOperationException();
    }

    public int getRemainingShares() {
        throw new UnsupportedOperationException();
    }

    public void addShares(int toAdd) {
        throw new UnsupportedOperationException();
    }

    public void removeShares(int toRemove) {
        throw new UnsupportedOperationException();
    }

}
