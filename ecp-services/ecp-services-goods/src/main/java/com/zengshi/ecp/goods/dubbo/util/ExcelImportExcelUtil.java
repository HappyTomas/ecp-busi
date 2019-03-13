package com.zengshi.ecp.goods.dubbo.util;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.zengshi.ecp.goods.dubbo.dto.excelImp.ExcelImportGdsModelDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;

import net.sf.json.JSONObject;

public class ExcelImportExcelUtil {

    private static final Log log = LogFactory.getLog(ExcelImportExcelUtil.class);

    private static final String MODULE = ExcelImportExcelUtil.class.getName();

    public static Object getCellVal(Cell cell) {

        if (cell == null)
            return null;
        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {

            return cell.getStringCellValue();
        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return cell.getBooleanCellValue();

        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return cell.getNumericCellValue();

        } else {
            return null;
        }

    }

    public static String getHeaderName(Sheet sheet, int colIndex) {
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(colIndex);
        return cell.getStringCellValue();
    }

    public static void fitGdsInfo(String headerName, Object val, ExcelImportGdsModelDTO gdsModel) throws Exception {
        // 如果输入的数据不合法，则捕获异常，不设置值。后续必传的在后端校验

        if ("商品名称".equals(headerName)) {
            if (val != null) {
                gdsModel.setGdsName((String) val);
            }

        } else if ("平台分类编码".equals(headerName)) {
            try {
                Long tempCatg = ((Double) Double.parseDouble(val.toString())).longValue();
                gdsModel.setCatgCode(tempCatg.toString());

            } catch (Exception e) {

            }
        } else if ("商品编码".equals(headerName)) {
            try {
                Long tempGdsId = ((Double) Double.parseDouble(val.toString())).longValue();
                gdsModel.setGdsId(tempGdsId);

            } catch (Exception e) {

            }
        } else if ("店铺编码".equals(headerName)) {
            try {
                gdsModel.setShopId(((Double) Double.parseDouble(val.toString())).longValue());
            } catch (Exception e) {

            }
        } else if ("产品类型".equals(headerName)) {
            try {
                gdsModel.setGdsType(((Double) Double.parseDouble(val.toString())).longValue());
            } catch (Exception e) {

            }
        } else if ("产品副标题".equals(headerName)) {
            if (val != null) {
                gdsModel.setGdsTitle(val.toString());
            }
        } else if ("产品参考价".equals(headerName)) {
            try {
                gdsModel.setGdsPrice(((Double) (100 * Double.parseDouble(val.toString()))).longValue());
            } catch (Exception e) {

            }
        } else if ("库存".equals(headerName)) {
            try {
                gdsModel.setGdsStock(((Double) (Double.parseDouble(val.toString()))).longValue());
            } catch (Exception e) {

            }
        } else if ("产品详情".equals(headerName)) {
            if (val != null) {
                gdsModel.setGdsDetailDesc(val.toString());
            }
        } else if ("包装清单".equals(headerName)) {
            if (val != null) {
                gdsModel.setGdsPackDesc(val.toString());
            }
        }

    }

