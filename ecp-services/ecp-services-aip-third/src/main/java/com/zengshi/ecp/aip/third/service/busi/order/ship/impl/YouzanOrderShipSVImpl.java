package com.zengshi.ecp.aip.third.service.busi.order.ship.impl;

import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderShipReqDTO;
import com.zengshi.ecp.aip.third.model.youzan.ErrorMessage;
import com.zengshi.ecp.aip.third.service.busi.client.youzan.KdtApiClient;
import com.zengshi.ecp.aip.third.service.busi.order.ship.interfaces.IOrderShipSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 有赞发货
 * 接口文档：http://open.youzan.com/apilist/detail/logistics?name=kdt.logistics.online.confirm
 */
public class YouzanOrderShipSVImpl implements IOrderShipSV {

    /**
     * 卖家发货
     */
    private static final String DELIVER = "kdt.logistics.online.confirm";

    @Override
    public Map<String, Object> ship(OrderShipReqDTO req) throws BusinessException {
        LogUtil.info("YouzanOrderShipSVImpl", "tradeId:" + req.getTradeId() + "\noutSid:" + req.getOutSid() + "\ncompanyCode:" + req.getCompanyCode() + "\nsubTid:" + req.getSubTid());
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tid", req.getTradeId());
        //快递单号
        params.put("out_sid", req.getOutSid());
        //物流公司code
        params.put("out_stype", req.getCompanyCode());
        //如果需要拆单发货，使用该字段指定要发货的交易明细的编号，多个明细编号用半角逗号“,”分隔； 不需要拆单发货，则改字段不传或值为空；
        if (StringUtil.isNotBlank(req.getSubTid())) {
            params.put("oids", req.getSubTid());
        }

        try {
            KdtApiClient client = new KdtApiClient(req.getAppkey(), req.getAppscret());
            HttpResponse response = client.get(DELIVER, params);
            if (200 == response.getStatusLine().getStatusCode()) {
                String content = getResponseContent(response);
                LogUtil.info("YouzanOrderShipSVImpl", "youzan ship response:" + content);
                if (StringUtil.isBlank(content) || content.contains("error_response")) {
                    ErrorMessage errorMessage = JSON.parseObject(content, ErrorMessage.class);
                    return getResult(false, errorMessage);
                }
                return getResult(true, null);
            }
            LogUtil.error(this.getClass().getName(), "有赞接口请求返回失败");

        } catch (Exception e) {
            LogUtil.error(this.getClass().getName(), "请求有赞服务接口异常", e);

        }
        return getResult(false, null);
    }

    private Map<String, Object> getResult(boolean result, ErrorMessage errorMessage) {
        Map<String, Object> map = new HashMap<>();
        map.put("result", result);
        if (errorMessage != null) {
            map.put("msg", errorMessage.getErrorResponse().getMsg());
        }
        return map;
    }

    private String getResponseContent(HttpResponse response) {
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = response.getEntity().getContent();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder content = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
            }
            return content.toString();
        } catch (Exception e) {
            LogUtil.error(this.getClass().getName(), "获取接口数据异常", e);
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
                if (bufferedReader != null)
                    bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
