package JavaCoreAdvancedLevel.Task2;

public class DayOfWeekMain {

    public enum DayOfWeek {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
    }

    static int getWorkingHours(DayOfWeek e){
        switch (e){
            case MONDAY: return 40;
            case TUESDAY: return 32;
            case WEDNESDAY: return 24;
            case THURSDAY: return 16;
            case FRIDAY: return 8;
            default:  return 0;
        }
    }

    public static void main(final String[] args) {
        System.out.println(getWorkingHours(DayOfWeek.MONDAY));
    }
}
