package com.nttu.dacnsv.Controller;

import com.nttu.dacnsv.Model.User;
import com.nttu.dacnsv.Request.PageServiceResult;
import com.nttu.dacnsv.Request.ServiceResult;
import com.nttu.dacnsv.Service.PageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/")
@AllArgsConstructor
public class PageController {
    public final PageService pageService;

    @GetMapping("/Bill/page/{page}")
    public ResponseEntity<PageServiceResult> bill(@PathVariable("page") long page) {
        return ResponseEntity.ok().body(pageService.billPage(page));
    }
    @GetMapping("/User/page/{page}")
    public ResponseEntity<PageServiceResult> user(@PathVariable("page") long page) {
        return ResponseEntity.ok().body(pageService.userPage(page));
    }
    @GetMapping("/Car/page/{page}")
    public ResponseEntity<PageServiceResult> car(@PathVariable("page") long page) {
        return ResponseEntity.ok().body(pageService.carPage(page));
    }
    @GetMapping("/Bill/status/{status}/page/{page}")
    public ResponseEntity<PageServiceResult> billByStatus(@PathVariable String status,@PathVariable("page") long page) {
        return ResponseEntity.ok().body(pageService.findBillByStatus(status,page));
    }
}
