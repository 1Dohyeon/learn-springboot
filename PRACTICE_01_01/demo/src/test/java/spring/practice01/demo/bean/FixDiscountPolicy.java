package spring.practice01.demo.bean;

public class FixDiscountPolicy implements DiscountPolicy {
    @Override
    public int discount() {
        return 10;
    }
}