    /**
     * 
     * importGdsExcelFile:(解析excel获取数据). <br/>
     * optType 0:导入 1：编辑
     * 
     * @author zjh
     * @param stream
     * @param fileType
     * @param optType
     * @return
     * @since JDK 1.6
     */
    public static List<ExcelImportGdsModelDTO> importGdsExcelFile(InputStream stream, String fileType, String optType) {
        List<ExcelImportGdsModelDTO> excelImportGdsModelVOs = new ArrayList<ExcelImportGdsModelDTO>();
        Sheet sheet;
        Workbook book;
        try {

            if (fileType.equals("xls")) {
                book = new HSSFWorkbook(stream);
            } else if (fileType.equals("xlsx")) {
                book = new XSSFWorkbook(stream);
            } else {
                LogUtil.error(MODULE, "您输入的excel格式不正确");
                stream.close();
                return excelImportGdsModelVOs;
            }
            sheet = book.getSheetAt(0);

            int i = 1;
            while (true) {
                ExcelImportGdsModelDTO excelImportGdsModel = null;
                Row row = sheet.getRow(i);
                // 如果读取的数据为空
                if (row == null) {
                    ExcelImportGdsModelDTO lastImportGdsModel = excelImportGdsModelVOs.get(excelImportGdsModelVOs.size() - 1);
                    lastImportGdsModel.setLastRowIdx(i - 1);
                    break;
                }

                Cell firstCell = sheet.getRow(i).getCell(0);
                if (firstCell != null && getCellVal(firstCell) != null) {
                    // // 如果第一行有值新增的一个商品对象
                    excelImportGdsModel = new ExcelImportGdsModelDTO();
                    excelImportGdsModel.setFirstRowIdx(firstCell.getRowIndex());
                    excelImportGdsModel.setLastRowIdx(firstCell.getRowIndex());
                    // 获取每一行的单元格
                    int j = 0;

                    while (true) {

                        Cell cell = sheet.getRow(i).getCell(j);
                        // 如果cell为空则换下一行
                        if (cell == null){
                            break;
                        }
                        // 如果该行第一个元素不为空，则
                        String headerName = getHeaderName(sheet, cell.getColumnIndex());
                        Object val;
                        val = getCellVal(cell);

                        if (!"属性值".equals(headerName) && !"属性名称".equals(headerName)) {

                            if (val != null) {
                                // 如果值不为null,则设置临时对象的值
                                fitGdsInfo(headerName, val, excelImportGdsModel);
                            }
                        }
                        j++;
                    }
                    if (excelImportGdsModelVOs.size() > 0) {
                        ExcelImportGdsModelDTO importGdsModelPre = excelImportGdsModelVOs.get(excelImportGdsModelVOs.size() - 1);
                        if (excelImportGdsModel.getFirstRowIdx() - importGdsModelPre.getFirstRowIdx() > 1) {
                            importGdsModelPre.setLastRowIdx(excelImportGdsModel.getFirstRowIdx() - 1);
                        }
                    }
                    excelImportGdsModelVOs.add(excelImportGdsModel);
                }
                i++;

            }
            // 对属性进行加工
            for (ExcelImportGdsModelDTO gdsModel : excelImportGdsModelVOs) {
                Map<String, String> propInfos = new HashMap<String, String>();
                for (int k = gdsModel.getFirstRowIdx(); k <= gdsModel.getLastRowIdx(); k++) {

                    Cell propNCell;
                    Cell propVCell;
                    if ("0".equals(optType)) {
                        // propName取属性值前一个cell的属性名
                        propNCell = sheet.getRow(k).getCell(7);
                        propVCell = sheet.getRow(k).getCell(8);
                    } else {
                        propNCell = sheet.getRow(k).getCell(3);
                        propVCell = sheet.getRow(k).getCell(5);
                    }
                    String propId;
                    if (getCellVal(propNCell) == null) {
                        propId = null;
                    } else {
                        propId = getCellVal(propNCell).toString();

                        try {
                            // 去除数字的小数位
                            boolean isNum = propId.toString().matches("[0-9]+");
                            if (!isNum) {
                                DecimalFormat df = new DecimalFormat("0");
                                propId = df.format(getCellVal(propNCell));
                            }
                        } catch (Exception e) {
                            // TODO: handle exception

                        }
                    }

                    String propVal;
                    if (getCellVal(propVCell) == null) {
                        propVal = null;
                    } else {
                        propVal = getCellVal(propVCell).toString();
                    }

                    propInfos.put(propId, propVal);
                }
                gdsModel.setPropInfos(propInfos);
                // if()
                // log.info(JSONObject.fromObject(gdsModel).toString());
            }
            book.close();

        } catch (Exception e) {
            log.error("gds excel import failded!", e);
        }
        return excelImportGdsModelVOs;
    }

}
