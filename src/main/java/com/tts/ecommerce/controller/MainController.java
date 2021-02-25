package com.tts.ecommerce.controller;

import com.tts.ecommerce.model.Product;
import com.tts.ecommerce.service.ProductService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Data
@Controller
@ControllerAdvice // This makes the `@ModelAttribute`s of this controller available to all controllers, for the navbar.
public class MainController {

    @Autowired
    ProductService productService;

    @GetMapping(value = "/")
    public String main() {
        return "main";
    }

    @ModelAttribute("products")
    public List<Product> products() {
        return productService.findAll();
    }

    @ModelAttribute("categories")
    public List<String> categories() {
        return productService.findDistinctCategories();
    }

    @ModelAttribute("brands")
    public List<String> brands() {
        return productService.findDistinctBrands();
    }

    @GetMapping(value = "/filter")
    public String filter(@RequestParam(required = false) String category,
                         @RequestParam(required = false) String brand,
                         Model model) {
        List<Product> filtered = productService.findByBrandAndOrCategory(brand, category);
        model.addAttribute("products", filtered); // Overrides the @ModelAttribute above.
        return "main";
    }

    @GetMapping(value = "/about")
    public String about() {
        return "about";
    }

//    ////////////////////////////////////////////////////////////


//    @GetMapping("/allproducts")
//    public String AllProducts() {
//        return "allproducts";
//    }
//
//    public void addNew() {
//        List<Product> newProducts = new ArrayList<Product>();
//
//        newProducts.add(new Product(4, (float) 1500.00, "Apple Mackbook Pro", "MAckbook Pro", "Apple", "computer", "images/pointer.jpg"));
//        newProducts.add(new Product(5, (float) 1500.00, "Apple Mackbook Pro 2", "MAckbook Pro 2", "Apple", "computer", "images/pointer.jpg"));
//        List<Product> old = productService.findAll();
//        for (Product product : old) {
//            productService.deleteById(product.getId());
//        }
//        for (Product product : newProducts) {
//            productService.save(product);
//        }
//    }





}

