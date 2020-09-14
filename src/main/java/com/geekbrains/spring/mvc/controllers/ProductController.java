package com.geekbrains.spring.mvc.controllers;

import com.geekbrains.spring.mvc.model.Product;
import com.geekbrains.spring.mvc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listAllProducts(Model model) {
        List<Product> allProducts = productService.getAllProducts();
        model.addAttribute("catalog", allProducts);
        return "product_catalog";
    }

    @PostMapping("/add_product")
    public String addProduct(@RequestParam String title, @RequestParam int cost) {
        productService.addProduct(new Product(title, cost));
        return "redirect:/products";
    }

    @GetMapping("/remove_product")
    public String removeProduct(@RequestParam Long id) {
        productService.removeProductById(id);
        return "redirect:/products";
    }
}
