package com.fabrick.bank.account.model.request;



public class LegalPersonBeneficiary {
	
    private String fiscalCode;
    private String legalRepresentativeFiscalCode;
    
    public LegalPersonBeneficiary() {
    	
    }

	public String getFiscalCode() {
		return fiscalCode;
	}

	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}

	public String getLegalRepresentativeFiscalCode() {
		return legalRepresentativeFiscalCode;
	}

	public void setLegalRepresentativeFiscalCode(String legalRepresentativeFiscalCode) {
		this.legalRepresentativeFiscalCode = legalRepresentativeFiscalCode;
	}
}
