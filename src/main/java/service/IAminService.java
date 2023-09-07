package service;

import model.AdminModel;

import java.util.List;

public interface IAminService {
    List<AdminModel> getAllStaff();

    AdminModel findStaffById(long id);

    void updateStaff(long id, AdminModel staff);

    void deleteStaff(long id);

    void createStaff(AdminModel staff);

    List<AdminModel> searchStaff(Long id);

}
