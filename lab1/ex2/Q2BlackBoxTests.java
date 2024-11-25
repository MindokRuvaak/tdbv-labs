import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.Formatter;

public class Q2BlackBoxTests {

    private static final Logger lgr = Logger.getLogger(Q2BlackBoxTest.class.getName());
    private static final Level I = Level.INFO;
    private static final Level C = Level.CONFIG;

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

        lgr.log(C, "a)\n" + //
                "\nBlock #1:");

        WorkSchedule ws1 = new WorkSchedule(5);
        ws1.setRequiredNumber(0, 0, 0);
        lgr.log(I, "" + ws1.readSchedule(0).requiredNumber);

        lgr.log(C, "-------\n" + //
                "\nBlock #2:");

        WorkSchedule ws2 = new WorkSchedule(5);
        ws2.setRequiredNumber(0, 0, 1);
        lgr.log(I, "" + ws2.readSchedule(0).requiredNumber);
        lgr.log(I, "" + ws2.readSchedule(1).requiredNumber);

        lgr.log(C, "-------\n" + //
                "\nBlock #3:");

        WorkSchedule ws3 = new WorkSchedule(5);
        try {
            ws3.setRequiredNumber(0, 0, -1);
            lgr.log(I, "no exception");
            lgr.log(I, "" + ws3.readSchedule(0).requiredNumber);
        } catch (Exception e) {
            lgr.log(I, "Exception caugth");
        }

        lgr.log(C, "-------\n" + //
                "\nBlock #4:");
        WorkSchedule ws4 = new WorkSchedule(5);
        ws4.setRequiredNumber(0, 1, 1);
        lgr.log(I, "" + ws4.readSchedule(1).requiredNumber);

        lgr.log(C, "-------\n" + //
                "\nBlock #5:");
        WorkSchedule ws5 = new WorkSchedule(5);
        ws5.setRequiredNumber(0, 1, 2);
        lgr.log(I, "" + ws5.readSchedule(1).requiredNumber);
        lgr.log(I, "" + ws5.readSchedule(2).requiredNumber);

        lgr.log(C, "-------\n" + //
                "\nBlock #6:");
        WorkSchedule ws6 = new WorkSchedule(5);
        ws6.setRequiredNumber(0, 1, 0);
        lgr.log(I, "" + ws6.readSchedule(0).requiredNumber);
        lgr.log(I, "" + ws6.readSchedule(1).requiredNumber);

        lgr.log(C, "-------\n" + //
                "\nBlock #7:");
        WorkSchedule ws7 = new WorkSchedule(5);
        ws7.setRequiredNumber(1, 0, 0);
        lgr.log(I, "" + ws7.readSchedule(0).requiredNumber);

        lgr.log(C, "-------\n" + //
                "\nBlock #8:");
        WorkSchedule ws8 = new WorkSchedule(5);
        ws8.setRequiredNumber(1, 0, 1);
        lgr.log(I, "" + ws8.readSchedule(0).requiredNumber);
        lgr.log(I, "" + ws8.readSchedule(1).requiredNumber);

        lgr.log(C, "-------\n" + //
                "\nBlock #9:");
        WorkSchedule ws9 = new WorkSchedule(5);
        try {
            ws9.setRequiredNumber(1, 0, -1);
            lgr.log(I, "No exception");
        } catch (Exception e) {
            lgr.log(I, "exception");
            e.printStackTrace();
        }

        lgr.log(C, "-------\n" + //
                "\nBlock #10:");
        WorkSchedule ws10 = new WorkSchedule(5);
        ws10.setRequiredNumber(1, 1, 1);
        lgr.log(I, "" + ws10.readSchedule(1).requiredNumber);

        lgr.log(C, "-------\n" + //
                "\nBlock #11:");
        WorkSchedule ws11 = new WorkSchedule(5);
        ws11.setRequiredNumber(1, 1, 2);
        lgr.log(I, "" + ws11.readSchedule(1).requiredNumber);
        lgr.log(I, "" + ws11.readSchedule(2).requiredNumber);

        lgr.log(C, "-------\n" + //
                "\nBlock #12:");
        WorkSchedule ws12 = new WorkSchedule(5);
        ws12.setRequiredNumber(1, 1, 0);
        lgr.log(I, "" + ws12.readSchedule(0).requiredNumber);
        lgr.log(I, "" + ws12.readSchedule(1).requiredNumber);

        lgr.log(C, "-------\n" + //
                "\nBorder case #1:");
        try {
            WorkSchedule wsBC1 = new WorkSchedule(Integer.MAX_VALUE / 100);
            wsBC1.setRequiredNumber(Integer.MAX_VALUE / 100, Integer.MAX_VALUE / 100,
                    Integer.MAX_VALUE / 100);
            lgr.log(I, "" + wsBC1.readSchedule(Integer.MAX_VALUE / 100).requiredNumber);
        } catch (Exception e) {
            // e.printStackTrace();
        }

