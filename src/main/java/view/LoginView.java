package view;

import service.ILoginService;
import service.LoginService;

import java.util.Scanner;

public class LoginView {
    private ILoginService iLoginService;
    private Scanner scanner = new Scanner(System.in);

    public LoginView() {
        iLoginService = new LoginService();
    }

    public void menu() {
        while (true) {
            System.out.println("                                           ╔═══════════════════════════════════════════════════════════╗");
            System.out.println("                                           ║        Welcome to employee management software            ║");
            System.out.println("                                           ╠═══════════════════════════════════════════════════════════╣");
            System.out.println("                                           ║                                                           ║");
            System.out.println("                                           ║              1. LogIn(Đăng nhập)                          ║");
            System.out.println("                                           ║              2. Register(Đăng kí)                         ║");
            System.out.println("                                           ║              0. Exit(Thoát chương trình)                  ║");
            System.out.println("                                           ║                                                           ║");
            System.out.println("                                           ╚═══════════════════════════════════════════════════════════╝");
            int number = Integer.parseInt(scanner.nextLine());
            switch (number) {
                case 1 -> menuLogin();
                case 2 -> menuRegister();
                default -> {
                    return;
                }
            }
        }
    }

    public void menuLogin() {
        LoginService loginService = new LoginService();
        loginService.login();
    }

    public void menuRegister() {
        RegisterView registerService = new RegisterView();
        registerService.register();
    }


}
