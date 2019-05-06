package models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class History implements Serializable {
    private String OponnentUserName;
    private LocalDateTime localDateTime;
    private GameStatus yourStatus;

    public void setLocalDateTime() {
        localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
    }

    public void setYourStatus(GameStatus yourStatus) {
        this.yourStatus = yourStatus;
    }

    public GameStatus getYourStatus() {
        return yourStatus;
    }

    public String getDifference() {
        LocalDateTime fromDateTime = localDateTime;
        LocalDateTime toDateTime = LocalDateTime.now();
        System.out.println(toDateTime);

        LocalDateTime tempDateTime = LocalDateTime.from(fromDateTime);

        long years = tempDateTime.until(toDateTime, ChronoUnit.YEARS);
        tempDateTime = tempDateTime.plusYears(years);

        long months = tempDateTime.until(toDateTime, ChronoUnit.MONTHS);
        tempDateTime = tempDateTime.plusMonths(months);

        long days = tempDateTime.until(toDateTime, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays(days);


        long hours = tempDateTime.until(toDateTime, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours(hours);

        long minutes = tempDateTime.until(toDateTime, ChronoUnit.MINUTES);

        return months + " months " +
                days + " days " +
                hours + " hours " +
                minutes + " minutes ";
    }

    public String getOponnentUserName() {
        return OponnentUserName;
    }

    public void setOponnentUserName(String oponnentUserName) {
        OponnentUserName = oponnentUserName;
    }
}
