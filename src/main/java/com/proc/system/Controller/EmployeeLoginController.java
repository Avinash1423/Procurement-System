package com.proc.system.Controller;

import com.proc.system.Model.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeLoginController {

    NewUserFormRepository newUserFormRepository;
    ValidateEmployeeLoginDetails validateEmployeeLoginDetails;
    PurReqObjectRepository purReqObjectRepository;

    @Autowired
    public EmployeeLoginController(ValidateEmployeeLoginDetails validateEmployeeLoginDetails, NewUserFormRepository newUserFormRepository,PurReqObjectRepository purReqObjectRepository) {
        this.validateEmployeeLoginDetails = validateEmployeeLoginDetails;
        this.newUserFormRepository = newUserFormRepository;
        this.purReqObjectRepository=purReqObjectRepository;
    }

    @GetMapping("/employeeLogin")
    public String employeeLoginMethod() {

        return "EmployeeLoginPage";

    }

    @PostMapping("/validateEmpLogin")
    public String getEmployeeLoginDetails(@RequestParam String empId, @RequestParam String empPassword, Model model, HttpSession session) {


        if (empId.isEmpty() || empPassword.isEmpty()) {

            model.addAttribute("emptyFieldError", "Complete all required Fields");

            return "EmployeeLoginPage";

        }

        try {
            Integer EmpIdInt = Integer.parseInt(empId);


            if (!validateEmployeeLoginDetails.validate(EmpIdInt)) {
                model.addAttribute("accountDoesNotExistError", "Account doesn't exist. Try again.");
                return "EmployeeLoginPage";
            } else if (!validateEmployeeLoginDetails.checkPassword(EmpIdInt, empPassword)) {

                model.addAttribute("passwordIncorrectError", "Password is incorrect. Try Again.");
                return "EmployeeLoginPage";
            } else

                session.setAttribute("LoggedInEmployee",EmpIdInt);
                 model.addAttribute("listOfPurReqs",purReqObjectRepository.findByRaisedBy(EmpIdInt));
                return "EmployeeView";
        } catch (NumberFormatException e) {
            model.addAttribute("accountDoesNotExistError", "Account doesn't exist. Try again.");
            return "EmployeeLoginPage";

        }
    }
}





