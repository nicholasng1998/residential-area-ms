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
import residentialarea.bean.EmergencyRequestBean;
import residentialarea.bean.PaymentBean;
import residentialarea.bean.ResidentBean;
import residentialarea.constant.EmergencyRequestStatusEnum;
import residentialarea.constant.PaymentStatusEnum;
import residentialarea.dao.PaymentDao;
import residentialarea.model.PaymentResponseModel;
import residentialarea.model.ResidentResponseModel;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final PaymentDao paymentDao;

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
}
