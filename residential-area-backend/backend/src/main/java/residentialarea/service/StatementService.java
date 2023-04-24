package residentialarea.service;

import com.itextpdf.text.DocumentException;

import java.io.IOException;

public interface StatementService {

    byte[] generateStatement(Integer year, Integer month) throws DocumentException, IOException;
}
