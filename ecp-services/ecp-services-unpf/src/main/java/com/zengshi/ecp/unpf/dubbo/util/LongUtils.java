package com.zengshi.ecp.unpf.dubbo.util;

public class LongUtils {
    
    /**
     * is empty Long.
     * 
     * @param str source Long.
     * @return is empty.
     */
    public static boolean isEmpty(Long lo)
    {
        if( lo == null || lo == 0 )
            return true;
        return false;
    }
    /**
     * isnot empty Long.
     * 
     * @param str source Long.
     * @return is empty.
     */
    public static boolean isNotEmpty(Long lo)
    {
        if( lo != null && lo != 0 )
            return true;
        return false;
    }    

}

