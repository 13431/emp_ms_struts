package com.nf.empst.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommonUtilTest {
    @Test
    public void notallempty() throws Exception {
        assert CommonUtil.notallempty("", "skdfjsk", null);
        assert CommonUtil.notallempty("sdf", "ksdfj", "ksdf");
        assert !CommonUtil.notallempty("", "", null);
        assert !CommonUtil.notallempty(null);
        assert !CommonUtil.notallempty("");
    }

}