package com.Banking.Retail_Banking.utils;

import static com.Banking.Retail_Banking.constants.constants.ACCOUNT_NUMBER_PATTERN_STRING;
import static com.Banking.Retail_Banking.constants.constants.SORT_CODE_PATTERN_STRING;

import com.mifmif.common.regex.Generex;

public class CodeGenerator {
    Generex sortCodeGenerex = new Generex(SORT_CODE_PATTERN_STRING);
    Generex accountNumberGenerex = new Generex(ACCOUNT_NUMBER_PATTERN_STRING);

    public CodeGenerator(){}

    public String generateSortCode() {
        return sortCodeGenerex.random();
    }

    public String generateAccountNumber() {
        return accountNumberGenerex.random();
    }
}
