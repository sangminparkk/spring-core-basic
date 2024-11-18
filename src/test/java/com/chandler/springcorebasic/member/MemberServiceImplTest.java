package com.chandler.springcorebasic.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.chandler.springcorebasic.member.Grade.VIP;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MemberServiceImplTest {

    MemberServiceImpl memberService = new MemberServiceImpl();

    @Test
    @DisplayName("회원 가입") //TODO: 데이터 비교는 최소 2~3개 할것
    public void join() throws Exception {
        // given
        long memberId = 1L;
        Member memberA = new Member(memberId, "memberA", VIP);

        // when
        memberService.join(memberA);

        // then
        Member findMember = memberService.findMember(memberId);

        assertEquals(memberA, findMember); //TODO: object 비교도 추가해야함
        assertEquals(memberId, findMember.getId());
        assertEquals(memberA.getName(), findMember.getName());
        assertEquals(memberA.getGrade(), findMember.getGrade());
    }

}