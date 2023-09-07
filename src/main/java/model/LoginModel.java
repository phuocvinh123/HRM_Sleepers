package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utils.AppConstant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginModel implements IParseModel<LoginModel> {
    private String userName;
    private String password;
    private ERole role;

    @Override
    public LoginModel parse(String line) {
        String[] items = line.split(AppConstant.SPLIT_ITEM_FILE);
        LoginModel l = new LoginModel(items[0], items[1], ERole.valueOf(items[2]));
        return l;
    }
    @Override
    public String toString() {
        return String.format("%s,%s,%s", this.userName, this.password, this.role);
    }
}
