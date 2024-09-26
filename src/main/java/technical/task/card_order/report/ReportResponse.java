package technical.task.card_order.report;

import java.util.List;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-26
 */
public class ReportResponse {
    private long clientCount;
    private long orderCount;
    private List<OperatorReportResponse> operatorReports;

    public long getClientCount() {
        return clientCount;
    }

    public ReportResponse setClientCount(long clientCount) {
        this.clientCount = clientCount;
        return this;
    }

    public long getOrderCount() {
        return orderCount;
    }

    public ReportResponse setOrderCount(long orderCount) {
        this.orderCount = orderCount;
        return this;
    }

    public List<OperatorReportResponse> getOperatorReports() {
        return operatorReports;
    }

    public ReportResponse setOperatorReports(List<OperatorReportResponse> operatorReports) {
        this.operatorReports = operatorReports;
        return this;
    }

    @Override
    public String toString() {
        return "ReportResponse{" +
                "clientCount=" + clientCount +
                ", orderCount=" + orderCount +
                ", operatorReports=" + operatorReports +
                '}';
    }

    public static class OperatorReportResponse {
        private String operatorLogin;
        private String operatorFLN;
        private int operatorOrderCount;

        public String getOperatorLogin() {
            return operatorLogin;
        }

        public OperatorReportResponse setOperatorLogin(String operatorLogin) {
            this.operatorLogin = operatorLogin;
            return this;
        }

        public String getOperatorFLN() {
            return operatorFLN;
        }

        public OperatorReportResponse setOperatorFLN(String operatorFLN) {
            this.operatorFLN = operatorFLN;
            return this;
        }

        public int getOperatorOrderCount() {
            return operatorOrderCount;
        }

        public OperatorReportResponse setOperatorOrderCount(int operatorOrderCount) {
            this.operatorOrderCount = operatorOrderCount;
            return this;
        }

        @Override
        public String toString() {
            return "OperatorReportResponse{" +
                    "operatorLogin='" + operatorLogin + '\'' +
                    ", operatorFLN='" + operatorFLN + '\'' +
                    ", operatorOrderCount=" + operatorOrderCount +
                    '}';
        }
    }
}
