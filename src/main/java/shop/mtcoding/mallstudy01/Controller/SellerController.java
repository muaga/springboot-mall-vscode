package shop.mtcoding.mallstudy01.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.mallstudy01.model.Seller;
import shop.mtcoding.mallstudy01.repository.SellerRepository;

@Controller
public class SellerController {

    @Autowired
    private SellerRepository sellerRepository;

    // 판매자목록 페이지
    @GetMapping("/seller")
    public String sellerPage(Model model) {
        List<Seller> sellerList = sellerRepository.findByAll();
        for (Seller seller : sellerList) {
            System.out.println(seller.getName());
            System.out.println(seller.getEmail());
        }
        model.addAttribute("sellerList", sellerList);

        return "seller/seller-list";
    }

    // 판매자등록 페이지
    @GetMapping("/seller/savePage")
    public String sellerSavePage() {
        return "seller/seller";
    }

    // 판매자등록 기능
    @PostMapping("/seller/save")
    public String sellerSave(Seller seller) {
        Seller seller1 = sellerRepository.findByEmail(seller.getEmail());
        // 계속 안되었던 이유는... Seller seller1를 안해서...
        if (seller1 == null) {
            sellerRepository.save(seller);
            return "redirect:/seller";
        } else {
            return "redirect:/exist";
        }
    }

    // 판매자 상세 페이지
    @GetMapping("/seller/{id}")
    public String sellerDetailPage(@PathVariable Integer id, Model model) {
        Seller seller = sellerRepository.findById(id);
        model.addAttribute("seller", seller);
        return "seller/seller-detail";
    }

    // 판매자 수정 기능
    @PostMapping("/seller/update")
    public String sellerUpdate(Seller seller) {
        sellerRepository.update(seller);
        return "redirect:/seller";
    }

    // 판매자 삭제 기능
    @PostMapping("/seller/delete")
    public String sellerDelete(Integer id) {
        sellerRepository.delete(id);
        return "redirect:/seller";
    }
}
