package ru.dragonestia.jdash.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.dragonestia.jdash.JDashApplication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

@Component
public class DebugInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!request.getRequestURI().startsWith(JDashApplication.DATABASE_ADDRESS)) return true;

        System.out.println("[" + request.getMethod() + "] " + request.getRequestURI());
        Map<String, String[]> map = request.getParameterMap();

        for (String key : map.keySet()) {
            System.out.println("   " + key + ": " + Arrays.toString(map.get(key)));
        }
        return true;
    }
}
