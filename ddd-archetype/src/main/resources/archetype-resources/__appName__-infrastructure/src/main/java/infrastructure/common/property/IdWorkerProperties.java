#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.common.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "ddd.id-worker")
public class IdWorkerProperties {

    private List<String> cluster;
    private int skip;

    public List<String> getCluster() {
        return cluster;
    }

    public void setCluster(List<String> cluster) {
        this.cluster = cluster;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }
}
