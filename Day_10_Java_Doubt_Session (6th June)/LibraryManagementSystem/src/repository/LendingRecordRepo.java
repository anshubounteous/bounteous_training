//package repository;
//
//import model.Book;
//import model.LendingRecord;
//import model.Member;
//import repository.Interface.ILandingRecordRepo;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class LendingRecordRepo implements ILandingRecordRepo {
//
//    public List<LendingRecord> lendingRecordList = new ArrayList<>();
//
//    public void issueABook(Book book, Member member, Date issue_date, Date due_date) {
//        LendingRecord record = new LendingRecord();
//        record.setBook(book);
//        record.setMember(member);
//        record.setIssue_date(issue_date);
//        record.setDue_date(due_date);
//        record.setReturn_date(null);
//        record.setRecord_id(lendingRecordList.size() + 1);
//        lendingRecordList.add(record);
//        book.setIs_issued(true);
//    }
//
//    public void returnABook(Book book, Member member, Date return_date) {
//        for (LendingRecord record : lendingRecordList) {
//            if (record.getBook().equals(book) && record.getMember().equals(member) && record.getReturn_date() == null) {
//                record.setReturn_date(return_date);
//                book.setIs_issued(false);
//                break;
//            }
//        }
//    }
//
//    public List<LendingRecord> getRecords() {
//        return lendingRecordList;
//    }
//}


package repository;

import model.LendingRecord;

import java.util.*;

public class LendingRecordRepo {
    private final Map<Integer, LendingRecord> records = new HashMap<>();

    public void add(LendingRecord record) {
        records.put(record.getRecord_id(), record);
    }

    public Optional<LendingRecord> get(int recordId) {
        return Optional.ofNullable(records.get(recordId));
    }

    public void update(int recordId, LendingRecord record) {
        if (records.containsKey(recordId)) {
            records.put(recordId, record);
        }
    }

    public void delete(int recordId) {
        records.remove(recordId);
    }

    public Collection<LendingRecord> getAll() {
        return records.values();
    }
}
