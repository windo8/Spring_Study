package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest //spring test 보다 순수java코드로 단위 테스트가 웬만해선 더 좋은 test임
@Transactional //db에가서 sql문을 날리고 테스트 하나가 끝나면 rollback을 함
public class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given 주어진 상황
        Member member = new Member();
        member.setName("kojun1");

        //when 실행했을때
        Long id = memberService.join(member);

        //then 결과 도출
        Member findMember = memberService.findOne(id).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중목_회원_예외() {
        Member member1 = new Member();
        member1.setName("kojun1");
        Member member2 = new Member();
        member2.setName("kojun1");

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,() -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

      /*  try {
            memberService.join(member2);
            System.out.println("예외가 발생 해야합니다");
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
    }

    @Test
    public void 회원_목록() {
       List<Member> result =  memberService.findMembers();
       assertThat(result.size()).isEqualTo(7);
    }
}
