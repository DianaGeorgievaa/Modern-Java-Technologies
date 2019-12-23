package christmas;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class Workshop {
    private static final int NUMBER_OF_ELVES = 3;

    private Queue<Gift> backlog;
    private AtomicInteger wishCount;
    private Elf[] elves;
    private boolean isChristmasTime;

    public Workshop() {
        backlog = new LinkedList<>();
        elves = new Elf[NUMBER_OF_ELVES];
        isChristmasTime = false;
        wishCount = new AtomicInteger(0);
        createElves();
    }

    /**
     * Adds a gift to the elves' backlog.
     **/
    public synchronized void postWish(Gift gift) {
        backlog.add(gift);
        wishCount.getAndIncrement();
        this.notifyAll();
    }

    /**
     * Returns an array of the elves working in Santa's workshop.
     **/
    public Elf[] getElves() {
        return elves;
    }

    /**
     * Returns the next gift from the elves' backlog that has to be manufactured.
     **/
    public synchronized Gift nextGift() {
        return backlog.poll();
    }

    /**
     * Returns the number of gifts in backlog.
     **/
    public int getGiftsCountInBacklog() {
        return backlog.size();
    }

    /**
     * Returns the total number of wishes sent to Santa's workshop by the kids.
     **/
    public int getWishCount() {
        return wishCount.get();
    }

    public boolean isChristmasTime() {
        return isChristmasTime;
    }

    public synchronized void setChristmasTime() {
        isChristmasTime = true;
        this.notifyAll();
    }

    private void createElves() {
        for (int i = 0; i < NUMBER_OF_ELVES; i++) {
            elves[i] = new Elf(i, this);
            elves[i].start();
        }
    }
}