public class SustechTime {
    private int hour;
    private int minute;
    private String timeInfo;
    private String hourStr;
    private String minuteStr;
    private int times;

    public SustechTime(String timeInfo) {
        String[] strings = timeInfo.split(":");
        this.hour = Integer.parseInt(strings[0]);
        this.minute = Integer.parseInt(strings[1]);
        this.hourStr = hour < 10 ? ("0" + hour) : strings[0];
        this.minuteStr = minute < 10 ? ("0" + minute) : strings[1];
        this.timeInfo = hourStr + ":" + minuteStr;
        this.times = 60 * hour + minute;
    }

    public boolean isTimeValid(SustechTime targetTime) {
        return isEarlier(targetTime) && timeDifference(targetTime) > 30;
    }

    public boolean isEarlier(SustechTime targetTime) {
        return this.times < targetTime.times;
    }

    public int timeDifference(SustechTime targetTime) {
        return Math.abs(this.times - targetTime.times);
    }

    @Override
    public String toString() {
        return this.timeInfo;
    }
}
