package com.codecampushubt.NCKH2024TQQD._enum.Admin.Lesson;

public enum type {
    VIDEO("video"),
    TEXT("text"),
    QUIZ("quiz"),
    ASSIGMENT("assignment"),
    CODING("coding");
    private final String value;
    type(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

    public static type fromString(String text){
        for (type t : type.values()) {
            if (t.value.equalsIgnoreCase(text)){
                return t;
            }
        }
        throw new IllegalArgumentException("No enum constant " + text);
    }
}
