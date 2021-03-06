package com.nttu.dacnsv.Controller;

import com.nttu.dacnsv.Model.Bill;
import com.nttu.dacnsv.Request.DeleteByIdRequest;
import com.nttu.dacnsv.Request.ServiceResult;
import com.nttu.dacnsv.Service.BillService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/Bill")
@AllArgsConstructor
public class BillController {
    public final BillService service;

    @GetMapping //Get all user from database
    public ResponseEntity<ServiceResult> getAllBill() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @PostMapping
    public ResponseEntity<ServiceResult> addBill(@RequestBody Bill bill) {
        return ResponseEntity.ok().body(service.add(bill));
    }

    @PutMapping
    public ResponseEntity<ServiceResult> updateBill(@RequestBody Bill bill) {
        return ResponseEntity.ok().body(service.update(bill));
    }

    @DeleteMapping
    public ResponseEntity<ServiceResult> deleteBill(@RequestBody DeleteByIdRequest req) {
        return ResponseEntity.ok().body(service.delete(req.getId()));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ServiceResult> findBillByStatus(@PathVariable("status") String status) {
        return ResponseEntity.ok().body(service.findByStatus(status));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ServiceResult> findBillById(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/u={userName}&s={status}")
    public ResponseEntity<ServiceResult> findBillByUserNameAndStatus(@PathVariable("userName") String userName, @PathVariable("status")String status) {
        return ResponseEntity.ok().body(service.findByUserAndStatus(userName, status));
    }
    @GetMapping("/u={userName}")
    public ResponseEntity<ServiceResult> findBillByUserName(@PathVariable("userName") String userName) {
        return ResponseEntity.ok().body(service.findByUser(userName));
    }
}
