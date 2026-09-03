package com.foodapp.lib;

import com.foodapp.entity.User;
import com.foodapp.security.user.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {

    public CustomUserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        return (CustomUserDetails) authentication.getPrincipal();
    }
    public User getUser() {
        return getCurrentUserDetails().user();
    }

    public Long getUserId() {
        return getUser().getId();
    }

    public String getEmail() {
        return getUser().getEmail();
    }
}