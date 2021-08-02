package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
public class StudyMemberRepositoryTest {
    @Autowired StudyMemberRepository memberRepository;

    @Test
    @Transactional
    public void testMember() throws Exception {
        //given
        StudyMember member = new StudyMember();
        member.setUserName("memberA");
        //when
        Long saveId = memberRepository.save(member);
        StudyMember findMember = memberRepository.find(saveId);
        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUserName()).isEqualTo(member.getUserName());
        Assertions.assertThat(findMember).isEqualTo(member);
        System.out.println("findMember == member : " + (findMember==member));

    }
}