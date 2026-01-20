package business.banking.dgnravepay.wallet.controller;


import business.banking.dgnravepay.wallet.enums.IndustryCategory;
import business.banking.dgnravepay.wallet.enums.IndustrySubCategory;
import business.banking.dgnravepay.wallet.service.IndustryCatalog;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/industry")
@RequiredArgsConstructor
public class IndustryController {

    private final IndustryCatalog catalog;

    @GetMapping("/categories")
    public IndustryCategory[] categories() {
        return IndustryCategory.values();
    }

    @GetMapping("/sub-categories/{category}")
    public List<IndustrySubCategory> subCategories(
            @PathVariable IndustryCategory category) {
        return catalog.getSubCategories(category);
    }
}
