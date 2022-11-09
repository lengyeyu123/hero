package com.han.hero.framework.security;

import com.han.hero.common.enums.DelFlagEnums;
import com.han.hero.project.domain.Menu;
import com.han.hero.project.domain.Role;
import com.han.hero.project.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
        authorities.addAll(roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName())).collect(Collectors.toList()));
        authorities.addAll(menus.stream().map(menu -> new SimpleGrantedAuthority(menu.getPerms())).collect(Collectors.toList()));
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
        if (user.getDelFlag() == null) {
            return false;
        }
        return user.getDelFlag().equals(DelFlagEnums.NORMAL);
    }
}
