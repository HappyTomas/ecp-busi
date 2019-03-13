package com.zengshi.ecp.prom.dubbo.impl.converter;

public abstract interface Converter<SOURCE, TARGET> extends
        org.springframework.core.convert.converter.Converter<SOURCE, TARGET> {
    public abstract TARGET convert(SOURCE paramSOURCE) throws ConversionException;

    public abstract TARGET convert(SOURCE paramSOURCE, TARGET paramTARGET)
            throws ConversionException;
}
