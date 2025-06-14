// MemberRepo.java
package repository;

import model.Member;
import repository.Interface.IMemberRepo;

import java.util.HashMap;

public class MemberRepo implements IMemberRepo {

    public HashMap<Integer, Member> memberMap = new HashMap<>();

    public void AddMember(Member member) {
        memberMap.put(member.getMember_id(), member);
    }

    public void DeleteMemberById(int member_id) {
        memberMap.remove(member_id);
    }

    public Member getMemberById(int member_id) {
        return memberMap.get(member_id);
    }
}
