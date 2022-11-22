package com.han.hero.framework.security;

import com.han.hero.project.domain.Menu;
import com.han.hero.project.domain.Role;
import com.han.hero.project.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements UserDetails {

    private User user;

    private List<Role> roles;

    private List<Menu> menus;

    /**
     * spring security 中的对于ROLE_字符串的权限有特殊处理  对于这种字符串可以使用 @PreAuthorize("hasRole('super')") 注解
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.addAll(roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getCode())).collect(Collectors.toList()));
        authorities.addAll(menus.stream()
                .filter(menu -> StringUtils.isNotBlank(menu.getPerms()))
                .map(menu -> new SimpleGrantedAuthority(menu.getPerms())).collect(Collectors.toList()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
