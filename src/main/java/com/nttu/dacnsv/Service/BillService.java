package com.nttu.dacnsv.Service;

import com.nttu.dacnsv.Model.Bill;
import com.nttu.dacnsv.Repository.BillRepository;
import com.nttu.dacnsv.Request.ServiceResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class BillService {
    private final BillRepository repository;

    public ServiceResult getAll() {
        ServiceResult result = new ServiceResult();
        result.setData(repository.findAll());
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
}