        lgr.log(C, "-------\n" + //
                "\nFurther testing");
        WorkSchedule wsExceptionTest = new WorkSchedule(0);
        try {
            wsExceptionTest.setRequiredNumber(0, 0, 0);
            lgr.log(I, "" + wsExceptionTest.readSchedule(0).requiredNumber + " {0:0,0,0}");
        } catch (IndexOutOfBoundsException e) {
            lgr.log(I, "Index out of bounds exception caught" + " {0:0,0,0}");
        }

        WorkSchedule wsExceptionTest2 = new WorkSchedule(1);
        try {
            wsExceptionTest2.setRequiredNumber(0, 0, 1);
            lgr.log(I, "" + wsExceptionTest2.readSchedule(0).requiredNumber + " {1:0,0,1}");
        } catch (IndexOutOfBoundsException e) {
            lgr.log(I, "Index out of bounds exception caught" + " {1:0,0,1}");
        }

        WorkSchedule wsExceptionTest3 = new WorkSchedule(1);
        try {
            wsExceptionTest3.setRequiredNumber(0, 1, 0);
            lgr.log(I, "" + wsExceptionTest3.readSchedule(0).requiredNumber + " {1:0,1,0}");
        } catch (IndexOutOfBoundsException e) {
            lgr.log(I, "Index out of bounds exception caught" + " {1:0,1,0}");
        }

        WorkSchedule wsExceptionTest4 = new WorkSchedule(1);
        try {
            wsExceptionTest4.setRequiredNumber(0, 0, 0);
            lgr.log(I, "" + wsExceptionTest4.readSchedule(0).requiredNumber + " {1:0,0,0}");
        } catch (IndexOutOfBoundsException e) {
            lgr.log(I, "Index out of bounds exception caught" + " {1:0,0,0}");
        }

        WorkSchedule wsExceptionTest5 = new WorkSchedule(1);
        try {
            wsExceptionTest5.setRequiredNumber(0, 0, 2);
            lgr.log(I, "" + wsExceptionTest5.readSchedule(0).requiredNumber + " {1:0,0,2}");
        } catch (IndexOutOfBoundsException e) {
            lgr.log(I, "Index out of bounds exception caught+ {1:0,0,2}\n\n\n");
        }

        /*
         * -----------------------------------------------------------------------------
         */

        lgr.log(C, "b)\n\n\n" + //
                "\nBlock #1:");
        WorkSchedule wsb1 = new WorkSchedule(0);
        lgr.log(I, "" + wsb1.nextIncomplete(0));

        lgr.log(C, "-------\n" + //
                "\nBlock #2:");
        WorkSchedule wsb2 = new WorkSchedule(1);
        lgr.log(I, "" + wsb2.nextIncomplete(0));

        lgr.log(C, "-------\n" + //
                "\nBlock #3:");
        WorkSchedule wsb3 = new WorkSchedule(0);
        lgr.log(I, "" + wsb3.nextIncomplete(1));

        lgr.log(C, "-------\n" + //
                "\nBlock #4:");
        WorkSchedule wsb4 = new WorkSchedule(1);
        lgr.log(I, "" + wsb4.nextIncomplete(1));

        lgr.log(C, "-------\n" + //
                "\nBlock #5:");
        WorkSchedule wsb5 = new WorkSchedule(1);
        wsb5.setRequiredNumber(1, 0, 0);
        lgr.log(I, "" + wsb5.nextIncomplete(0));

        lgr.log(C, "-------\n" + //
                "\nBlock #6:");
        WorkSchedule wsb6 = new WorkSchedule(2);
        wsb6.setRequiredNumber(1, 1, 1);
        lgr.log(I, "" + wsb6.nextIncomplete(0));

        lgr.log(C, "-------\n" + //
                "\nBlock #7:");
        WorkSchedule wsb7 = new WorkSchedule(1);
        wsb7.setRequiredNumber(1, 0, 0);
        lgr.log(I, "" + wsb7.nextIncomplete(1));

        lgr.log(C, "-------\n" + //
                "\nBlock #8:");
        WorkSchedule wsb8 = new WorkSchedule(2);
        wsb8.setRequiredNumber(1, 1, 1);
        lgr.log(I, "" + wsb8.nextIncomplete(1));



        int a = 1048576;
        lgr.log(C, "-------\n" + //
                "\nEdge cases:");
        WorkSchedule wsbEC1 = new WorkSchedule(a);
        lgr.log(I, "" + wsbEC1.nextIncomplete(a-1));
        
        WorkSchedule wsbEC2 = new WorkSchedule(a);
        wsbEC2.setRequiredNumber(a, a-1, a-1);
        lgr.log(I, "" + wsbEC2.nextIncomplete(a-1));


    }
}
