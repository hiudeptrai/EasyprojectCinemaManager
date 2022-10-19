package cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class seatInfo {
    private int row;
    private int column;
    private int price;

    @JsonIgnore
    private boolean isbooked;
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isIsbooked() {
        return isbooked;
    }

    public void setIsbooked(boolean isbooked) {
        this.isbooked = isbooked;
    }

    public seatInfo(int row, int column, int price, boolean isbooked) {
        this.row = row;
        this.column = column;
        this.price = price;
        this.isbooked = isbooked;
    }
}
