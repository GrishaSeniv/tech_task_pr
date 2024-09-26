package technical.task.card_order.report;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import technical.task.card_order.domain.ClientServiceAware;
import technical.task.card_order.domain.OrderServiceAware;
import technical.task.card_order.domain.UserServiceAware;
import technical.task.card_order.domain.model.UserReportProjection;

import java.io.StringWriter;
import java.util.List;

import static technical.task.card_order.report.Converter.toOperatorReportResponses;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-26
 */
@Service
class ReportService {
    private static final Logger logger = LoggerFactory.getLogger(ReportService.class);
    private final ClientServiceAware clientServiceAware;
    private final OrderServiceAware orderServiceAware;
    private final UserServiceAware userServiceAware;

    ReportService(ClientServiceAware clientServiceAware,
                  OrderServiceAware orderServiceAware,
                  UserServiceAware userServiceAware) {
        this.clientServiceAware = clientServiceAware;
        this.orderServiceAware = orderServiceAware;
        this.userServiceAware = userServiceAware;
    }

    public ReportResponse generateReport() {
        logger.info("Generate report");
        long clientCount = clientServiceAware.getClientCount();
        long orderCount = orderServiceAware.getOrdersCount();
        List<UserReportProjection> userReport = userServiceAware.getUserReport();
        ReportResponse reportResponse = new ReportResponse()
                .setClientCount(clientCount)
                .setOrderCount(orderCount)
                .setOperatorReports(toOperatorReportResponses(userReport));
        logger.info("Generated report: {}", reportResponse);
        return reportResponse;
    }

    public ResponseEntity<String> exportReport() {
        ReportResponse reportResponse = generateReport();
        List<ReportResponse.OperatorReportResponse> operatorReports = reportResponse.getOperatorReports();
        logger.info("Export report: {}", reportResponse);
        StringWriter writer = new StringWriter();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.builder().setHeader("Загальна кількість клієнтів", "Загальна кількість замовлень", "Логін оператора", "ПІБ оператора", "Оброблено замовлень оператором").build())) {
            for (ReportResponse.OperatorReportResponse record : operatorReports) {
                csvPrinter.printRecord(reportResponse.getClientCount(), reportResponse.getOrderCount(), record.getOperatorLogin(), record.getOperatorFLN(), record.getOperatorOrderCount());
            }
        } catch (Exception e) {
            String msg = "Something went wrong while exporting report";
            logger.error(msg);
            throw new RuntimeException(msg, e);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=user_order_report.csv");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.TEXT_PLAIN)
                .body(writer.toString());
    }
}
