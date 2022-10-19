package cinema.controller;

import cinema.entity.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.UUID.randomUUID;

@RestController
public class mainController {
   static final cinema cinema = new cinema(9,9);
   static final ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap<String, seatInfo>();

   static int income;
    @GetMapping("/seats")
    public cinema getSeats() {
        return cinema;
    }

    @PostMapping("/purchase")
    public Object getPurchase(@RequestBody seat seat) {
        if(seat.getRow()>9||seat.getColumn()>9||seat.getRow()<1||seat.getColumn()<1){
            return new ResponseEntity<>(new error("The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        }else {
            for (seatInfo element : cinema.getAvailable_seats()) {
                if (element.getRow()==seat.getRow()&&element.getColumn()==seat.getColumn()&&element.isIsbooked()==false) {
                    element.setIsbooked(true);
                    String token = String.valueOf(randomUUID());
                    concurrentHashMap.put(token,element);
                    income=income+((seat.getRow() < 5) ? 10 : 8);
                    String ticket = "{\n\t\"token\": \""+token+"\",\n\t\"ticket\": { \n\t\t\"row\": "+String.valueOf(seat.getRow())+",\n\t\t\"column\": "+
                            String.valueOf(seat.getColumn())+",\n\t\t\"price\": "+String.valueOf((seat.getRow() < 5) ? 10 : 8)+"\n\t} \n}";
                    return new ResponseEntity<>(ticket, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(new error("The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/return")
    public Object getReturn(@RequestBody token token){
        seatInfo seatReturn = (seatInfo) concurrentHashMap.get(token.getToken());
        if(seatReturn!=null){
            concurrentHashMap.remove(token.getToken());
            seatReturn.setIsbooked(false);
            income=income-((seatReturn.getRow() < 5) ? 10 : 8);
            String ticket = "{\n\t\"returned_ticket\": { \n\t\t\"row\": "+String.valueOf(seatReturn.getRow())+",\n\t\t\"column\": "+
                    String.valueOf(seatReturn.getColumn())+",\n\t\t\"price\": "+String.valueOf((seatReturn.getRow() < 5) ? 10 : 8)+"\n\t} \n}";
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new error("Wrong token!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/stats")
    public Object getstats(@RequestParam(required = false, defaultValue = "") String password){
        if(password.equals("super_secret")){
            String info = "{\n\t\"current_income\": "+income+",\n\t\"number_of_available_seats\": "+(81-concurrentHashMap.size())+
                    ",\n\t\"number_of_purchased_tickets\": "+concurrentHashMap.size()+"\n}";
            return new ResponseEntity<>(info, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new error("The password is wrong!"), HttpStatus.valueOf(401));
        }

    }
}