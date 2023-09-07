package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utils.AppConstant;
import utils.DateUtils;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminModel implements IParseModel<AdminModel> {
    private long id;
    private String position;
    private String fullName;
    private int age;
    private String gender;
    private LocalDate birthday;
    private String cccd;
    private String phone;
    private String address;
    private String email;
    private String Code;


    @Override
    public AdminModel parse(String line) {
        //1,servingFood,Tấn Tây,19,male,2003-05-18,123324654678,0783245678,12 An dương vương,tan@gmail.com
        String[] items = line.split(AppConstant.SPLIT_ITEM_FILE);
        AdminModel s = new AdminModel(Long.parseLong(items[0]),
                items[1],
                items[2],
                Integer.parseInt(items[3]),
                items[4],
                DateUtils.parseDate(items[5]),
                items[6],
                items[7],
                items[8],
                items[9],
                items[10]);
        return s;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", this.id, this.position, this.fullName, this.age, this.gender, DateUtils.formatDate(this.birthday), this.cccd, this.phone, this.address, this.email, this.Code);
    }
}