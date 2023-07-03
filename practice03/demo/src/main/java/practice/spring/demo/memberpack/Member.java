package practice.spring.demo.memberpack;

public class Member {
    private Long id;
    private String name;
    private Coupon cp;

    public Member(Long id, String name, Coupon cp) {
        this.id = id;
        this.name = name;
        this.cp = cp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coupon getCoupon() {
        return cp;
    }

    public void setGrade(Coupon cp) {
        this.cp = cp;
    }
}
