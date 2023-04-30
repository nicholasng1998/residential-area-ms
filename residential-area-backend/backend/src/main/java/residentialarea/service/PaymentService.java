package residentialarea.service;

import org.springframework.data.domain.Page;
import residentialarea.model.PaymentResponseModel;

public interface PaymentService {
    Page<PaymentResponseModel> readPayments(int pageSize, int pageNumber);

    void complete(int id);

    void reject(int id);
}
