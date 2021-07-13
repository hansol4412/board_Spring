package kr.ac.kopo41.ctc.spring.board.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping(value ="/main")
	public String helloStrpingBoot(Model model) {
		return "main";
	}
}
