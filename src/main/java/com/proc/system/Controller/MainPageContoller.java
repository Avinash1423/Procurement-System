package com.proc.system.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageContoller {


    @GetMapping("/MainPage")
    public String MainPage(){


        return "MainPage";

    }

    @GetMapping("/fromMainToAdmin")
    public String fromMainToAdmin(){


        return "adminLoginPage";

    }

    @GetMapping("/fromMainToEmp")
    public String fromMainToEmp(){


        return "EmployeeLoginPage";

    }


}

