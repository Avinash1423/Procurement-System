package com.proc.system;
import com.proc.system.Model.DeleteUser;
import com.proc.system.Model.NewUserForm;
import com.proc.system.Model.NewUserFormRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteUserTest {

    @Mock
    NewUserFormRepository newUserFormRepository;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    DeleteUser deleteUser;


    @Test
    public void checkIfVerifyPasswordIsWorking(){
        Integer empId=111;
        String password="hello";
        String actualPassword="$h82873y$&";

        NewUserForm newUserForm=new NewUserForm();
        newUserForm.setEmpId(empId);
        newUserForm.setPassword(actualPassword);

        when(newUserFormRepository.findById(empId)).thenReturn(Optional.of(newUserForm));
      //  when(newUserForm.getPassword()).thenReturn(actualPassword);
        when(bCryptPasswordEncoder.matches(password,actualPassword)).thenReturn(true);

        boolean result= deleteUser.verifyPassword(empId,password);

       assertTrue(result);

    }



}
