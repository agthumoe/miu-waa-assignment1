package edu.miu.assignment.others;

public final class SecurityUtils {
    /**
     * Just to prevent instantiation.
     */
    private SecurityUtils() {}

    /**
     * Will implements and get principle name from spring security context in the future.
     * @return Authenticated user's name.
     */
    public static String getPrinciple() {
        return "Anonymous";
    }
}
