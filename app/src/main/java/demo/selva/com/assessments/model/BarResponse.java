package demo.selva.com.assessments.model;

import java.util.List;

/**
 * @author selva.raman
 * @version 1.0
 * @since 6/14/2018
 */
public class BarResponse {
    private List<BarValues> graph;

    public List<BarValues> getGraph() {
        return graph;
    }


    public class BarValues {
        private String index;
        private String value;

        public String getIndex() {
            return index;
        }

        public String getValue() {
            return value;
        }
    }
}
