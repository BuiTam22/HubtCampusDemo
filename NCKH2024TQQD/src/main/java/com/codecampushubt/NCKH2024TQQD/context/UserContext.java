package com.codecampushubt.NCKH2024TQQD.context;

public class UserContext {
    private static final ThreadLocal<String> usernameHolder = new ThreadLocal<>();
    private static final ThreadLocal<Long> userIDHolder = new ThreadLocal<>();

    public static void setUsername(String username) {
        usernameHolder.set(username);
    }

    public static String getUsername() {
        return usernameHolder.get();
    }

    public static void setUserID(Long userID) {
        userIDHolder.set(userID);
    }

    public static Long getUserID() {
        return userIDHolder.get();
    }

    public static void clear() {
        usernameHolder.remove();
        userIDHolder.remove();
    }
}
