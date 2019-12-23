package christmas;

import java.util.concurrent.atomic.AtomicInteger;

public class Elf extends Thread {
    private int id;
    private final Workshop workshop;
    private AtomicInteger numberOfCraftedGifts;

    public Elf(int id, Workshop workshop) {
        this.id = id;
        this.workshop = workshop;
        numberOfCraftedGifts = new AtomicInteger(0);
    }

    /**
     * Gets a wish from the backlog and creates the wanted gift.
     **/
    public void craftGift() throws InterruptedException {
        Gift currentGift = workshop.nextGift();

        Thread.sleep(currentGift.getCraftTime());
        numberOfCraftedGifts.getAndIncrement();
    }

    /**
     * Get total gifts crafted
     *
     * @return the total number of gifts that the given elf has crafted.
     */
    public int getTotalGiftsCrafted() {
        return numberOfCraftedGifts.get();
    }

    @Override
    public void run() {
        synchronized (workshop) {
            while (!workshop.isChristmasTime()
                    || workshop.getGiftsCountInBacklog() != 0) {
                try {
                    if (workshop.getGiftsCountInBacklog() != 0) {
                        craftGift();
                    } else {
                        workshop.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}