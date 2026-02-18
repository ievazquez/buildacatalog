package com.cakefactory;

import com.cakefactory.catalog.CatalogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CatalogController {

    private final CatalogService catalog;

    public CatalogController(CatalogService catalog) {
        this.catalog = catalog;
    }

    @GetMapping("/")
    ModelAndView index() {
        ModelAndView mav = new ModelAndView("catalog");
        mav.addObject("items", catalog.getItems());
        return mav;
    }

}