package view;

import service.AdminService;
import service.IAminService;
import service.TimeService;
import service.TotalSalaryCalculator;

import java.util.Scanner;

public class StaffView {
    private IAminService iStaffService;
    private Scanner scanner = new Scanner(System.in);
    private TimeView timeView = new TimeView();

    public StaffView() {
        iStaffService = new AdminService();
    }

    public void launcher() {
        while (true) {
            System.out.println("                                                           ╔════════════════════════════════════════════╗");
            System.out.println("                                                           ║       Trang thông tin nhân viên            ║");
            System.out.println("                                                           ║      1. Xem thông tin cá nhân              ║");
            System.out.println("                                                           ║      2. Chấm công                          ║");
            System.out.println("                                                           ║      3. Hiển thị tổng số thời gian làm việc║");
            System.out.println("                                                           ║     trong 1 tháng                          ║");
            System.out.println("                                                           ║      4. Tính lương giờ làm                 ║");
            System.out.println("                                                           ║      5.Tính lương tăng ca                  ║");
            System.out.println("                                                           ║      6. Tổng lương phải trả                ║");
            System.out.println("                                                           ║      7. Mức Lương cơ bản                   ║");
            System.out.println("                                                           ║      0. Quay lại                           ║");
            System.out.println("                                                           ╚════════════════════════════════════════════╝");
            int actionMenu = Integer.parseInt(scanner.nextLine());
            switch (actionMenu) {
                case 0 -> {
                    menu();
                }
                case 1 -> {
                    showStaff();
                }
                case 2 -> {
                    Timekeeping();
                }
                case 3 -> {
                    hoursWorked();
                }
                case 4 -> {
                    Payroll();
                }
                case 5 -> {
                    PayrollOvertime();
                }
                case 6 -> {
                    totalSalaryPayable();
                }
                case 7 -> {
                    basicSalary();
                }
                default -> {
                    return;
                }
            }
        }
    }

    private void basicSalary() {
        WageView wageView = new WageView();
        int s = wageView.BasicWage();
        System.out.println(s);
    }

    private void totalSalaryPayable() {
        TotalSalaryCalculator t = new TotalSalaryCalculator();
        System.out.print("Nhập id muốn hiển thị lương: ");
        String id = scanner.nextLine();
        t.readTotalById(id);
    }

    private void PayrollOvertime() {
        timeView.toatalOverTime();
    }

    private void Payroll() {
        WageView wageView = new WageView();
        wageView.Wage();
    }

    private void hoursWorked() {
        timeView.totalTime();
    }

    private void Timekeeping() {
        timeView.launcher();
    }

    private void showStaff() {
        AdminView adminView = new AdminView();
        adminView.searchStaffs();
    }

    private void menu() {
        LoginView loginView = new LoginView();
        loginView.menu();
    }

    public static void main(String[] args) {
        StaffView staffView = new StaffView();
        staffView.launcher();
    }
}


