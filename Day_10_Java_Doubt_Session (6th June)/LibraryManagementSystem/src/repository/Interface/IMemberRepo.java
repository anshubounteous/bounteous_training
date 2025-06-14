package repository.Interface;

import model.Member;
import java.util.HashMap;

public interface IMemberRepo {

    void AddMember(Member member);
    void DeleteMemberById(int member_id);
}
