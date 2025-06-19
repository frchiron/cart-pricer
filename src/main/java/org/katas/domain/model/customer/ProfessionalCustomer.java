package org.katas.domain.model.customer;

import org.katas.domain.model.Amount;
import org.katas.domain.model.Currency;
import org.katas.domain.model.pricingstrategy.DefaultProfessionalCustomerProductPricingStrategy;
import org.katas.domain.model.pricingstrategy.ProductPricingStrategy;
import org.katas.domain.model.pricingstrategy.ProfessionalCustomerWithHighRevenuePricingStrategy;

import static org.katas.domain.model.customer.VatNumber.NOT_SET;

public class ProfessionalCustomer extends Customer {
    public static final Amount HIGH_REVENUE_MINIMUM_LIMIT = Amount.of(10000000L, Currency.EURO);
    private final Amount annualRevenue;
    private final VatNumber vatNumber;
    private final String companyName;
    private final Siren siren;

    public static ProfessionalCustomer of(CustomerId customerId, String companyName, VatNumber vatNumber, Siren siren, Amount annualRevenue) {
        return new ProfessionalCustomer(customerId, annualRevenue,companyName,siren,vatNumber);
    }

    public static ProfessionalCustomer of(CustomerId customerId, String companyName, Siren siren, Amount annualRevenue) {
        return new ProfessionalCustomer(customerId, annualRevenue, companyName, siren, NOT_SET);
    }

    private ProfessionalCustomer(CustomerId customerId, Amount annualRevenue, String companyName, Siren siren, VatNumber vatNumber) {
        super(customerId);
        this.annualRevenue = annualRevenue;
        this.siren = siren;
        this.vatNumber = vatNumber;
        this.companyName = companyName;
    }

    @Override
    public ProductPricingStrategy getProductPricingStrategy() {
        if (annualRevenue.isGreaterThan(HIGH_REVENUE_MINIMUM_LIMIT)) {
            return new ProfessionalCustomerWithHighRevenuePricingStrategy();
        }
        return new DefaultProfessionalCustomerProductPricingStrategy();
    }
}
