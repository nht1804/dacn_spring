package com.nttu.dacnsv.Service;

import com.nttu.dacnsv.Model.Bill;
import com.nttu.dacnsv.Repository.BillRepository;
import com.nttu.dacnsv.Request.ServiceResult;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BillService {
    private final BillRepository repository;

    public ServiceResult getAll() {
        ServiceResult result = new ServiceResult();
        result.setData(repository.findAll(Sort.by(Sort.Direction.DESC, "createDate")));
        result.setMessage("SUCCESS");
        return result;
    }

    public ServiceResult add(Bill bill) {
        ServiceResult result = new ServiceResult();
        bill.setCreateDate(LocalDateTime.now());
        bill.setStatus(Bill.BillStatus.WAITING);
        repository.insert(bill);
        result.setMessage("SUCCESS");
        return result;
    }

    public ServiceResult update(Bill bill) {
        ServiceResult result = new ServiceResult();
        repository.save(bill);
        result.setMessage("SUCCESS");
        return result;
    }

    public ServiceResult delete(String id) {
        ServiceResult result = new ServiceResult();
        repository.deleteById(id);
        result.setMessage("SUCCESS");
        return result;
    }

    public ServiceResult findByStatus(String status) {
        ServiceResult result = new ServiceResult();
        result.setMessage("SUCCESS");
        result.setData(repository.findByStatus(status));
        return result;
    }

    public ServiceResult findByUserAndStatus(String userName, String status) {
        ServiceResult result = new ServiceResult();
        List<Bill> bills = repository.findByUserAndStatus(userName, status);
        if (bills.isEmpty()) {
            result.setMessage("NO BILLS");
            result.setStatus(ServiceResult.Status.FAILED);
        } else {
            result.setData(bills);
        }
        return result;
    }

    public ServiceResult findByUser(String userName) {
        ServiceResult result = new ServiceResult();
        List<Bill> bills = repository.findByUser(userName,Sort.by(Sort.Direction.DESC, "createDate"));
        if (bills.isEmpty()) {
            result.setMessage("No bills");
            result.setStatus(ServiceResult.Status.FAILED);
        } else {
            result.setData(bills);
        }
        return result;
    }
}
