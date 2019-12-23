package christmas;

import java.util.Random;

public class Kid extends Thread {
    private static final int MAX_TIME_FOR_CHOOSING_GIFT = 10;

    private Workshop workshop;
    private int timeToChooseGift;

    public Kid(Workshop workshop) {
        this.workshop = workshop;
        timeToChooseGift = new Random().nextInt(MAX_TIME_FOR_CHOOSING_GIFT);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(timeToChooseGift);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        workshop.postWish(Gift.getGift());
    }
}