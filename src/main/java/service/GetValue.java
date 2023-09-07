package service;

import model.AdminModel;
import utils.DateUtils;

import java.time.LocalDate;
import java.util.Scanner;

public class GetValue extends AdminModel {
    static Scanner scanner = new Scanner(System.in);
    public static String getFullName(String name) {
        boolean check = false;
        do {
            System.out.println("Enter to full name:");
            name = scanner.nextLine();
            try {
                if (name.length() < 6 || name.length() > 20) {
                    throw new IllegalArgumentException(" Name must be between 6 and 20 characters long (Tên phải có độ dài từ 6 đến 20 ký tự).");
                }
                check = true;
            } catch (IllegalArgumentException e) {
                System.err.println("Lỗi: " + e.getMessage());
            }
        } while (!check);
        return name;
    }
    public static String getGender(String gender){
        boolean check = false;
        do{System.out.println("Enter 0->2 to male,female,other");
            gender=scanner.nextLine();
            try{
                if (!(gender.equals("0") || gender.equals("1") || gender.equals("2") )) {
                    throw new IllegalArgumentException("Invalid input number. Please re-enter.(0-2)");
                }
                check=true;

            }catch (IllegalArgumentException e){
                System.err.println("Lỗi: "+e.getMessage());
            }
        }while (!check);
        String str = "";
        if (gender != null) {
            switch (gender) {
                case "0" -> str = "Male";
                case "1" -> str = "FEMALE";
                case "2" -> str = "OTHER";
            }
        }
        return str;
    }
    public static String getPosition(String position){
        boolean check = false;
        do {
            System.out.println("Enter 0->4 to Staff:(Serve,Serving Food,receptionist,Regional Chief,Manager)");
            position = scanner.nextLine();
            try {
                if (!(position.equals("0") || position.equals("1") || position.equals("2") || position.equals("3") || position.equals("4"))) {
                    throw new IllegalArgumentException("Invalid input number. Please re-enter (0-4).");
                }
                check = true;
            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } while (!check);

        String strType = "";
        if (position != null) {
            switch (position) {
                case "0" -> strType = "StaffServe";
                case "1" -> strType = "ServingFood";
                case "2" -> strType = "receptionist";
                case "3" -> strType = "RegionalChief";
                case "4" -> strType = "Manager";
            }
        }
        return strType;
    }

    public static String getCccd(String cccd){
        boolean check = false;
        do{System.out.println("Enter to cccd:");
            cccd=scanner.nextLine();
            try{
                if(cccd.length()!=12){
                    throw new IllegalArgumentException("cccd must be 12 numbers");
                }
                check=true;

            }catch (IllegalArgumentException e){
                System.err.println("Lỗi: "+e.getMessage());
            }
        }while (!check);
        return cccd;
    }
    public static String getAddress(String address){
        boolean check=false;
        do {
            System.out.println("Enter to address:");
            address = scanner.nextLine();
            try {
                if (address.length() < 10 || address.length() > 50) {
                    throw new IllegalArgumentException(" address must be between 10 and 50 characters long (địa chỉ phải có độ dài từ 10 đến 50 ký tự).");
                }
                check = true;
            } catch (IllegalArgumentException e) {
                System.err.println("Lỗi: " + e.getMessage());
            }
        }while (!check);
        return address;
    }
    public static String getPhone(String phone){
        boolean check = false;
        do{ System.out.println("Enter to phone number:");
            phone=scanner.nextLine();
            try {
                if(!phone.matches("(09|03|05|07|08|02)\\d{8}")&&phone.length()!=10){
                    throw new IllegalArgumentException("phone number must start tape 02,03,05,07,08,09 and enough 10 numbers");
                }check=true;
            }catch (IllegalArgumentException e){
                System.err.println("Lỗi: "+e.getMessage());
            }
        }while (!check);
        return phone;
    }
    public static String getEmail(String email){
        boolean check = false;
        do{ System.out.println("Enter to email:");
            email=scanner.nextLine();
            try {
                if(!email.matches("^[A-Za-z0-9+-._]+@gmail.com+$")){
                    throw new IllegalArgumentException("Invalid email");
                }check=true;
            }catch (IllegalArgumentException e){
                System.err.println("Lỗi: "+e.getMessage());
            }
        }while (!check);
        return email;
    }
    public static int getAge(String age) {
        boolean check = false;
        int userAge = 0;

        do {
            System.out.println("Enter your age:");
            try {
                userAge =Integer.parseInt(scanner.nextLine());
                if (userAge < 18) {
                    throw new IllegalArgumentException("You are not old enough.");
                }
                check = true;
            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Invalid input. Please enter a valid age.");
                scanner.nextLine(); // Clear the input buffer
            }
        } while (!check);

        return userAge;
    }
    public static LocalDate getBirtDay(String birtDay){
        LocalDate dob = null;
       try{
           System.out.println("Nhập ngày sinh: (dd-MM-yyyy)");
           dob = DateUtils.parseDate(scanner.nextLine());
       } catch (Exception exception) {
           exception.printStackTrace();
       }
        return dob;
    }
    public static String getCode(String code){
        System.out.println("Enter to code:");
       code=scanner.nextLine();
        return code;
    }

}
