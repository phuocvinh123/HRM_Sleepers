package view;

import service.OvertimeService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OvertimeView {

    private Scanner scanner = new Scanner(System.in);
    private Map<LocalDate, Double> overtimeByDay;
    private Map<LocalDate, String> overtimeIdByDay;
    private OvertimeService overtimeService = new OvertimeService();

    public OvertimeView() {
        overtimeByDay = new HashMap<>();
        overtimeIdByDay = new HashMap<>();
    }

    //Đăng kí tăng ca
    public void overtime(String staffId) {
        double overtimeWage;
        LocalDate date = LocalDate.now(); // Lấy ngày hiện tại

        DayOfWeek dayOfWeek = date.getDayOfWeek();

        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            overtimeWage = 70000.0; // Mức lương tăng ca cuối tuần
        } else {
            overtimeWage = 50000.0; // Mức lương tăng ca ngày thường
        }
        // Tiếp tục xử lý đăng ký tăng ca với mức lương overtimeWage và ID của nhân viên
        checkOvertime(overtimeWage, staffId);
    }


    public void checkOvertime(double overtimeWage, String staffId) {
        System.out.println("Check Overtime - Nhập thông tin:");

        LocalDate currentDate = LocalDate.now();
        double currentOvertime = 0.0;
        if (overtimeByDay.containsKey(currentDate)) {
            currentOvertime = overtimeByDay.get(currentDate);
        }
        System.out.print("Số giờ tăng ca: ");
        double overtimeHours = scanner.nextDouble();

        double overtimePay = overtimeHours * overtimeWage;
        currentOvertime += overtimePay;
        overtimeByDay.put(currentDate, currentOvertime);
        overtimeIdByDay.put(currentDate, staffId);
        System.out.println("Tổng tăng ca ngày " + currentDate + ": " + currentOvertime);
        overtimeService.saveOvertimeToFile(overtimeByDay, overtimeIdByDay);
    }


}
