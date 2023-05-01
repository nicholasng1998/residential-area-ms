package residentialarea.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import residentialarea.bean.*;
import residentialarea.constant.PaymentStatusEnum;
import residentialarea.dao.PaymentDao;
import residentialarea.dao.ResidentCredentialDao;
import residentialarea.dao.ResidentDao;
import residentialarea.dao.StatementDao;
import residentialarea.model.CreatePaymentRequestModel;
import residentialarea.model.PaymentResponseModel;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final PaymentDao paymentDao;
    private final StatementDao statementDao;
    private final ResidentCredentialDao residentCredentialDao;
    private final ResidentDao residentDao;

    @Override
    @Transactional(readOnly = true)
    public Page<PaymentResponseModel> readPayments(int pageSize, int pageNumber) {
        log.info("readPayments");
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<PaymentBean> paymentBeans = paymentDao.findAll(pageable);
        log.info("paymentBeans: " + paymentBeans);
        List<PaymentResponseModel> paymentResponseModels = new ArrayList<>();
        paymentBeans.getContent().forEach((paymentBean -> {
            PaymentResponseModel paymentResponseModel = new PaymentResponseModel();
            BeanUtils.copyProperties(paymentBean, paymentResponseModel);
            paymentResponseModels.add(paymentResponseModel);
        }));
        return new PageImpl<>(paymentResponseModels, pageable, paymentDao.findAll().size());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void complete(int id) {
        PaymentBean paymentBean = paymentDao.getOne(id);
        paymentBean.setStatus(PaymentStatusEnum.COMPLETE.getStatus());
        paymentDao.save(paymentBean);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reject(int id) {
        PaymentBean paymentBean = paymentDao.getOne(id);
        paymentBean.setStatus(PaymentStatusEnum.REJECTED.getStatus());
        paymentDao.save(paymentBean);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(CreatePaymentRequestModel createPaymentRequestModel) {
        PaymentBean paymentBean = new PaymentBean();
        paymentBean.setAmount(createPaymentRequestModel.getAmount());
        paymentBean.setMethod("FPX");

        ResidentCredentialBean residentCredentialBean = residentCredentialDao.findByUsername(createPaymentRequestModel.getUsername());
        ResidentBean residentBean = residentDao.getOne(residentCredentialBean.getResidentId());
        StatementBean statementBean = statementDao.getOne(createPaymentRequestModel.getStatementId());
        paymentBean.setReference(String.format("%s%s_%s", statementBean.getYear(), statementBean.getMonth(), residentBean.getId()));
        paymentBean.setStatus(PaymentStatusEnum.PENDING.getStatus());
        paymentBean.setStatementId(statementBean.getId());
        paymentDao.save(paymentBean);
    }
}
