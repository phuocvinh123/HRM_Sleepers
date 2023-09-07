package service;

import model.AdminModel;
import model.ERole;
import model.LoginModel;
import utils.FileUtils;
import view.AdminView;
import view.StaffView;

import java.util.List;
import java.util.Scanner;



public class LoginService implements ILoginService {
    private final String fileLogin = "./data/login.txt";
    private  final String fileStaff = "./data/staff.txt";
    Scanner scanner = new Scanner(System.in);

    @Override
    public List<LoginModel> getAllLogin() {
        return FileUtils.readData(fileLogin, LoginModel.class);
    }


    private String userName() {
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

    private String password() {
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

    public void login() {
        boolean check = false;
        do {
            System.out.println("Đăng nhập:");
            String username = userName();
            String password = password();

            try {
                if (validateLogin(username, password)) {
                    System.out.println("Đăng nhập thành công!");
                    AdminService a = new AdminService();
                    Long staffId = a.findStaffIdByUserName(fileStaff, username); // Thay đổi kiểu dữ liệu của staffId từ long sang Long

                    if (staffId != null) {
                        System.out.println("Mã ID nhân viên của bạn: " + staffId );
                        // Tiếp tục thực hiện các hành động sau khi lấy được ID nhân viên
                    } else {
                        System.err.println("Không tìm thấy ID nhân viên cho tên đăng nhập này");
                    }

                    // Kiểm tra vai trò người dùng sau khi xác thực thành công
                    if (getUserRole(username) == ERole.USER) {
                        // Chuyển hướng đến trang người dùng
                        System.out.println("Chúc bạn có một ngày làm việc hiệu quả");
                        StaffView staffView = new StaffView();
                        staffView.launcher();
                    } else if (getUserRole(username) == ERole.ADMIN) {
                        // Chuyển hướng đến trang quản trị viên
                        System.out.println("Chào mừng admin chúc bạn một ngày tốt lành");
                        AdminView staffView = new AdminView();
                        staffView.launcher();
                    }

                    check = true;
                } else {
                    System.err.println("Đăng nhập thất bại!");
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Lỗi: " + e.getMessage());
            }
        } while (!check);
    }

    private boolean validateLogin(String username, String password) {
        List<LoginModel> login = getAllLogin();

        for (LoginModel loginItem : login) {
            boolean isLoginValid = loginItem.getUserName().equals(username) && loginItem.getPassword().equals(password);
            if (isLoginValid) {
                if (loginItem.getRole() == ERole.USER) {
                    // Kiểm tra vai trò người dùng
                    // Nếu là người dùng, thực hiện các hành động tương ứng
                    return true;
                } else if (loginItem.getRole() == ERole.ADMIN) {
                    // Kiểm tra vai trò người dùng
                    // Nếu là admin, thực hiện các hành động tương ứng
                    return true;
                }
            }
        }

        return false;
    }

    private ERole getUserRole(String username) {
        List<LoginModel> loginList = getAllLogin();
        for (LoginModel login : loginList) {
            if (login.getUserName().equals(username)) {
                return login.getRole();
            }
        }
        return null;
    }

}
