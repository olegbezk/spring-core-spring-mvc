package guru.springframework.controllers;

import guru.springframework.commands.ProductForm;
import guru.springframework.converters.ProductToProductForm;
import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class ProductController {

    private final ProductService productService;

    private final ProductToProductForm productToProductForm;

    @Autowired
    public ProductController(final ProductService productService, final ProductToProductForm productToProductForm) {
        this.productService = productService;
        this.productToProductForm = productToProductForm;
    }

    @RequestMapping("/product/list")
    public String listProducts(Model model){
        model.addAttribute("products", productService.listAll());
        return "product/list";
    }

    @RequestMapping("/product/show/{id}")
    public String getProduct(@PathVariable Long id, Model model){
        model.addAttribute("product", productService.getById(id));
        return "product/show";
    }

    @RequestMapping("product/edit/{id}")
    public String edit(@PathVariable Long id, Model model){

        final Product byId = productService.getById(id);

        model.addAttribute("productForm", productToProductForm.convert(byId));
        return "product/productform";
    }

    @RequestMapping("/product/new")
    public String newProduct(Model model){
        model.addAttribute("productForm", new ProductForm());
        return "product/productform";
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String saveOrUpdateProduct(@Valid ProductForm productForm, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "product/productform";
        }

        Product savedProduct = productService.saveOrUpdateProductForm(productForm);
        return "redirect:/product/show/" + savedProduct.getId();
    }

    @RequestMapping("/product/delete/{id}")
    public String delete(@PathVariable Long id){
        productService.delete(id);
        return "redirect:/product/list";
    }
}
