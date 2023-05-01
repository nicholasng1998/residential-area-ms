package residentialarea.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xhtmlrenderer.pdf.ITextRenderer;
import residentialarea.bean.ResidentBean;
import residentialarea.bean.ResidentCredentialBean;
import residentialarea.bean.StatementBean;
import residentialarea.constant.StatementStatusEnum;
import residentialarea.dao.ResidentCredentialDao;
import residentialarea.dao.ResidentDao;
import residentialarea.dao.StatementDao;
import residentialarea.model.MonthlyStatementModel;
import residentialarea.model.StatementResponseModel;
import residentialarea.util.PdfConfig;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatementServiceImpl implements StatementService{

    private final StatementDao statementDao;
    private final ResidentDao residentDao;
    private final PdfConfig pdfConfig;
    private final ResidentCredentialDao residentCredentialDao;

    @Override
    @Transactional(readOnly = true)
    public Resource generateStatement(Integer year, Integer month) {
        log.info("generateStatement");
        Configuration cfg = pdfConfig.getFreeMarkerConfiguration();
        Resource resource = null;

        try {
            Template template = cfg.getTemplate("monthly-statement.ftl");
            Map<String, List<MonthlyStatementModel>> data = new HashMap<>();
            List<StatementBean> completeStatements = statementDao.findAllByStatusAndYearAndMonth(StatementStatusEnum.COMPLETE.getStatus(), year, month);
            List<StatementBean> pendingStatements = statementDao.findAllByStatusAndYearAndMonth(StatementStatusEnum.PENDING.getStatus(), year, month);

            List<MonthlyStatementModel> completeStatementsModels = new ArrayList<>();
            completeStatements.forEach(statementBean -> {
                ResidentBean residentBean = residentDao.getOne(statementBean.getResidentId());
                MonthlyStatementModel monthlyStatementModel = new MonthlyStatementModel(
                        statementBean.getResidentId(),
                        residentBean.getName(),
                        residentBean.getEmail(),
                        residentBean.getPhoneNo(),
                        residentBean.getUnitNo()
                );
                completeStatementsModels.add(monthlyStatementModel);
            });
            data.put("complete", completeStatementsModels);

            List<MonthlyStatementModel> pendingStatementsModels = new ArrayList<>();
            pendingStatements.forEach(statementBean -> {
                ResidentBean residentBean = residentDao.getOne(statementBean.getResidentId());
                MonthlyStatementModel monthlyStatementModel = new MonthlyStatementModel(
                        statementBean.getResidentId(),
                        residentBean.getName(),
                        residentBean.getEmail(),
                        residentBean.getPhoneNo(),
                        residentBean.getUnitNo()
                );
                pendingStatementsModels.add(monthlyStatementModel);
            });
            data.put("pending", pendingStatementsModels);

            StringWriter stringWriter = new StringWriter();
            template.process(data, stringWriter);
            String htmlContent = stringWriter.toString();

            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                ITextRenderer renderer = new ITextRenderer();
                renderer.setDocumentFromString(htmlContent);
                renderer.layout();
                renderer.createPDF(outputStream);
                renderer.finishPDF();
                resource = new ByteArrayResource(outputStream.toByteArray());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resource;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StatementResponseModel> getStatementByUsername(String username) {
        log.info("StatementServiceImpl#getStatementByUsername");
        ResidentCredentialBean residentCredentialBean = residentCredentialDao.findByUsername(username);
        List<StatementBean> statementBeans = statementDao.findAllByStatusAndResidentId(StatementStatusEnum.PENDING.getStatus(), residentCredentialBean.getResidentId());
        Pageable pageable = PageRequest.of(0, 999);
        List<StatementResponseModel> statementResponseModels = new ArrayList<>();
        statementBeans.forEach(statementBean -> {
            StatementResponseModel statementResponseModel = new StatementResponseModel();
            BeanUtils.copyProperties(statementBean, statementResponseModel);
            statementResponseModels.add(statementResponseModel);
        });
        log.info("statementResponseModels: " + statementResponseModels);
        return new PageImpl<>(statementResponseModels, pageable, statementDao.findAllByStatusAndResidentId(StatementStatusEnum.PENDING.getStatus(), residentCredentialBean.getResidentId()).size());
    }
}
