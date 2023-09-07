package service;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TotalSalaryCalculator {
    private final String fileWage = "./data/wage.txt";
    private final String fileOvertime = "./data/overtime.txt";
    private final String fileTotal = "./data/total.txt";

    //Đọc dữ liệu từ file overtime và tính tổng thời gian làm việc
    public double readOvertimeSalary(String filename, String staffId) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileOvertime));
        String line;
        double overtimeSalary = 0.0;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length >= 3 && data[0].trim().equals(staffId)) {
                overtimeSalary += Double.parseDouble(data[2]);
            }
        }
        reader.close();
        return overtimeSalary;
    }

    //Đọc dữ liệu từ file wage và tính tổng lương
    public double readBaseSalary(String filename, String staffId) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileWage));
        String line;
        double baseSalary = 0.0;

        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length >= 4 && data[0].trim().equals(staffId)) {
                baseSalary += Double.parseDouble(data[2]);
            }
        }

        reader.close();

        return baseSalary;
    }

    public void saveTotalSalary(String filename, String staffId, double overtimeSalary, double baseSalary, double totalSalary) throws IOException {
        String currentMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("MM-yyyy"));
        File file = new File(fileTotal);
        boolean isExistingId = false;
        StringBuilder fileContent = new StringBuilder();
        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 1 && data[0].trim().equals(staffId)) {
                    // Ghi đè lên thông tin lương nếu có cùng ID
                    line = staffId + "," + overtimeSalary + "," + baseSalary + "," + totalSalary + "," + currentMonth;
                    isExistingId = true;
                }
                fileContent.append(line).append(System.lineSeparator());
            }
            reader.close();
        }
        // Nếu không có ID trùng khớp, thêm thông tin lương mới vào cuối tệp
        if (!isExistingId) {
            String newLine = staffId + "," + overtimeSalary + "," + baseSalary + "," + totalSalary + "," + currentMonth + "\n";
            fileContent.append(newLine).append(System.lineSeparator());
        }
        // Ghi nội dung mới vào tệp
        FileWriter writer = new FileWriter(file);
        writer.write(fileContent.toString());
        writer.close();
    }


    //Tính lương tăng ca và lương làm việc lưu vào file total
    public void totalSalary() {
        try {
            // Tạo danh sách nhân viên từ fileOvertime và fileWage
            List<String> staffIds = getStaffIds();

            // Tính toán tổng lương cho từng nhân viên
            for (String staffId : staffIds) {
                // Tính toán lương tăng ca
                double overtimeSalary = readOvertimeSalary(fileOvertime, staffId);

                // Tính toán lương cơ bản
                double baseSalary = readBaseSalary(fileWage, staffId);

                // Tính toán tổng lương
                double totalSalary = overtimeSalary + baseSalary;

                // Lưu thông tin tổng lương vào tệp
                saveTotalSalary(fileTotal, staffId, overtimeSalary, baseSalary, totalSalary);
            }
        } catch (IOException e) {
            System.err.println("Đã xảy ra lỗi trong quá trình xử lý lương: " + e.getMessage());
        }
    }

    private List<String> getStaffIds() throws IOException {
        List<String> staffIds = new ArrayList<>();
        File Overtime = new File(fileOvertime);
        File Wage = new File(fileWage);

        if (Overtime.exists() && Wage.exists()) {
            // Đọc nhân viên từ fileOvertime
            BufferedReader overtimeReader = new BufferedReader(new FileReader(Overtime));
            String overtimeLine;
            while ((overtimeLine = overtimeReader.readLine()) != null) {
                String[] data = overtimeLine.split(",");
                if (data.length >= 1) {
                    String staffId = data[0].trim();
                    if (!staffIds.contains(staffId)) {
                        staffIds.add(staffId);
                    }
                }
            }
            overtimeReader.close();

            // Đọc nhân viên từ fileWage
            BufferedReader wageReader = new BufferedReader(new FileReader(Wage));
            String wageLine;
            while ((wageLine = wageReader.readLine()) != null) {
                String[] data = wageLine.split(",");
                if (data.length >= 1) {
                    String staffId = data[0].trim();
                    if (!staffIds.contains(staffId)) {
                        staffIds.add(staffId);
                    }
                }
            }
            wageReader.close();
        }

        return staffIds;
    }

    public List<String> readTotalSalary(String filename) throws IOException {
        List<String> totalSalaries = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileTotal));
        String line;

        while ((line = reader.readLine()) != null) {
            totalSalaries.add(line);
        }

        reader.close();

        return totalSalaries;
    }

    //Hiển thị lương của tất cả nhân viên trong file total
    public void readTotal() {
        try {
            List<String> totalSalaries = readTotalSalary(fileTotal);
            // Hiển thị thông tin lương từ danh sách
            for (String salary : totalSalaries) {
                System.out.println(salary);
            }
        } catch (IOException e) {
            System.err.println("Đã xảy ra lỗi trong quá trình đọc tệp: " + e.getMessage());
        }
    }

    //Hiển thị lương cuả nhân viên theo id
    public void readTotalById(String staffId) {
        try {
            List<String> totalSalaries = readTotalSalary(fileTotal);

            // Hiển thị thông tin lương từ danh sách dựa trên ID
            for (String salary : totalSalaries) {
                String[] data = salary.split(",");
                if (data.length >= 1 && data[0].trim().equals(staffId)) {
                    System.out.println(salary);
                }
            }
        } catch (IOException e) {
            System.err.println("Đã xảy ra lỗi trong quá trình đọc tệp: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        TotalSalaryCalculator t = new TotalSalaryCalculator();
        t.totalSalary();
    }
}
