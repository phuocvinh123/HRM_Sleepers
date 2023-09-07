package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utils.AppConstant;

import java.util.List;
import java.util.Scanner;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeModel implements IParseModel<TimeModel> {
    private long id;
    private String date;
    private String startTime;
    private String endTime;


    @Override
    public TimeModel parse(String line) {
        String[] items = line.split(AppConstant.SPLIT_ITEM_FILE);
        TimeModel time = new TimeModel(Long.parseLong(items[0]), items[1], items[2], items[3]);
        return time;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s", this.id, this.date, this.startTime, this.endTime);
    }


}



