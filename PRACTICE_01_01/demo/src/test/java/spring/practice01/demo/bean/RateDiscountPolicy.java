package spring.practice01.demo.bean;

public class RateDiscountPolicy implements DiscountPolicy {

    @Override
    public int discount() {
        return 0;
    }
}
