package com.nanhng.FastFood.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecurityContexts {
    private static ThreadLocal<SecurityContexts> context = new ThreadLocal<SecurityContexts>();
    private Object data;

    public static SecurityContexts getContext() {
        return context.get();
    }

    public static void newContext() {
        SecurityContexts securityContexts = new SecurityContexts();
        SecurityContexts.context.set(securityContexts);
    }
}
