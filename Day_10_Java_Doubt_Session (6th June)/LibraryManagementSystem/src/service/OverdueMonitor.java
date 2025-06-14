package service;

import model.LendingRecord;

import java.util.Date;
import java.util.List;

public class OverdueMonitor extends Thread {
    private final List<LendingRecord> lendingRecords;

    public OverdueMonitor(List<LendingRecord> lendingRecords) {
        this.lendingRecords = lendingRecords;
    }

    @Override
    public void run() {
        while (true) {
            Date now = new Date();
            System.out.println("[Monitor] Checking for overdue books at: " + now);
            for (LendingRecord record : lendingRecords) {
                if (record.getReturn_date() == null && record.getDue_date().before(now)) {
                    System.out.println("[Overdue] Book: " + record.getBook().getTitle() +
                            ", Member: " + record.getMember().getName());
                }
            }
            try {
                Thread.sleep(60000); 
            } catch (InterruptedException e) {
                System.out.println("[Monitor] Interrupted.");
                break;
            }
        }
    }
}
