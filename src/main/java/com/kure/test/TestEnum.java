package com.kure.test;

public enum TestEnum {

        SPOT("即期到期交割"),
        SPOTDELAY("即期延迟交割"),
        FWD("远期到期交割"),
        SWAP("掉期到期交割"),
        OPTEE("期权费到期交割"),
        OPTCASHFLOW("期权现金流到期交割"),
        IRSCASHFLOW("利率掉期现金流到期交割"),
        CCSCASHFLOW("货币掉期现金流到期交割"),
        CCSPRINCIPAL("货币掉期本金到期交割");

        TestEnum(String batchTypeName) {
            this.batchTypeName = batchTypeName;
        }

        private String batchTypeName;

        public String getBatchTypeName() {
            return batchTypeName;
        }

        public void setBatchTypeName(String batchTypeName) {
            this.batchTypeName = batchTypeName;
        }
}
