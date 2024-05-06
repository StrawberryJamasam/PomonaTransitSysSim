// Pomona transit System

import java.util.Scanner;

public class PomonaTransitSys {
    public static void main(String[] args){
     
        JDBC jdbc = new JDBC();

        if(jdbc.getSuccess()){
            Scanner input = new Scanner(System.in);

            String selection = "-1";

            while(!selection.equals("0")){
                
                System.out.println("Welcome to the Pomona Transit System Simulator!");
                System.out.println();
                System.out.println("Please select a menu option");
                System.out.println("15: Update Database");
                System.out.println("1:  View Trip Schedule");
                System.out.println("2:  Delete Trip Offering");
                System.out.println("3:  Add Trip Offering");
                System.out.println("4:  Change the Driver for a Trip");
                System.out.println("5:  Change the Bus for a Trip");
                System.out.println("6:  View Stops for a Trip");    
                System.out.println("7:  View Driver Schedule");        /////////////////// Issue with Date_Add
                System.out.println("8:  Add a Driver"); 
                System.out.println("9:  Add a Bus");
                System.out.println("10: Remove a Bus");
                System.out.println("11: Enter data for a Trip");
                System.out.println("0:  Exit");
                
                selection = input.nextLine();

                if(selection.equals("15")){
                    System.out.println("enter sql");
                    String sql = input.nextLine();
    
                    jdbc.updateDB(sql);
                }
                else if(selection.equals("1")){
                    jdbc.viewSchedule();
                }
                else if(selection.equals("2")){
                    System.out.println("Enter the Trip number");
                    int num = Integer. valueOf(input.nextLine());
                    System.out.println("Enter the Trip Date yyyy-MM-dd");
                    String date = input.nextLine();
                    java.sql.Date datesql = java.sql.Date.valueOf(date);
                    System.out.println("Enter the Starting time hh:mm:ss");
                    String stime = input.nextLine();
                    java.sql.Time stimesql = java.sql.Time.valueOf(stime);

                    jdbc.deleteTripOffering(num, datesql, stimesql);
                }
                else if(selection.equals("3")){
                    selection = "y";
                    while(selection.equals("y")){
                        System.out.println("Enter the Trip number");
                        int num = Integer.valueOf(input.nextLine());
                        System.out.println("Enter the Trip Date yyyy-MM-dd");
                        String date = input.nextLine();
                        java.sql.Date datesql = java.sql.Date.valueOf(date);
                        System.out.println("Enter the Starting time hh:mm:ss");
                        String stime = input.nextLine();
                        java.sql.Time stimesql = java.sql.Time.valueOf(stime);
                        System.out.println("Enter the Ending time hh:mm:ss");
                        String etime = input.nextLine();
                        java.sql.Time etimesql = java.sql.Time.valueOf(etime);
                        System.out.println("Enter the name of the Bus Driver");
                        String name = input.nextLine();
                        System.out.println("Enter the ID of the bus");
                        int id = Integer. valueOf(input.nextLine());

                        jdbc.addTripOffering(num, datesql, stimesql, etimesql, name, id);

                        System.out.println("Would you like to add another trip offering? \'y\'\\\'n\'");
                        selection = input.nextLine();
                    }
                }
                else if(selection.equals("4")){
                    System.out.println("Enter the Trip number");
                    int num = Integer. valueOf(input.nextLine());
                    System.out.println("Enter the Trip Date yyyy-MM-dd");
                    String date = input.nextLine();
                    java.sql.Date datesql = java.sql.Date.valueOf(date);
                    System.out.println("Enter the Starting time hh:mm:ss");
                    String stime = input.nextLine();
                    java.sql.Time stimesql = java.sql.Time.valueOf(stime);
                    System.out.println("Enter name of desired driver");
                    String name = input.nextLine();

                    jdbc.changeDriver(num, datesql, stimesql, name);
                }
                else if(selection.equals("5")){
                    System.out.println("Enter the Trip number");
                    int num = Integer. valueOf(input.nextLine());
                    System.out.println("Enter the Trip Date yyyy-MM-dd");
                    String date = input.nextLine();
                    java.sql.Date datesql = java.sql.Date.valueOf(date);
                    System.out.println("Enter the Starting time hh:mm:ss");
                    String stime = input.nextLine();
                    java.sql.Time stimesql = java.sql.Time.valueOf(stime);
                    System.out.println("Enter ID of desired Bus");
                    int id = Integer.valueOf(input.nextLine());

                    jdbc.changeBus(num, datesql, stimesql, id);
                }
                else if(selection.equals("6")){
                    System.out.println("Enter the Trip you want to view the stops of");
                    int num = Integer.valueOf(input.nextLine());

                    jdbc.viewStops(num);
                }
                else if(selection.equals("7")){
                    System.out.println("Enter the name of the Driver");
                    String name = input.nextLine();
                    System.out.println("Enter the starting date for the desired week");
                    String date = input.nextLine();
                    java.sql.Date datesql = java.sql.Date.valueOf(date);

                    jdbc.driverSchedule(name, datesql);
                }
                else if(selection.equals("8")){
                    System.out.println("Enter the name of the Driver");
                    String name = input.nextLine();
                    System.out.println("Enter the 10 digit phone number of the driver");
                    String num = input.nextLine();
                    int telenum = Integer.valueOf(num);

                    jdbc.addDriver(name, telenum);
                }
                else if(selection.equals("9")){
                    System.out.println("Enter ID of new Bus");
                    int id = Integer.valueOf(input.nextLine());
                    System.out.println("Enter the model");
                    String model = input.nextLine();
                    System.out.println("Enter the year of manufacture yyyy");
                    int year = Integer.valueOf(input.nextLine());

                    jdbc.addBus(id, model, year);
                }
                else if(selection.equals("10")){
                    System.out.println("Enter the ID of the bus to remove");
                    int id = Integer.valueOf(input.nextLine());

                    jdbc.removeBus(id);
                }
                else if(selection.equals("11")){
                    System.out.println("Enter the Trip number");
                    int num = Integer. valueOf(input.nextLine());
                    System.out.println("Enter the Trip Date yyyy-MM-dd");
                    String date = input.nextLine();
                    java.sql.Date datesql = java.sql.Date.valueOf(date);
                    System.out.println("Enter the Starting time hh:mm:ss");
                    String stime = input.nextLine();
                    java.sql.Time stimesql = java.sql.Time.valueOf(stime);
                    System.out.println("Enter the Ending time hh:mm:ss");
                    String etime = input.nextLine();
                    java.sql.Time etimesql = java.sql.Time.valueOf(etime);
                    System.out.println("Enter the number of the final stop");
                    int stop = Integer.valueOf(input.nextLine());
                    System.out.println("Enter the actual arrival time hh:mm:ss");
                    String stimeA = input.nextLine();
                    java.sql.Time stimeAsql = java.sql.Time.valueOf(stimeA);
                    System.out.println("Enter the actual departure time hh:mm:ss");
                    String stimeE = input.nextLine();
                    java.sql.Time stimeEsql = java.sql.Time.valueOf(stimeE);
                    System.out.println("Enter the number of passengers who got on the bus");
                    int passOn = Integer.valueOf(input.nextLine());
                    System.out.println("Enter the number of passengers who got off the bus");
                    int passOff = Integer.valueOf(input.nextLine());

                    jdbc.enterTripData(num, datesql, stimesql, etimesql, stop, stimeAsql, stimeEsql, passOn, passOff);
                }
                else{
                    System.out.println("Invalid Input");
                    System.out.println();
                }
            }
            
            input.close();
        }

    }
}