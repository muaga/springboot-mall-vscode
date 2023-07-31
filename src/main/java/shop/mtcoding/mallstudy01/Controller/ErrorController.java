package shop.mtcoding.mallstudy01.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/ex40x")
    public String ex40x() {
        return "error/ex40x";
    }

    @GetMapping("/exist")
    public String exist() {
        return "error/exist";
    }

    @GetMapping("/null")
    public String empty() {
        return "error/null";
    }
}
