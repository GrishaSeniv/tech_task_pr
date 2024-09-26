package technical.task.card_order.report;

import technical.task.card_order.domain.model.UserReportProjection;

import java.util.List;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-26
 */
class Converter {
    static List<ReportResponse.OperatorReportResponse> toOperatorReportResponses(List<UserReportProjection> reportProjections) {
        return reportProjections.stream()
                .map(Converter::toOperatorReportResponse)
                .toList();
    }

    static ReportResponse.OperatorReportResponse toOperatorReportResponse(UserReportProjection reportProjection) {
        return new ReportResponse.OperatorReportResponse()
                .setOperatorLogin(reportProjection.getLogin())
                .setOperatorFLN(reportProjection.getFullName())
                .setOperatorOrderCount(reportProjection.getOrdersCount());
    }
}
