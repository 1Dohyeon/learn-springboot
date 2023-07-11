package practice1.spring.demo.xml;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.support.GenericXmlApplicationContext;

import practice1.spring.demo.Member.MemberService;

import static org.assertj.core.api.Assertions.*;

public class XmlAppContext {
    @Test
    void xmlAppContext() {
        try (GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml")) {
            MemberService memberService = ac.getBean("memberService", MemberService.class);
            assertThat(memberService).isInstanceOf(MemberService.class);
        } catch (BeansException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
