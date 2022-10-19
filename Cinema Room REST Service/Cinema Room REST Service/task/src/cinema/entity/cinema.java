package cinema.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class cinema {
    private int total_rows;
    private int total_columns;
    private List<seatInfo> available_seats;

    public cinema(){

    }

    public cinema(int total_rows,int total_columns){
        this.total_rows=9;
        this.total_columns=9;
        available_seats= new ArrayList<seatInfo>();
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(i<4) {
                    available_seats.add(new seatInfo(i + 1, j + 1,10,false ));
                }else{
                    available_seats.add(new seatInfo(i + 1, j + 1,8,false ));
                }

            }
        }
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public List<seatInfo> getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(List<seatInfo> available_seats) {
        this.available_seats = available_seats;
    }
}
