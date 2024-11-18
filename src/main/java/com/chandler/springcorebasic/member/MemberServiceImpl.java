package com.chandler.springcorebasic.member;

public class MemberServiceImpl implements MemberService{ //TODO: 관례로 구현체가 1개일 경우 "interface명"에 postfix로 impl을 해줌

    private final MemoryMemberRepository repository = new MemoryMemberRepository(); //TODO: final(명확한 설계의도) + 구현체 직접 주입

    @Override
    public void join(Member member) {
        repository.save(member);
    }

    @Override
    public Member findMember(Long id) {
        return repository.findById(id);
    }
}
