package shop.mtcoding.mallstudy01.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shop.mtcoding.mallstudy01.model.Product;
import shop.mtcoding.mallstudy01.model.Seller;
import shop.mtcoding.mallstudy01.repository.ProductRepository;
import shop.mtcoding.mallstudy01.repository.SellerRepository;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SellerRepository sellerRepository;

    // 홈페이지
    // 상품목록 페이지
    @GetMapping("/")
    public String homePage(Model model) {
        List<Product> productList = productRepository.findByAll();
        for (Product product : productList) {
            System.out.println(product.getName());
            System.out.println(product.getPrice());
            System.out.println(product.getQty());
        }
        model.addAttribute("productList", productList);
        return "home";
    }

    // 상품등록 페이지
    @GetMapping("/product/savePage")
    public String productSavePage() {
        return "product/product";
    }

    // 상품등록 기능
    @PostMapping("/product/save")
    public String productSave(Product product, @RequestParam("sellerId") Integer sellerId) {
        try {
            Seller seller = sellerRepository.findById(sellerId);
            if (seller != null)
                product.setSeller(seller);
            if (product.getName() == null || product.getName().isEmpty()) {
                return "redirect:/ex40x";
            }
            if (product.getPrice() == null || product.getPrice().equals(null)) {
                return "redirect:/ex40x";
            }
            if (product.getQty() == null || product.getQty().equals(null)) {
                return "redirect:/ex40x";
            }
            productRepository.save(product);
            return "redirect:/";
        } catch (Exception e) {
            return "redirect:/null";
        }
    }

    // 상품 상세 페이지
    @GetMapping("/product/{id}")
    public String productDetailPage(@PathVariable Integer id, Model model) {
        Product product = productRepository.findById(id);
        model.addAttribute("product", product);
        return "product/product-detail";
    }

    // 상품수정 기능
    @PostMapping("/product/update")
    public String productUpdate(Product product, @RequestParam("sellerId") Integer sellerId) {
        try {
            Seller seller = sellerRepository.findById(sellerId);
            if (seller != null)
                product.setSeller(seller);
            if (product.getName() == null || product.getName().isEmpty()) {
                return "redirect:/ex40x";
            }
            if (product.getPrice() == null || product.getPrice().equals(null)) {
                return "redirect:/ex40x";
            }
            if (product.getQty() == null || product.getQty().equals(null)) {
                return "redirect:/ex40x";
            }
            productRepository.update(product);
            return "redirect:/";
        } catch (Exception e) {
            return "redirect:/";
        }
    }

    // 상품삭제 기능
    @PostMapping("/product/delete")
    public String productDelete(Integer id) {
        productRepository.delete(id);
        return "redirect:/";
    }

    // 판매자별 상품 조회 페이지
    @GetMapping("/seller/product/{id}")
    public String sellerToProductPage(@PathVariable Integer id, Model model) {
        List<Product> sellerToProductList = productRepository.findByIdJoinSeller(id);
        model.addAttribute("sellerToProductList", sellerToProductList);
        return "seller-to-product";
    }

}
