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
public class RegisterModel implements IParseModel<RegisterModel> {
    private String username;
    private String password;
    private ERole role;


    @Override
    public RegisterModel parse(String line) {
        String[] items = line.split(AppConstant.SPLIT_ITEM_FILE);
        RegisterModel r = new RegisterModel(items[0], items[1], ERole.valueOf(items[2]));
        return r;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s", this.username, this.password, this.role);
    }
}
