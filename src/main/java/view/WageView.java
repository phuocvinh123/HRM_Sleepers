package view;


import model.WageRange;
import service.TimeService;
import service.WageService;
import utils.DateUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static utils.DateUtils.parseDate;


public class WageView {
    private TimeService timeService = new TimeService();
    private WageService wageService = new WageService();
    private Scanner scanner = new Scanner(System.in);
    private final String fileTime = "./data/timekeeping.txt";

    private Map<String, Integer> wageMap;


    public WageView() {
        wageMap = new HashMap<>();
        wageMap.put("StaffServe", 15000);
        wageMap.put("ServingFood", 16000);
        wageMap.put("Receptionist", 20000);
        wageMap.put("RegionalChief", 25000);
        wageMap.put("Manager", 35000);
    }

    //Dùng để lấy lương cơ bản theo từng vị trí
    public int getBasicWageFromFile() {
        String staffId = enterStaffId();
        String jobTitle = wageService.getJobTitleFromStaffFile(staffId);
        return getWageByJobTitle(jobTitle);
    }

    private int getWageByJobTitle(String jobTitle) {
        for (Map.Entry<String, Integer> entry : wageMap.entrySet()) {
            String key = entry.getKey();
            if (jobTitle.equalsIgnoreCase(key)) {
                return entry.getValue();
            }
        }
        return 0;
    }

    public String enterStaffId() {
        System.out.print("Nhập ID nhân viên: ");
        return scanner.nextLine();
    }

    //lương cơ bản từng chức vụ
    public int BasicWage() {
        int basiWage = getBasicWageFromFile();
        return basiWage;
    }

    //Tính tổng thời gian làm việc và lương
    public double Wage() {
        String staffId = enterStaffId();
        String jobTitle = wageService.getJobTitleFromStaffFile(staffId);
        int basicWage = getWageByJobTitle(jobTitle);
        TimeService timeService = new TimeService();
        double wage = calculateSalaryById(fileTime, staffId, basicWage);

        // Lưu thông tin lương vào file wage.txt
        LocalDate date = LocalDate.now();
        wageService.saveWageToFile(staffId, timeService.showPrintTotalWorkTime(fileTime, staffId).toHours(), wage, DateUtils.formatDate(date));

        return wage;
    }

    public int calculateSalaryById(String fileTime, String staffId, double wageMultiplier) {
        Duration totalWorkTime = timeService.calculateTotalWorkTime(fileTime, staffId);
        int salary = (int) (totalWorkTime.toHours() * wageMultiplier);

        System.out.println("Lương của nhân viên có ID " + staffId + ": " + salary);

        return salary;
    }

    //Tính tổng thời gian làm việc của tất cả nhân viên
    public double calculateTotalWage() {
        List<String> staffIds = wageService.getAllStaffIds();

        double totalWage = 0.0;
        for (String staffId : staffIds) {
            String jobTitle = wageService.getJobTitleFromStaffFile(staffId);
            int basicWage = getWageByJobTitle(jobTitle);
            TimeService timeService = new TimeService();
            double wage = calculateSalary(fileTime, staffId, basicWage);
            totalWage += wage;

            // Lưu thông tin lương vào file wage.txt
            LocalDate date = LocalDate.now();
            wageService.saveWageToFile(staffId, timeService.printTotalWorkTime(fileTime, staffId).toHours(), wage, DateUtils.formatDate(date));
        }

        return totalWage;
    }

    public int calculateSalary(String fileTime, String staffId, double wageMultiplier) {
        Duration totalWorkTime = timeService.calculateTotalWorkTime(fileTime, staffId);
        int salary = (int) (totalWorkTime.toHours() * wageMultiplier);
        return salary;
    }


    public static void main(String[] args) {
        WageView wageView = new WageView();
        wageView.calculateTotalWage();
    }

}
