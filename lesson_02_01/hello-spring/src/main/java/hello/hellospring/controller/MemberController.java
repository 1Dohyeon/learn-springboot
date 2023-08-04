package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import hello.hellospring.domain.Member;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import org.springframework.ui.Model;

@Controller
public class MemberController {
    private final MemberService memberService;
    @Autowired  // 생성자 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @GetMapping("members/new")  // 브라우저 url 주소
    public String createForm() {
        return "members/createMemberForm";  // 실제 이동할 html 코드 주소
    }

    @PostMapping(value = "/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());

        memberService.join(member);
        return "redirect:/";    // 회원 가입 후 홈 화면으로 이동
    }

    @GetMapping("/members")
    public  String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
