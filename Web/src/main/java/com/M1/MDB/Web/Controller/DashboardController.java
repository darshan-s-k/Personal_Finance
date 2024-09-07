package com.M1.MDB.Web.Controller;

import com.M1.MDB.Web.Model.Transaction;
import com.M1.MDB.Web.Service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/transactions")
    public List<Transaction> getTransactionsByDateRange(
            @RequestParam String userId,
            @RequestParam String startDate,
            @RequestParam String endDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = sdf.parse(startDate);
        Date end = sdf.parse(endDate);
        return dashboardService.getTransactionsByDateRange(userId, start, end);
    }
}
