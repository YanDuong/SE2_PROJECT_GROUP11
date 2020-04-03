package model;
import java.util.Map;
import java.util.HashMap;
public enum Gender {
    MALE(1), FEMALE(0);
    private int value;
    private static Map<Integer, Gender> genderMap;
    private Gender(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public Gender getGender(int value) {
        if(genderMap == null) {
            genderMap = new HashMap<>();
            genderMap.put(0, Gender.MALE);
            genderMap.put(1, Gender.FEMALE);
        }
        return genderMap.get(value);
    }
}

