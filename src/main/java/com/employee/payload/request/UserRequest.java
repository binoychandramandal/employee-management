package com.employee.payload.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class UserRequest {

    private Integer userId;

   @NotBlank
   @Size(min = 3, max = 20)
   private String username;

   @NotBlank
   @Size(max = 50)
   @Email
   private String email;

   private String roles;

   @NotBlank
   @Size(min = 6, max = 40)
   @JsonInclude(JsonInclude.Include.NON_NULL)
   private String password;

   private String name;

   private char gender;

    private String dob;

   private String phone;

   private Timestamp createAt;
   private String address;
   private String createdBy;
//    @JsonInclude(JsonInclude.Include.NON_EMPTY)
//   private Set<CustomerRequest> customers = new HashSet<>();
    @JsonInclude
  public Set<String> rolesNames(){
      if(Objects.nonNull(this.roles)){
         return  Stream.of(roles.split(",")).collect(Collectors.toSet());
      }
      return null;
  }


}
