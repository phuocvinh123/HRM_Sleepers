package view;

import model.ERole;
import model.RegisterModel;
import service.IRegisterService;
import utils.DateUtils;
import utils.FileUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class RegisterView implements IRegisterService {
    private final String fileLogin = "./data/login.txt";
    private Scanner scanner = new Scanner(System.in);

    @Override
    public List<RegisterModel> getAllRegister() {
        return FileUtils.readData(fileLogin, RegisterModel.class);
    }

    public void register() {
        System.out.println("Đăng ký mới người dùng:");
        String username = enterUsername();
        String password = enterPassword();
        ERole role = ERole.valueOf(enterErole());

        // Tạo người dùng mới
        RegisterModel registerModel = new RegisterModel(username, password, role);
        boolean exists = usernameExists(username);
        if (exists) {
            System.err.println("Tên người dùng đã tồn tại!");
        } else {
            // Lưu thông tin người dùng vào cơ sở dữ liệu hoặc tệp tin
            saveUser(registerModel);
            System.out.println("Đăng ký thành công!");
        }
    }
    private String enterErole() {
        boolean check = false;
        String role = "USER";
        String adminId = "151002";

        do {
            try {
                System.out.print("Enter role (ADMIN/USER): ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("ADMIN")) {
                    System.out.print("Enter ADMIN ID: ");
                    adminId = scanner.nextLine();
                    if (adminId.equals("151002")) {
                        role = "ADMIN";
                        check = true;
                    } else {
                        System.err.println("Incorrect ADMIN ID. Please try again or enter as USER.");
                    }
                } else {
                    role = "USER";
                    check = true;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } while (!check);

        return role;
    }

    private String enterUsername() {
        boolean check = false;
        String username;
        do {
            System.out.println("Enter the username:");
            username = scanner.nextLine();
            try {
                if (username.length() < 6 || username.length() > 20) {
                    throw new IllegalArgumentException("The username must be between 6 and 20 characters long.");
                }
                check = true;
            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } while (!check);
        return username;
    }

    private String enterPassword() {
        boolean check = false;
        String password;
        do {
            System.out.println("Enter the password:");
            password = scanner.nextLine();
            try {
                if (password.length() < 8 || !password.matches(".*[a-zA-Z]+.*") || !password.matches(".*\\d+.*")) {
                    throw new IllegalArgumentException("The password must be at least 8 characters long and contain at least one letter and one number.");
                }
                check = true;
            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } while (!check);
        return password;
    }

    private boolean usernameExists(String username) {
        List<RegisterModel> registerList = getAllRegister();
        for (RegisterModel register : registerList) {
            if (register.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    //Lưu thông tin vào file
    private void saveUser(RegisterModel registerModel) {
        FileUtils.appendData(fileLogin, registerModel);
    }
}
