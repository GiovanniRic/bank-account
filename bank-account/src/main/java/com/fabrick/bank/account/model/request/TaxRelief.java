package com.fabrick.bank.account.model.request;


public class TaxRelief{
    private String taxReliefId;
    private boolean isCondoUpgrade;
    private String creditorFiscalCode;
    private String beneficiaryType;
    private NaturalPersonBeneficiary naturalPersonBeneficiary;
    private LegalPersonBeneficiary legalPersonBeneficiary;
}
