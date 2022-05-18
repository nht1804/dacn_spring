package com.nttu.dacnsv.Controller;

import com.nttu.dacnsv.Request.ServiceResult;
import com.nttu.dacnsv.Service.DashBoardService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/DashBoard")
@AllArgsConstructor
public class DashBoardController {
    private final DashBoardService dashBoardService;

    @GetMapping("/Bill/Car")
    public ResponseEntity<ServiceResult> getBillCarCountFromToDate(@RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from, @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to) {
        return ResponseEntity.ok().body(dashBoardService.billCarCountFromToDate(from, to));
    }

    @GetMapping("/Bill/User")
    public ResponseEntity<ServiceResult> billUserCountFromToDate(@RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from, @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to) {
        return ResponseEntity.ok().body(dashBoardService.billUserCountFromToDate(from, to));
    }

    @GetMapping("/Bill/UserTotal")
    public ResponseEntity<ServiceResult> billUserTotalCountFromToDate(@RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from, @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to) {
        return ResponseEntity.ok().body(dashBoardService.billUserTotalCountFromToDate(from, to));
    }

    @GetMapping("/Bill")
    public ResponseEntity<ServiceResult> billTotalMonthInYear(@RequestParam("year") @DateTimeFormat(pattern = "yyyy") String year) {
        return ResponseEntity.ok().body(dashBoardService.billTotalMonthInYear(year));
    }

    @GetMapping("/Bill/Last7Days")
    public ResponseEntity<ServiceResult> billCountLast7DaysFrom(@RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from, @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to) {
        return ResponseEntity.ok().body(dashBoardService.billCountLast7DaysFrom(from, to));
    }

    @GetMapping("/Bill/total")
    public ResponseEntity<ServiceResult> billTotal() {
        return ResponseEntity.ok().body(dashBoardService.billCount());
    }

    @GetMapping("/User/total")
    public ResponseEntity<ServiceResult> userTotal() {
        return ResponseEntity.ok().body(dashBoardService.userCount());
    }

    @GetMapping("/Car/total")
    public ResponseEntity<ServiceResult> carTotal() {
        return ResponseEntity.ok().body(dashBoardService.carCount());
    }

}
