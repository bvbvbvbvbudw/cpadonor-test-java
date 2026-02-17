package com.cpadonor.config;

/**
 * Shared configuration used by both API and UI layers.
 */
public final class AppConfig {
    private AppConfig() {}

    public static String apiBaseUrl() {
        return get("apiBaseUrl", "https://api.cpadonor.com/api");
    }

    public static String baseUrl() {
        return get("baseUrl", "https://cpadonor.com");
    }

    private static String get(String key, String def) {
        String v = System.getProperty(key);
        if (v != null && !v.isBlank()) return v;
        v = System.getenv(key);
        if (v != null && !v.isBlank()) return v;
        return def;
    }
}
