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
public class WageRange implements IParseModel<WageRange> {
    private long idUser;
    private String time;
    private double wage;
    private LocalDate month;

    @Override
    public WageRange parse(String line) {
        String[] items = line.split(AppConstant.SPLIT_ITEM_FILE);
        WageRange wageRange = new WageRange(Long.parseLong(items[0]), items[1], Double.parseDouble(items[2]), DateUtils.parseDate(items[3]));
        return wageRange;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s", this.idUser, this.time, this.wage, DateUtils.formatDate(this.month));
    }

}
