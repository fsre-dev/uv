package excelExport;

import com.jis.uv.model.Document;
import com.jis.uv.model.Member;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class DocumentExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private Document document;

    public DocumentExcelExporter(Document document) {
        this.document = document;
        workbook = new XSSFWorkbook();
    }

    private void writeDocumentInfoHeader() {
        sheet = workbook.createSheet("Document");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Document ID", style);
        createCell(row, 1, "Description", style);
        createCell(row, 2, "Event", style);
        createCell(row, 3, "Event date", style);
        createCell(row, 4, "Price per member", style);
        createCell(row, 5, "Total price", style);

    }


    private void writeHeaderLine() {
        Row row = sheet.createRow(10);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "User ID", style);
        createCell(row, 1, "E-mail", style);
        createCell(row, 2, "First name", style);
        createCell(row, 3, "Last name", style);
        createCell(row, 4, "Card number", style);
        createCell(row, 5, "Identity card", style);
        createCell(row, 6, "OIB", style);
        createCell(row, 7, "Passport number", style);
        createCell(row, 8, "Birth date", style);
        createCell(row, 9, "City", style);
        createCell(row, 10, "Address", style);
        createCell(row, 11, "Phone number", style);


    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof String) {
            cell.setCellValue((String) value);
        } else {
            cell.setCellValue(value.toString());
        }
        cell.setCellStyle(style);
    }

    private void writeDocumentDataLines() {
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        Row row = sheet.createRow(1);
        int columnCount = 0;

        createCell(row, columnCount++, document.getId(), style);
        createCell(row, columnCount++, document.getDescription(), style);
        createCell(row, columnCount++, document.getEvent(), style);
        createCell(row, columnCount++, document.getEventDate(), style);
        createCell(row, columnCount++, document.getPricePerMember(), style);
        createCell(row, columnCount++, document.getTotalCost(), style);
    }

    private void writeMemberDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Member member : document.getMembers()) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, member.getId(), style);
            createCell(row, columnCount++, member.getEmail(), style);
            createCell(row, columnCount++, member.getFirstName(), style);
            createCell(row, columnCount++, member.getLastName(), style);
            createCell(row, columnCount++, member.getCardNumber(), style);
            createCell(row, columnCount++, member.getIdentityCard(), style);
            createCell(row, columnCount++, member.getOib(), style);
            createCell(row, columnCount++, member.getPassportNumber(), style);
            createCell(row, columnCount++, member.getBirthDate(), style);
            createCell(row, columnCount++, member.getCity(), style);
            createCell(row, columnCount++, member.getAddress(), style);
            createCell(row, columnCount++, member.getPhoneNumber(), style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeDocumentInfoHeader();
        writeDocumentDataLines();
        writeHeaderLine();
        writeMemberDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
