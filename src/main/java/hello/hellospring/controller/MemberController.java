package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;
    /*
    * 생정자 주입의 3가지
    * -필드 주입
    * -setter 주입
    * -생성자 주입
    * 의존관계 실행중 동적변경 거의X 생성자 주입 권장
    *
    */
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new") // 회원가입 form
    public String CreateForm() {
        return "members/createMemberForm";
    }

    @PostMapping("members/new") //회원가입 form 전송시
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; //"home"
    }

    @GetMapping("/members") //member list 출력
    public String memberList(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "/members/memberList";
    }

    @GetMapping("/members/delete")
    public String deleteForm() {
        return "/members/deleteMemberForm";
    }
//
//    @PostMapping("/members/delete")
//    public String delete(MemberForm form) {
//        Member member = new Member();
//        member.setName(form.getName());
//        int result = memberService.deleteMember(member);
//        if (result == 1) {
//            return "/members/memberList";
//        }
//        return "redirect:/";
//    }
}
