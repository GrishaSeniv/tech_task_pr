package technical.task.card_order.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-26
 */
@RestController
@RequestMapping("/api/v1/reports")
@PreAuthorize("hasAnyAuthority('ADMIN')")
class ReportController {
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
    private final ReportService reportService;

    ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/generate")
    public ResponseEntity<ReportResponse> generateReport() {
        ReportResponse reportResponse = reportService.generateReport();
        return ResponseEntity.ok(reportResponse);
    }

    @GetMapping("/export")
    public ResponseEntity<String> exportReport() {
        return reportService.exportReport();
    }
}
