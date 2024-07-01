package Formcom.example.Taaw3iya.dao.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
 
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
 
import static Formcom.example.Taaw3iya.dao.enums.Privilege.*;
import static Formcom.example.Taaw3iya.dao.enums.Role.ADMIN;
import static Formcom.example.Taaw3iya.dao.enums.Privilege.READ_PRIVILEGE;
import static Formcom.example.Taaw3iya.dao.enums.Privilege.WRITE_PRIVILEGE;
import static Formcom.example.Taaw3iya.dao.enums.Privilege.DELETE_PRIVILEGE;
import static Formcom.example.Taaw3iya.dao.enums.Privilege.UPDATE_PRIVILEGE;


@Getter
@RequiredArgsConstructor
public enum Role {
  ADMIN(
      Set.of(READ_PRIVILEGE,WRITE_PRIVILEGE,UPDATE_PRIVILEGE,DELETE_PRIVILEGE)
 ),
  USER(
      Set.of(READ_PRIVILEGE)
 );
 
  private final Set<Privilege> privileges;
 
  public List<SimpleGrantedAuthority> getAuthorities(){
    List<SimpleGrantedAuthority> authorities = getPrivileges()
       .stream()
       .map(privilege -> new SimpleGrantedAuthority(privilege.name()))
       .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
    return authorities;
 }
}
