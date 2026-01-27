package business.banking.dgnravepay.wallet.service;


import business.banking.dgnravepay.wallet.enums.IndustryCategory;
import business.banking.dgnravepay.wallet.enums.IndustrySubCategory;
import business.banking.dgnravepay.wallet.enums.Occupation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IndustryCatalog {

    public List<IndustrySubCategory> getSubCategories(IndustryCategory category) {

        return switch (category) {

            case AGRICULTURE -> List.of(
                    IndustrySubCategory.CROP_PRODUCTION,
                    IndustrySubCategory.LIVESTOCK,
                    IndustrySubCategory.FORESTRY,
                    IndustrySubCategory.FISHING_AQUACULTURE
            );

            case MINING -> List.of(
                    IndustrySubCategory.CRUDE_PETROLEUM_GAS,
                    IndustrySubCategory.COAL_MINING,
                    IndustrySubCategory.METAL_ORES,
                    IndustrySubCategory.QUARRYING
            );

            case MANUFACTURING -> List.of(
                    IndustrySubCategory.FOOD_BEVERAGES,
                    IndustrySubCategory.TEXTILES,
                    IndustrySubCategory.APPAREL,
                    IndustrySubCategory.ELECTRICAL_ELECTRONICS,
                    IndustrySubCategory.MOTOR_VEHICLES,
                    IndustrySubCategory.WOOD_PRODUCTS,
                    IndustrySubCategory.PULP_PAPER,
                    IndustrySubCategory.CHEMICAL_PHARMACEUTICALS,
                    IndustrySubCategory.NON_METALLIC_MINERALS,
                    IndustrySubCategory.BASIC_METALS,
                    IndustrySubCategory.PLASTIC_RUBBER
            );

            case CONSTRUCTION -> List.of(
                    IndustrySubCategory.BUILDING_CONSTRUCTION,
                    IndustrySubCategory.CIVIL_ENGINEERING,
                    IndustrySubCategory.SPECIALIZED_CONSTRUCTION
            );

            case UTILITIES -> List.of(
                    IndustrySubCategory.ELECTRICITY,
                    IndustrySubCategory.GAS,
                    IndustrySubCategory.WATER_SUPPLY,
                    IndustrySubCategory.SEWERAGE_WASTE,
                    IndustrySubCategory.STEAM_AIR_CONDITIONING
            );

            case INFORMATION_COMMUNICATION -> List.of(
                    IndustrySubCategory.TELECOMMUNICATIONS,
                    IndustrySubCategory.BROADCASTING,
                    IndustrySubCategory.IT_SERVICES,
                    IndustrySubCategory.MOTION_PICTURES_MUSIC,
                    IndustrySubCategory.PUBLISHING
            );


            case FINANCIAL_INSURANCE -> List.of(
                    IndustrySubCategory.COMMERCIAL_BANKING,
                    IndustrySubCategory.MICROFINANCE,
                    IndustrySubCategory.INSURANCE,
                    IndustrySubCategory. PENSION,
                    IndustrySubCategory.CAPITAL_MARKET
            );

            case TRADE -> List.of(
                    IndustrySubCategory.WHOLESALE,
                    IndustrySubCategory.VEHICLE_REPAIR,
                    IndustrySubCategory.RETAIL
            );


            case REAL_ESTATE -> List.of(
                    IndustrySubCategory.FACILITY_MANAGEMENT,
                    IndustrySubCategory.REAL_ESTATE_AGENCY,
                    IndustrySubCategory.PROPERTY_DEVELOPMENT
            );

            case  PROFESSIONAL_TECHNICAL -> List.of(
                    IndustrySubCategory.ACCOUNTING,
                    IndustrySubCategory.ARCHITECTURE_ENGINEERING,
                    IndustrySubCategory.ADVERTISING,
                    IndustrySubCategory.VETERINARY
            );


            case TRANSPORT_STORAGE -> List.of(
                    IndustrySubCategory.ROAD_TRANSPORT,
                    IndustrySubCategory.WAREHOUSING,
                    IndustrySubCategory.AIR_TRANSPORT,
                    IndustrySubCategory.WATER_TRANSPORT
            );

            case   HOSPITALITY_TOURISM -> List.of(
                    IndustrySubCategory.ACCOMMODATION,
                    IndustrySubCategory.FOOD_BEVERAGE,
                    IndustrySubCategory.TRAVEL_AGENCY
            );


            case EDUCATION_HEALTH -> List.of(
                    IndustrySubCategory.DIAGNOSTIC,
                    IndustrySubCategory.HOSPITALS,
                    IndustrySubCategory.SCHOOLS,
                    IndustrySubCategory.SOCIAL_WORK
            );

            case    ARTS_RECREATION -> List.of(
                    IndustrySubCategory.GAMBLING,
                    IndustrySubCategory.CREATIVE_ARTS,
                    IndustrySubCategory.SPORTS_AMUSEMENT
            );



            case ADMINISTRATIVE_SUPPORT -> List.of(
                    IndustrySubCategory.RENTAL_LEASING,
                    IndustrySubCategory.EMPLOYMENT_AGENCY,
                    IndustrySubCategory.SECURITY,
                    IndustrySubCategory.OFFICE_SUPPORT
            );


            default -> List.of();
        };
    }



    public List<Occupation> getOccupations(IndustrySubCategory subCategory) {

        return switch (subCategory) {

            case CROP_PRODUCTION -> List.of(
                    Occupation.FARMER,
                    Occupation.AGRONOMIST,
                    Occupation.CROP_SCIENTIST,
                    Occupation.AGRICULTURAL_TECHNICIAN
            );

            case LIVESTOCK -> List.of(
                    Occupation.LIVESTOCK_FARMER,
                    Occupation.VETERINARY_TECHNICIAN,
                    Occupation.ANIMAL_BREEDER,
                    Occupation.POULTRY_FARMER
            );

            case CRUDE_PETROLEUM_GAS -> List.of(
                    Occupation.PETROLEUM_ENGINEER,
                    Occupation.DRILLING_ENGINEER,
                    Occupation.RIG_TECHNICIAN,
                    Occupation.OIL_GAS_OPERATOR
            );

            case IT_SERVICES -> List.of(
                    Occupation.SOFTWARE_ENGINEER,
                    Occupation.BACKEND_DEVELOPER,
                    Occupation.FRONTEND_DEVELOPER,
                    Occupation.MOBILE_APP_DEVELOPER,
                    Occupation.DEVOPS_ENGINEER,
                    Occupation.CLOUD_ENGINEER,
                    Occupation.DATA_ENGINEER,
                    Occupation.DATA_SCIENTIST,
                    Occupation.CYBERSECURITY_ANALYST
            );

            case COMMERCIAL_BANKING -> List.of(
                    Occupation.BANKER,
                    Occupation.CREDIT_ANALYST,
                    Occupation.LOAN_OFFICER,
                    Occupation.COMPLIANCE_OFFICER,
                    Occupation.RISK_ANALYST
            );

            case HOSPITALS -> List.of(
                    Occupation.MEDICAL_DOCTOR,
                    Occupation.NURSE,
                    Occupation.PHARMACIST,
                    Occupation.LAB_TECHNICIAN,
                    Occupation.RADIOGRAPHER
            );

            default -> List.of();
        };
    }
}
