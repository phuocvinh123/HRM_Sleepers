package service;

import utils.DateUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

public class OvertimeService {
    private final String fileOvertime = "./data/overtime.txt";
    private Map<LocalDate, Double> overtimeByDay;

    public void saveOvertimeToFile(Map<LocalDate, Double> overtimeByDay, Map<LocalDate, String> overtimeIdByDay) {
        try {
            FileWriter writer = new FileWriter(fileOvertime, true);

            for (Map.Entry<LocalDate, Double> entry : overtimeByDay.entrySet()) {
                LocalDate date = entry.getKey();
                Double overtime = entry.getValue();
                String staffId = overtimeIdByDay.get(date);

                String line = staffId + "," + DateUtils.formatDate(date) + "," + overtime.toString();
                writer.write(line);
                writer.write(System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Lỗi khi lưu thông tin tăng ca vào tệp tin.");
            e.printStackTrace();
        }
    }

    public double calculateTotalSalary(String overtimeFile, String staffId) {
        double totalSalary = 0.0;

        try (BufferedReader reader = new BufferedReader(new FileReader(overtimeFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String id = parts[0];
                    LocalDate date = DateUtils.parseDate(parts[1]);
                    double overtime = Double.parseDouble(parts[2]);
                    if (id.equals(staffId)) {
                        // Tính toán lương từ số giờ tăng ca và mức lương tăng ca
                        double salary = overtime;
                        totalSalary += salary;
                    }
                }
            }
            System.out.println("Tổng tiền tăng ca là:" + totalSalary);
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc tệp tin overtime.");
            e.printStackTrace();
        }

        return totalSalary;
    }

}

