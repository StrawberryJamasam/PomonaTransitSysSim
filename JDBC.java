// holds JDBC connection and implementataions

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class JDBC {

    private Connection conn = null;
    private String URL = "jdbc:postgresql://localhost:5432/BusTransitSystemSim";
    private String USER = "postgres";
    private String PASS = "enter your password here";
    private boolean success = false;

    // constructor connects to database
    JDBC(){
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(URL, USER, PASS);

            if(conn != null){
                System.out.println("Connected to Database");
                System.out.println();
                success = true;
            }
            else {
                System.out.println("Connection Failed");
                System.out.println();
            }

        } catch (Exception e) {
            System.out.print(e);
            System.out.println();
        }
    }

    // checks if connection was successful
    public boolean getSuccess(){
        return success;
    }

    public void updateDB(String qry){
        try(Statement stmt = conn.createStatement();){
            stmt.executeUpdate(qry);
            System.out.println("successfully updated db");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void viewSchedule(){
        String qry = "Select startlocationname, destinationname, date, scheduledstarttime," + 
                            " scheduledarrivaltime, drivername, busID " + 
                     "FROM tripoffering INNER JOIN trip ON tripoffering.tripnumber=trip.tripnumber";
        
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(qry);){
                while(rs.next()){
                    System.out.println("    Start Location: " + rs.getString("startlocationname"));
                    System.out.println("    Destination Location: " + rs.getString("destinationname"));
                    System.out.println("    Date: " + rs.getDate("date"));
                    System.out.println("    Scheduled Start Time: " + rs.getTime("scheduledstarttime"));
                    System.out.println("    Scheduled Arrival Time: "+ rs.getTime("scheduledarrivaltime"));
                    System.out.println("    Bus Driver: " + rs.getString("drivername"));
                    System.out.println("    Bus ID: " + rs.getInt("busid"));
                    System.out.println();
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTripOffering(int num, Date date, Time start){
        String qry = "Delete FROM tripoffering " + 
                     "Where tripnumber = " + num + " AND date = \'" + date + "\'"  + " AND scheduledstarttime = \'" + start + "\'";
        
        try(Statement stmt = conn.createStatement();){
            stmt.executeUpdate(qry);
            System.out.println("successfully deleted trip offering");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTripOffering(int num, Date date, Time start, Time end, String driver, int id){
        
        String qry = "INSERT INTO tripoffering(tripnumber, date, scheduledstarttime, scheduledarrivaltime" + 
                       ", drivername, busid) VALUES (?, ?, ?, ?, ?, ?)";
      
        try(PreparedStatement pstmt = conn.prepareStatement(qry)){
            pstmt.setInt(1, num); 
            pstmt.setDate(2, date);
            pstmt.setTime(3, start);
            pstmt.setTime(4, end);
            pstmt.setString(5, driver);
            pstmt.setInt(6, id);
            pstmt.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void changeDriver(int num, Date date, Time stime, String name){
        String qry = "Update tripoffering SET drivername = \'" + name + "\' Where " + 
                     "tripnumber = " + num + " AND date = \'" + date + "\'"  + " AND scheduledstarttime = \'" + stime + "\'";
        
        try (Statement stmt = conn.createStatement();){
            stmt.executeUpdate(qry);
            System.out.println("successfully changed driver");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeBus(int num, Date date, Time stime, int id){
        String qry = "Update tripoffering SET busid = " + id + " Where " + 
                     "tripnumber = " + num + " AND date = \'" + date + "\'"  + " AND scheduledstarttime = \'" + stime + "\'";
        
        try (Statement stmt = conn.createStatement();){
            stmt.executeUpdate(qry);
            System.out.println("successfully changed Bus");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewStops(int num){
        String qry = "Select tripstopinfo.tripnumber, stopnumber, sequencenumber, drivingtime " + 
                     "FROM tripstopinfo " + 
                     "Where tripnumber = " + num;
        
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(qry);){
                while(rs.next()){
                    System.out.println("    Trip Number: " + rs.getInt("tripnumber"));
                    System.out.println("    Stop Number: " + rs.getInt("stopnumber"));
                    System.out.println("    Sequence Number: " + rs.getInt("sequencenumber"));
                    System.out.println("    Travel Time: " + rs.getTime("drivingtime"));
                    System.out.println();
                }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    public void driverSchedule(String name, Date date){
        String qry = "Select tripnumber, date, scheduledstarttime," + 
                            " scheduledarrivaltime, drivername " + 
                     "FROM tripoffering " + 
                     "WHERE drivername = \'" + name + "\' AND date between Date_add(" + (Date)date + ", interval \'1 WEEK\') AND \'" + date + "\'";
        System.out.println(qry);
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(qry);){
                if(!rs.next()){
                    System.out.println("No results");
                }
                while(rs.next()){
                    System.out.println("    Trip Number: " + rs.getInt("tripnumber"));
                    System.out.println("    Date: " + rs.getDate("date"));
                    System.out.println("    Scheduled Start Time: " + rs.getTime("scheduledstarttime"));
                    System.out.println("    Scheduled Arrival Time: "+ rs.getTime("scheduledarrivaltime"));
                    System.out.println("    Bus Driver: " + rs.getString("drivername"));
                    System.out.println();
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addDriver(String name, int telnum){
        String qry = "INSERT INTO driver(drivername, drivertelephonenumber) VALUES (?, ?)";
      
        try(PreparedStatement pstmt = conn.prepareStatement(qry)){
            pstmt.setString(1, name); 
            pstmt.setString(2, String.valueOf(telnum));
            pstmt.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addBus(int id, String model, int year){
        String qry = "INSERT INTO bus(busid, model, year) VALUES (?, ?, ?)";
      
        try(PreparedStatement pstmt = conn.prepareStatement(qry)){
            pstmt.setInt(1, id); 
            pstmt.setString(2, model);
            pstmt.setInt(3, year);
            pstmt.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removeBus(int id){
        String qry = "Delete FROM bus " + 
                     "Where busid = " + id;
        
        try(Statement stmt = conn.createStatement();){
            stmt.executeUpdate(qry);
            System.out.println("successfully deleted bus");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void enterTripData(int num, Date date, Time start, Time end, int stopnum, Time actualEnd, Time actualStart, int numIn, int numOut){
        String qry = "INSERT INTO actualtripstopinfo(tripnumber, date, scheduledstarttime, scheduledarrivaltime" + 
                       ", stopnumber, actualarrivaltime, actualstarttime, numberofpassengerin, numberofpassengerout) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
      
        try(PreparedStatement pstmt = conn.prepareStatement(qry)){
            pstmt.setInt(1, num); 
            pstmt.setDate(2, date);
            pstmt.setTime(3, start);
            pstmt.setTime(4, end);
            pstmt.setInt(5, stopnum);
            pstmt.setTime(6, actualEnd);
            pstmt.setTime(7, actualStart);
            pstmt.setInt(8, numIn);
            pstmt.setInt(9, numOut);
            pstmt.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
