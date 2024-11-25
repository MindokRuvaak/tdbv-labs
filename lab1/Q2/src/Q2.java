import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.Formatter;

public class Q2 {

    private static final Logger lgr = Logger.getLogger(Q2.class.getName());
    private static final Level I = Level.INFO;
    private static final Level T = Level.CONFIG;

    public static void main(String[] args) {
        try {
            FileHandler fH = new FileHandler("out.log", false);
            fH.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    return record.getLevel() + ": " + record.getMessage() + "\n";
                }
            });
            lgr.addHandler(fH);
            lgr.setLevel(Level.ALL);
        } catch (IOException e) {
            e.printStackTrace();
        }

        lgr.log(T, "a)\n" + //
                "\nBlock #1:");

        WorkSchedule ws1 = new WorkSchedule(5);
        ws1.setRequiredNumber(0, 0, 0);
        lgr.log(I, "" + ws1.readSchedule(0).requiredNumber);

        lgr.log(T, "-------\n" + //
                "\nBlock #2:");

        WorkSchedule ws2 = new WorkSchedule(5);
        ws2.setRequiredNumber(0, 0, 1);
        lgr.log(I, "" + ws2.readSchedule(0).requiredNumber);
        lgr.log(I, "" + ws2.readSchedule(1).requiredNumber);

        lgr.log(T, "-------\n" + //
                "\nBlock #3:");

        WorkSchedule ws3 = new WorkSchedule(5);
        try {
            ws3.setRequiredNumber(0, 0, -1);
            lgr.log(I, "no exception");
            lgr.log(I, "" + ws3.readSchedule(0).requiredNumber);
        } catch (Exception e) {
            lgr.log(I, "Exception caugth");
        }

        lgr.log(T, "-------\n" + //
                "\nBlock #4:");
        WorkSchedule ws4 = new WorkSchedule(5);
        ws4.setRequiredNumber(0, 1, 1);
        lgr.log(I, "" + ws4.readSchedule(1).requiredNumber);

        lgr.log(T, "-------\n" + //
                "\nBlock #5:");
        WorkSchedule ws5 = new WorkSchedule(5);
        ws5.setRequiredNumber(0, 1, 2);
        lgr.log(I, "" + ws5.readSchedule(1).requiredNumber);
        lgr.log(I, "" + ws5.readSchedule(2).requiredNumber);

        lgr.log(T, "-------\n" + //
                "\nBlock #6:");
        WorkSchedule ws6 = new WorkSchedule(5);
        ws6.setRequiredNumber(0, 1, 0);
        lgr.log(I, "" + ws6.readSchedule(0).requiredNumber);
        lgr.log(I, "" + ws6.readSchedule(1).requiredNumber);

        lgr.log(T, "-------\n" + //
                "\nBlock #7:");
        WorkSchedule ws7 = new WorkSchedule(5);
        ws7.setRequiredNumber(1, 0, 0);
        lgr.log(I, "" + ws7.readSchedule(0).requiredNumber);

        lgr.log(T, "-------\n" + //
                "\nBlock #8:");
        WorkSchedule ws8 = new WorkSchedule(5);
        ws8.setRequiredNumber(1, 0, 1);
        lgr.log(I, "" + ws8.readSchedule(0).requiredNumber);
        lgr.log(I, "" + ws8.readSchedule(1).requiredNumber);

        lgr.log(T, "-------\n" + //
                "\nBlock #9:");
        WorkSchedule ws9 = new WorkSchedule(5);
        try {
            ws9.setRequiredNumber(1, 0, -1);
            lgr.log(I, "No exception");
        } catch (Exception e) {
            lgr.log(I, "exception");
            e.printStackTrace();
        }

        lgr.log(T, "-------\n" + //
                "\nBlock #10:");
        WorkSchedule ws10 = new WorkSchedule(5);
        ws10.setRequiredNumber(1, 1, 1);
        lgr.log(I, "" + ws10.readSchedule(1).requiredNumber);

        lgr.log(T, "-------\n" + //
                "\nBlock #11:");
        WorkSchedule ws11 = new WorkSchedule(5);
        ws11.setRequiredNumber(1, 1, 2);
        lgr.log(I, "" + ws11.readSchedule(1).requiredNumber);
        lgr.log(I, "" + ws11.readSchedule(2).requiredNumber);

        lgr.log(T, "-------\n" + //
                "\nBlock #12:");
        WorkSchedule ws12 = new WorkSchedule(5);
        ws12.setRequiredNumber(1, 1, 0);
        lgr.log(I, "" + ws12.readSchedule(0).requiredNumber);
        lgr.log(I, "" + ws12.readSchedule(1).requiredNumber);

        /*
         * -----------------------------------------------------------------------------
         */
        lgr.log(T, "b)\n\n\n" + //
                "\nBlock #1:");
        WorkSchedule wsb1 = new WorkSchedule(0);
        lgr.log(I, ""+wsb1.nextIncomplete(0));

        lgr.log(T, "-------\n" + //
                "\nBlock #2:");
        WorkSchedule wsb2 = new WorkSchedule(5);
        lgr.log(I, ""+wsb2.nextIncomplete(1));

    }
}
