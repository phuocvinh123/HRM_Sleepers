package view;

import model.AdminModel;
import service.*;
import utils.DateUtils;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class AdminView {
    private IAminService iStaffService;
    private WageView wageView = new WageView();
    private Scanner scanner = new Scanner(System.in);

    public AdminView() {
        iStaffService = new AdminService();
    }

    public void launcher() {
        while (true) {
            System.out.println("                                                           ╔════════════════════════════════════════════╗");
            System.out.println("                                                           ║       Trang quản lí nhân viên              ║");
            System.out.println("                                                           ║      1. Thêm nhân viên                     ║");
            System.out.println("                                                           ║      2. Xóa nhân viên                      ║");
            System.out.println("                                                           ║      3. Thay đổi thông tin nhân viên       ║");
            System.out.println("                                                           ║      4. Hiển thị danh sách nhân viên       ║");
            System.out.println("                                                           ║      5. Tìm kiếm nhân viên theo id         ║");
            System.out.println("                                                           ║      6. Xắp xếp nhân viên                  ║");
            System.out.println("                                                           ║      7. Hiển thị lương của tất cả nhân viên║");
            System.out.println("                                                           ║      0. Quay lại                           ║");
            System.out.println("                                                           ╚════════════════════════════════════════════╝");
            int actionMenu = Integer.parseInt(scanner.nextLine());
            switch (actionMenu) {
                case 0 -> {
                    menu();
                }
                case 1 -> {
                    addStaff();
                }
                case 2 -> {
                    deleteStaff();
                }
                case 3 -> {
                    editStaff();
                }
                case 4 -> {
                    showStaff();
                }
                case 5 -> {
                    searchStaffs();
                }
                case 6 -> {
                    sortStaff();
                }
                case 7 -> {
                    showWage();
                }
                default -> {
                    return;
                }
            }
        }
    }

    private void showWage() {
        TotalSalaryCalculator t = new TotalSalaryCalculator();
        wageView.calculateTotalWage();
        t.totalSalary();
        t.readTotal();
    }

    public void searchStaffs() {
        System.out.print("Nhập id muốn tìm kiếm: ");
        long id =Long.parseLong(scanner.nextLine()) ;
        List<AdminModel> staff = iStaffService.searchStaff(id);
        showStaffByName(staff);
    }

    private void menu() {
        LoginView loginView = new LoginView();
        loginView.menu();
    }

    private void sortStaff() {
        System.out.println("                                                           ╔════════════════════════════════════════════╗");
        System.out.println("                                                           ║      Bạn muốn sắp xếp theo kiểu nào        ║");
        System.out.println("                                                           ║      Nhập 0. Back                          ║");
        System.out.println("                                                           ║      Nhập 1. Tăng dần                      ║");
        System.out.println("                                                           ║      Nhập 2. Giảm dần                      ║");
        System.out.println("                                                           ╚════════════════════════════════════════════╝");
        int action = Integer.parseInt(scanner.nextLine());
        List<AdminModel> staffs = iStaffService.getAllStaff();
        Comparator<AdminModel> comparator = null;
        switch (action) {
            case 0:
                launcher();
                break;
            case 1:
                staffs.sort(sortUserDecreasing(comparator));
                break;
            case 2:
                staffs.sort(sortUserAscending(comparator));
                break;
        }
        showStaffByName(staffs);
    }

    private Comparator<AdminModel> sortUserDecreasing(Comparator<AdminModel> comparator) {
        System.out.println("                                                           ╔════════════════════════════════════════════╗");
        System.out.println("                                                           ║            Bạn muốn sắp xếp theo:          ║");
        System.out.println("                                                           ║      0.Back to Menu                        ║");
        System.out.println("                                                           ║      1.ID                                  ║");
        System.out.println("                                                           ║      2.Name                                ║");
        System.out.println("                                                           ║      3.Position                            ║");
        System.out.println("                                                           ║      4.Gender                              ║");
        System.out.println("                                                           ║      5.Age                                 ║");
        System.out.println("                                                           ║      6.DOB                                 ║");
        System.out.println("                                                           ╚════════════════════════════════════════════╝");
        int action = Integer.parseInt(scanner.nextLine());
        switch (action) {
            case 0:
                sortStaff();
                break;
            case 1:
                comparator = Comparator.comparing(AdminModel::getId);
                break;
            case 2:
                comparator = Comparator.comparing(AdminModel::getFullName);
                break;
            case 3:
                comparator = Comparator.comparing(AdminModel::getPosition);
                break;
            case 4:
                comparator = Comparator.comparing(AdminModel::getGender);
                break;
            case 5:
                comparator = Comparator.comparing(AdminModel::getAge);
                break;
            case 6:
                comparator = Comparator.comparing(AdminModel::getBirthday);
                break;
        }
        return comparator;
    }

    private Comparator<AdminModel> sortUserAscending(Comparator<AdminModel> comparator) {
        System.out.println("                                                           ╔════════════════════════════════════════════╗");
        System.out.println("                                                           ║            Bạn muốn sắp xếp theo:          ║");
        System.out.println("                                                           ║      0.Back to Menu                        ║");
        System.out.println("                                                           ║      1.ID                                  ║");
        System.out.println("                                                           ║      2.Name                                ║");
        System.out.println("                                                           ║      3.Position                            ║");
        System.out.println("                                                           ║      4.Gender                              ║");
        System.out.println("                                                           ║      5.Age                                 ║");
        System.out.println("                                                           ║      6.DOB                                 ║");
        System.out.println("                                                           ╚════════════════════════════════════════════╝");
        int action = Integer.parseInt(scanner.nextLine());
        switch (action) {
            case 0:
                sortStaff();
                break;
            case 1:
                comparator = Comparator.comparing(AdminModel::getId).reversed();
                break;
            case 2:
                comparator = Comparator.comparing(AdminModel::getFullName).reversed();
                break;
            case 3:
                comparator = Comparator.comparing(AdminModel::getPosition).reversed();
                break;
            case 4:
                comparator = Comparator.comparing(AdminModel::getGender).reversed();
                break;
            case 5:
                comparator = Comparator.comparing(AdminModel::getAge).reversed();
                break;
            case 6:
                comparator = Comparator.comparing(AdminModel::getBirthday).reversed();
                break;

        }
        return comparator;
    }

    private void deleteStaff() {
        System.out.print("Nhập ID cần xóa: ");
        long id = Long.parseLong(scanner.nextLine());
        iStaffService.deleteStaff(id);
        showStaff();
    }

    private void editStaff() {
        showStaff();
        System.out.print("Nhập ID cần sửa: ");
        long id = Long.parseLong(scanner.nextLine());
        iStaffService.updateStaff(id, new AdminModel());
        showStaff();
    }

    private void addStaff() {
        String pos = GetValue.getPosition("");
        String name = GetValue.getFullName("");
        int age = GetValue.getAge("");
        String gender = GetValue.getGender("");
        LocalDate bir = GetValue.getBirtDay("");
        String cccd = GetValue.getCccd("");
        String phone = GetValue.getPhone("");
        String address = GetValue.getAddress("");
        String email = GetValue.getEmail("");
        String code = GetValue.getCode("");
        AdminModel staff = new AdminModel(System.currentTimeMillis() % 100000, pos, name, age, gender, bir, cccd, phone, address, email, code);
        iStaffService.createStaff(staff);
        showStaff();
    }

    public void showStaff() {
        System.out.println("                                                                         Thông tin nhân viên   ");
        System.out.println("=======================================================================================================================================================================================================================");
        System.out.printf(" %-15s| %15s |%25s |%15s |%15s |%15s |%15s |%15s |%30s |%30s \n", "ID", "POSITION", "NAME", "AGE", "GENDER", "BIRTHDAY", "CCCD", "PHONE", "ADDRESS", "EMAIL");
        List<AdminModel> staff = iStaffService.getAllStaff();
        for (AdminModel s : staff) {
            System.out.printf(" %-15s| %15s |%25s |%15s |%15s |%15s |%15s |%15s |%30s |%30s \n", s.getId(), s.getPosition(), s.getFullName(), s.getAge(), s.getGender(), DateUtils.formatDate(s.getBirthday()), s.getCccd(), s.getPhone(), s.getAddress(), s.getEmail());
        }
        System.out.println("========================================================================================================================================================================================================================\n\n");
    }


   public void showStaffByName(List<AdminModel> staff) {
        System.out.println("                                                                         Thông tin nhân viên   ");
        System.out.println("============================================================================================================================================================================================================================");
        System.out.printf(" %-15s| %15s |%25s |%15s |%15s |%15s |%15s |%15s |%30s |%30s  \n", "ID", "POSITION", "NAME", "AGE", "GENDER", "BIRTHDAY", "CCCD", "PHONE", "ADDRESS", "EMAIL");
        for (AdminModel s : staff) {
            System.out.printf(" %-15s| %15s |%25s |%15s |%15s |%15s |%15s |%15s |%30s |%30s  \n", s.getId(), s.getPosition(), s.getFullName(), s.getAge(), s.getGender(), DateUtils.formatDate(s.getBirthday()), s.getCccd(), s.getPhone(), s.getAddress(), s.getEmail());
        }
        System.out.println("=============================================================================================================================================================================================================================\n\n");
    }


    public static void main(String[] args) {
        AdminView a = new AdminView();
        a.launcher();
    }

}
