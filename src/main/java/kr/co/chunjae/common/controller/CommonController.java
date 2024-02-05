package kr.co.chunjae.common.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class CommonController {
  @GetMapping
    public String mainPage(){
    return "common/mainPage";
  }


  /*남원우*/




  /*변재혁*/




  /*유지호*/




  /*이무현*/




  /*최경락*/
}
