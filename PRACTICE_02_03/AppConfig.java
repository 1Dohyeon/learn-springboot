import memory.Memory;
import memory.MemoryImpl;
import surgery.EyesSurgery;
import surgery.Surgery;

public class AppConfig {
    public Surgery surgery() {
        return new EyesSurgery(
                memory());
        /*
         * return new NoseSurgery(
         * memory());
         */
    }

    public Memory memory() {
        return new MemoryImpl();
    }
}
