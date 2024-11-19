package com.chandler.springcorebasic.member;

public class MemberServiceImpl implements MemberService{ //TODO: 관례로 구현체가 1개일 경우 "interface명"에 postfix로 impl을 해줌

    private final MemberRepository repository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.repository = memberRepository;
    }

    @Override
    public void join(Member member) {
        repository.save(member);
    }

    @Override
    public Member findMember(Long id) {
        return repository.findById(id);
    }
}
