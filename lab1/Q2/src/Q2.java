import static java.lang.System.out;

import java.util.Arrays;
public class Q2 {
    public static void main(String[] args) {
        WorkSchedule ws = new WorkSchedule(5);

        WorkSchedule.Hour h = ws.readSchedule(0);
        out.println(h.requiredNumber);
        out.println(Arrays.toString( h.workingEmployees));
        ws.setRequiredNumber(0, 0, 0);

        out.println("-------------------------");
        out.println(h.requiredNumber);
        out.println(Arrays.toString( h.workingEmployees));




        // ws.nextIncomplete(0);

    }
}
