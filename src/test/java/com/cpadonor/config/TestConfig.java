package com.cpadonor.config;

/**
 * Centralised configuration for tests.
 *
 * Override via:
 *  -DbaseUrl=https://...
 *  -DapiBaseUrl=https://...
 *  -DuserEmail=...
 *  -DuserPass=...
 */
public final class TestConfig {
    private TestConfig() {}

    public static String baseUrl() {
        return get("baseUrl", "https://cpadonor.com");
    }

    public static String apiBaseUrl() {
        return get("apiBaseUrl", "https://api.cpadonor.com/api");
    }

    public static String userEmail() {
        return get("userEmail", "batmansila1234@gmail.com");
    }

    public static String userPass() {
        return get("userPass", "batmansila1234");
    }

    public static boolean isCi() {
        String ci = System.getenv("CI");
        if (ci != null && !ci.isBlank()) return true;
        return Boolean.parseBoolean(System.getProperty("ci", "false"));
    }

    private static String get(String key, String def) {
        String v = System.getProperty(key);
        if (v != null && !v.isBlank()) return v;
        v = System.getenv(key);
        if (v != null && !v.isBlank()) return v;
        return def;
    }
}
