package service;

import model.dto.MemberDto;
import repository.MemberRepository;

import java.util.List;

public class MemberService {

    private MemberRepository memberRepository;

    public MemberService() {
        this.memberRepository = new MemberRepository();
    }

    public boolean registerMember(MemberDto memberDto) {
        // Validate member data
        if (!isValidMember(memberDto)) {
            return false;
        }

        // Check if member ID is unique
        if (!isUniqueMemberId(memberDto.getIDstudendore())) {
            return false;
        }

        // Call method to save member to database
        return memberRepository.create(memberDto);
    }

    private boolean isValidMember(MemberDto memberDto) {
        // Perform validation on member data
        if (memberDto.getIDstudendore().isEmpty() || memberDto.getEmri().isEmpty() || memberDto.getEmail().isEmpty()
                || memberDto.getGjinia().isEmpty() || memberDto.getNumerTelefoni().isEmpty()) {
            return false;
        }

        // Additional validation logic can be added here

        return true;
    }

    private boolean isUniqueMemberId(String memberId) {
        List<String> existingMemberIds = memberRepository.getAllMemberIds();
        return !existingMemberIds.contains(memberId);
    }
}
