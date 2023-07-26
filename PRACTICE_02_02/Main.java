public class Main {
    public static void main(String[] args) {
        Person 짱구 = new Person("유쌍", "직선 코", "큰입");
        Person 철수 = new Person("무쌍", "독수리 코", "큰입");
        Person 맹구 = new Person("무쌍", "매부리 코", "작은");

        System.out.println("짱구 코 = " + 짱구.getnose());
        System.out.println("철수 코 = " + 철수.getnose());
        System.out.println("맹구 코 = " + 맹구.getnose());

        // 철수의 코 성형수술
        System.out.println("철수 코 = " + 철수.getnose());
        Surgery surgery1 = new NoseSurgery();
        surgery1.plasticSurgery(철수);

        System.out.println("철수 코 = " + 철수.getnose());

        // 맹구의 눈 성형수술
        System.out.println("맹구 눈 = " + 맹구.getEyes());
        Surgery surgery2 = new EyesSurgery();
        surgery2.plasticSurgery(맹구);

        System.out.println("맹구 눈 = " + 맹구.getEyes());
    }
}
