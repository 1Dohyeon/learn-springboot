package memory;

import java.util.ArrayList;

public class MemoryImpl implements Memory {
    private static ArrayList<String> store = new ArrayList<String>();

    @Override
    public void save(String kind) {
        store.add(kind);
    }

    @Override
    public ArrayList<String> getMemory() {
        return store;
    }
}
