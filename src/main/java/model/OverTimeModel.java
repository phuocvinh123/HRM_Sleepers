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
public class OverTimeModel implements IParseModel<OverTimeModel> {
    private long id;
    private int time;
    private double overtimeSalary;


    @Override
    public OverTimeModel parse(String line) {
        String[] items = line.split(AppConstant.SPLIT_ITEM_FILE);
        OverTimeModel time = new OverTimeModel(Long.parseLong(items[0]), Integer.parseInt(items[1]), Double.parseDouble(items[2]));
        return time;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s", this.id, this.time, this.overtimeSalary);
    }

}
