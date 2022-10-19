package cinema.entity;

public class seat {
    private int row;
    private int column;

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

    public seat(){
    }
    public seat(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public  boolean equals(seat seat){
        if(this.getColumn()==seat.getColumn()&&this.getRow()==seat.getRow()){
            return true;
        }
        return false;
    }
}
