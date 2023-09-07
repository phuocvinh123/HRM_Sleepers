package view;

import model.AdminModel;
import service.IAminService;
import service.OvertimeService;
import service.TimeService;

import java.io.*;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static utils.FileUtils.readData;

public class TimeView {
    private Scanner scanner = new Scanner(System.in);
    private String currentStaffId;
    private static String checkedInStaffId = "";
    TimeService timeService = new TimeService();
    OvertimeView overtimeView = new OvertimeView();

    public void launcher() {
        boolean checkAction = false;
        while (true) {
            System.out.println("                                                           ╔════════════════════════════════════════════╗");
            System.out.println("                                                           ║       Nỗ lực hết mình trong công việc,     ║");
            System.out.println("                                                           ║  luôn tươi cười, thân thiên với khách hàng ║");
            System.out.println("                                                           ║      1.Check In (bắt đầu làm việc thôi nào)║");
            System.out.println("                                                           ║      0. Quay lại                           ║");
            System.out.println("                                                           ╚════════════════════════════════════════════╝");
            int actionMenu = Integer.parseInt(scanner.nextLine());
            switch (actionMenu) {
                case 0 -> {
                    menu();
                }
                case 1 -> {
                    checkIn();
                }
                default -> {
                    return;
                }
            }
        }
    }

    private void menu() {
        StaffView staffView = new StaffView();
        staffView.launcher();
    }

    public void checkOut1() {
        while (true) {
            System.out.println("                                                           ╔════════════════════════════════════════════╗");
            System.out.println("                                                           ║    Bạn đã nỗ lực hết mình trong công việc, ║");
            System.out.println("                                                           ║   check out để kết thúc 1 ngày làm việc nào║");
            System.out.println("                                                           ║                1.Check Out                 ║");
            System.out.println("                                                           ║                0. Quay lại                 ║");
            System.out.println("                                                           ╚════════════════════════════════════════════╝");
            int actionMenu = Integer.parseInt(scanner.nextLine());
            switch (actionMenu) {
                case 0 -> {
                    menu();
                }
                case 1 -> {
                    checkOut();
                }
                default -> {
                    return;
                }
            }
        }
    }


    public void checkIn() {
        System.out.println("Check In - Nhập thông tin:");
        String staffId = enterStaffId();
        String code = enterCode();
        TimeService timeService = new TimeService(); // Khởi tạo đối tượng TimeService
        checkedInStaffId = timeService.checkIntTime(staffId, code);
        checkOut1();
    }


    public void checkOut() {
        System.out.println("Check Out - Nhập thông tin:");
        if (checkedInStaffId.isEmpty()) {
            System.out.println("Không có nhân viên đã Check In.");
            return;
        }
        String checkOutTime = "";
        System.out.print("Giờ check-out (HH:mm:ss): ");
        checkOutTime = scanner.nextLine();
        timeService.checkOutTime(checkOutTime);
        System.out.print("Bạn có muốn tăng ca không? (yes/no): ");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("yes")) {
            overtimeView.overtime(checkedInStaffId);
        } else {
            StaffView staffView = new StaffView();
            staffView.launcher();
        }
    }

    //Tổng số giờ làm việc
    public void totalTime() {
        String staffId = enterStaffId(); // Yêu cầu người dùng nhập ID nhân viên
        timeService.showPrintTotalWorkTime("./data/timekeeping.txt", staffId);
    }


    //tính tổng tiền tăng ca
    public void toatalOverTime() {
        String staffId = enterStaffId();
        OvertimeService overtimeService = new OvertimeService();
        overtimeService.calculateTotalSalary("./data/overtime.txt", staffId);
    }

    public String enterStaffId() {
        System.out.print("Nhập ID nhân viên: ");
        return scanner.nextLine();
    }

    public String enterCode() {
        System.out.print("Nhập mã xác thực: ");
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        TimeView timeView = new TimeView();
        timeView.checkIn();
    }

}