package com.cakefactory;

import com.cakefactory.catalog.Catalog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final Catalog catalog;

    public IndexController(Catalog catalog) {
        this.catalog = catalog;
    }

    @GetMapping("/")
    String index(Model model) {
        model.addAttribute("items", catalog.getItems());
        return "index";
    }

}