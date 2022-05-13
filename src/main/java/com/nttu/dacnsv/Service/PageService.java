package com.nttu.dacnsv.Service;

import com.nttu.dacnsv.Repository.BillRepository;
import com.nttu.dacnsv.Repository.CarRepository;
import com.nttu.dacnsv.Repository.UserRepository;
import com.nttu.dacnsv.Request.PageServiceResult;
import com.nttu.dacnsv.Request.ServiceResult;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PageService {
    private final BillRepository billRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final long ITEM_IN_PAGE = 5l;


    public PageServiceResult billPage(long n) {
        PageServiceResult result = new PageServiceResult();
        long num = Long.valueOf(billRepository.findAll().size());
        result.setTotalPage((num + ITEM_IN_PAGE - 1) / ITEM_IN_PAGE);
        result.setCurrentPage(n);
        result.setData(billRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate")).stream().skip((n - 1) * ITEM_IN_PAGE).limit(ITEM_IN_PAGE));
        result.setMessage("SUCCESS");
        return result;
    }

    public PageServiceResult userPage(long n) {
        PageServiceResult result = new PageServiceResult();
        long num = Long.valueOf(userRepository.findAll().size());
        result.setTotalPage((num + ITEM_IN_PAGE - 1) / ITEM_IN_PAGE);
        result.setCurrentPage(n);
        result.setData(userRepository.findAll().stream().skip((n - 1) * ITEM_IN_PAGE).limit(ITEM_IN_PAGE));
        result.setMessage("SUCCESS");
        return result;
    }

    public PageServiceResult carPage(long n) {
        PageServiceResult result = new PageServiceResult();
        long num = Long.valueOf(carRepository.findAll().size());
        result.setTotalPage((num + ITEM_IN_PAGE - 1) / ITEM_IN_PAGE);
        result.setCurrentPage(n);
        result.setData(carRepository.findAll().stream().skip((n - 1) * ITEM_IN_PAGE).limit(ITEM_IN_PAGE));
        result.setMessage("SUCCESS");
        return result;
    }

    public PageServiceResult findBillByStatus(String status, long n) {
        PageServiceResult result = new PageServiceResult();
        long num = Long.valueOf(billRepository.findByStatus(status).size());
        result.setTotalPage((num + ITEM_IN_PAGE - 1) / ITEM_IN_PAGE);
        result.setData(billRepository.findByStatus(status).stream().skip((n - 1) * ITEM_IN_PAGE).limit(ITEM_IN_PAGE));
        result.setMessage("SUCCESS");
        return result;
    }
}
