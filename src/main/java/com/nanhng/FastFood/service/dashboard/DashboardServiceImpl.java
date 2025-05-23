package com.nanhng.FastFood.service.dashboard;

import com.nanhng.FastFood.dto.constant.RoleType;
import com.nanhng.FastFood.dto.response.dashboard.OrderCountRes;
import com.nanhng.FastFood.dto.response.dashboard.OrderRevenueRes;
import com.nanhng.FastFood.dto.response.dashboard.ProductRevenueRes;
import com.nanhng.FastFood.entity.user.User;
import com.nanhng.FastFood.exception.LovelyException;
import com.nanhng.FastFood.other_service.excel.ExcelService;
import com.nanhng.FastFood.repository.order.OrderRepository;
import com.nanhng.FastFood.repository.orderItem.OrderItemRepository;
import com.nanhng.FastFood.service.BaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DashboardServiceImpl extends BaseService implements DashboardService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public List<OrderRevenueRes> calculateTotalRevenueByMonth() {
        User user = getUser(RoleType.ADMIN);

        return orderRepository.calculateRevenueByMonth();
    }

    @Override
    public List<ProductRevenueRes> calculateProductsRevenueByMonth() {
        User user = getUser(RoleType.ADMIN);

        return orderItemRepository.calculateRevenueByMonth();
    }

    @Override
    public List<OrderCountRes> countOrderByMonth() {
        User user = getUser(RoleType.ADMIN);

        return orderRepository.countNumberOrderByMonth();
    }

    @Override
    public InputStream excelTotalRevenueByMonth() {
        User user = getUser(RoleType.ADMIN);

        List<OrderRevenueRes> list = orderRepository.calculateRevenueByMonth();
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet =workbook.createSheet("Revenue");
            CellStyle style = MyCellStyle(workbook);
            sheet.setDefaultColumnWidth(30);
            Row row = sheet.createRow(0);
            ExcelService.createCell(row, "STT",0, style);

            ExcelService.createCell(row, "Tháng",1, style);

            ExcelService.createCell(row, "Doanh thu",2, style);

            for(int i = 0; i < list.size(); i++) {
                row = sheet.createRow(i+1);

                ExcelService.createCell(row, String.valueOf(i+1),0, style);
                ExcelService.createCell(row, list.get(i).getDate().toString(),1, style);
                ExcelService.createCell(row, list.get(i).getRevenue().toString(),1, style);
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            workbook.close();
            return new ByteArrayInputStream(out.toByteArray());

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new LovelyException("can not create excel file");
        }
    }

    @Override
    public InputStream excelProductByMonth() {
        User user = getUser(RoleType.ADMIN);

        List<ProductRevenueRes> list = orderItemRepository.calculateRevenueByMonth();

        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet =workbook.createSheet("Revenue");
            CellStyle style = MyCellStyle(workbook);
            sheet.setDefaultColumnWidth(30);
            Row row = sheet.createRow(0);
            ExcelService.createCell(row, "STT",0, style);

            ExcelService.createCell(row, "Tháng",1, style);

            ExcelService.createCell(row, "Sản phẩm",2, style);

            ExcelService.createCell(row, "Doanh thu",3, style);

            for(int i = 0; i < list.size(); i++) {
                row = sheet.createRow(i+1);

                ExcelService.createCell(row, String.valueOf(i+1),0, style);
                ExcelService.createCell(row, list.get(i).getDate().toString(),1, style);
                ExcelService.createCell(row, list.get(i).getProductName(),2, style);
                ExcelService.createCell(row, list.get(i).getRevenue().toString(),3, style);
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            workbook.close();
            return new ByteArrayInputStream(out.toByteArray());

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new LovelyException("can not create excel file");
        }
    }

    private CellStyle MyCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        // Set black borders
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        Font font = workbook.createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);
        style.setDataFormat(workbook.createDataFormat().getFormat("@"));
        return style;
    }

}
