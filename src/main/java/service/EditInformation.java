package service;

import model.AdminModel;
import utils.DateUtils;

import java.time.LocalDate;
import java.util.Scanner;

public class EditInformation {


    private static Scanner scanner = new Scanner(System.in);

    public static String updatePosition(AdminModel staff) {
        System.out.print("Nhập chức vụ muốn thay đổi: ");
        String pos = scanner.nextLine();
        if (!pos.isEmpty()) {
            staff.setPosition(pos);
        }
        return pos;
    }

    public static String updateFullName(AdminModel staff) {
        System.out.print("Nhập tên muốn thay đổi: ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            staff.setFullName(name);
        }
        return name;
    }

    public static String updateAge(AdminModel staff) {
        System.out.print("Nhập tuổi muốn thay đổi: ");
        int age = Integer.parseInt(scanner.nextLine());
        if (age != 0) {
            staff.setAge(age);
        }
        return null;
    }

    public static String updateGender(AdminModel staff) {
        System.out.print("Nhập giới tính muốn thay đổi: ");
        String gender = scanner.nextLine();
        if (!gender.isEmpty()) {
            staff.setGender(gender);
        }
        return gender;
    }

    public static String updateBirthday(AdminModel staff) {
        System.out.print("Nhập ngày sinh muốn thay đổi: ");
        LocalDate bir = DateUtils.parseDate(scanner.nextLine());
        if (bir != null) {
            staff.setBirthday(bir);
        }
        return String.valueOf(bir);
    }

    public static String updateCCCD(AdminModel staff) {
        System.out.print("Nhập cccd muốn thay đổi: ");
        String cccd = scanner.nextLine();
        if (!cccd.isEmpty()) {
            staff.setCccd(cccd);
        }
        return cccd;
    }

    public static String updatePhone(AdminModel staff) {
        System.out.print("Nhập số điện thoại muốn thay đổi: ");
        String phone = scanner.nextLine();
        if (!phone.isEmpty()) {
            staff.setPhone(phone);
        }
        return phone;
    }

    public static String updateAddress(AdminModel staff) {
        System.out.print("Nhập địa chỉ muốn thay đổi: ");
        String address = scanner.nextLine();
        if (!address.isEmpty()) {
            staff.setAddress(address);
        }
        return address;
    }

    public static String updateEmail(AdminModel staff) {
        System.out.print("Nhập email muốn thay đổi: ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            staff.setEmail(email);
        }
        return email;
    }

    public static String updateCode(AdminModel staff) {
        System.out.print("Nhập mã xác thực muốn thay đổi: ");
        String code = scanner.nextLine();
        if (!code.isEmpty()) {
            staff.setEmail(code);
        }
        return code;
    }
}
