package residentialarea.service;

import com.itextpdf.text.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import residentialarea.dao.ResidentDao;
import residentialarea.dao.StatementDao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.itextpdf.text.pdf.PdfWriter;
@Slf4j
@Service
@RequiredArgsConstructor
public class StatementServiceImpl implements StatementService{

    private final StatementDao statementDao;
    private final ResidentDao residentDao;

    @Override
    @Transactional(readOnly = true)
    public byte[] generateStatement(Integer year, Integer month) throws DocumentException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);
        document.open();
        document.add(new Paragraph("This is an example PDF."));
        Image image = Image.getInstance("example.png");
        image.setAlignment(Element.ALIGN_CENTER);
        document.add(image);
        document.close();
        return baos.toByteArray();
    }
}
