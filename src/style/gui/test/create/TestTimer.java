package style.gui.test.create;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TestTimer extends TimerTask {

    @Override
    public void run() {
        System.out.println("Start time:" + new Date());
        timer();
        System.out.println("End time:" + new Date());
    }

    private void timer() {
        try {

            Thread.sleep(10000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void Task() {

        TimerTask timerTask = new TestTimer();
        // running timer task as daemon thread
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 10 * 1000);
        System.out.println("TimerTask begins! :" + new Date());
        // cancel after sometime
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
        System.out.println("TimerTask cancelled! :" + new Date());
        System.exit(20);
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}