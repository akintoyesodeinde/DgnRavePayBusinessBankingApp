package business.banking.dgnravepay.wallet.controller;



import business.banking.dgnravepay.wallet.enums.IndustrySubCategory;
import business.banking.dgnravepay.wallet.enums.Occupation;
import business.banking.dgnravepay.wallet.service.IndustryCatalog;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/industry")
@RequiredArgsConstructor
public class OccupationController {

    private final IndustryCatalog catalog;

    @GetMapping("/occupations/{subCategory}")
    public List<Occupation> occupationsBySubCategory(
            @PathVariable IndustrySubCategory subCategory) {

        return catalog.getOccupations(subCategory);
    }
}
