package com.nttu.dacnsv.Service;

import com.nttu.dacnsv.Model.Bill;
import com.nttu.dacnsv.Model.User;
import com.nttu.dacnsv.Repository.BillRepository;
import com.nttu.dacnsv.Repository.CarRepository;
import com.nttu.dacnsv.Repository.UserRepository;
import com.nttu.dacnsv.Request.ServiceResult;
import lombok.AllArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;


@Service
@AllArgsConstructor
public class DashBoardService {
    private final BillRepository billRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public ServiceResult billCarCountFromToDate(LocalDate from, LocalDate to) {
        ServiceResult result = new ServiceResult();
        result.setMessage("SUCCESS");
        result.setData(billRepository.billCarCountFromToDate(from.toString(), to.toString()));
        return result;
    }

    public ServiceResult billUserCountFromToDate(LocalDate from, LocalDate to) {
        ServiceResult result = new ServiceResult();
        result.setMessage("SUCCESS");
        result.setData(billRepository.billUserCountFromToDate(from.toString(), to.toString()));
        return result;
    }

    public ServiceResult billTotalMonthInYear(String year) {
        ServiceResult result = new ServiceResult();
        result.setMessage("SUCCESS");
        result.setData(billRepository.billTotalMonthInYear(year));
        return result;
    }

    public ServiceResult billUserTotalCountFromToDate(LocalDate from, LocalDate to) {
        ServiceResult result = new ServiceResult();
        result.setMessage("SUCCESS");
        result.setData(billRepository.billUserTotalCountFromToDate(from.toString(), to.toString()));
        return result;
    }

    public ServiceResult billCount() {
        ServiceResult result = new ServiceResult();
        result.setMessage("SUCCESS");
        result.setData(billRepository.billCount());
        return result;
    }

    public ServiceResult userCount() {
        ServiceResult result = new ServiceResult();
        result.setMessage("SUCCESS");
        result.setData(userRepository.userCount());
        return result;
    }

    public ServiceResult carCount() {
        ServiceResult result = new ServiceResult();
        result.setMessage("SUCCESS");
        result.setData(carRepository.carCount());
        return result;
    }
    public ServiceResult billCountLast7DaysFrom(LocalDate from, LocalDate to) {
        ServiceResult result = new ServiceResult();
        result.setMessage("SUCCESS");
        result.setData(billRepository.billCountLast7DaysFrom(from.toString(), to.toString()));
        return result;
    }
}
