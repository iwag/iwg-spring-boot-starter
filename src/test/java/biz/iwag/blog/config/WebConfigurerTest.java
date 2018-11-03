package biz.iwag.blog.config;

import com.codahale.metrics.MetricRegistry;
import io.undertow.Undertow;
import io.undertow.Undertow.Builder;
import io.undertow.UndertowOptions;

import org.h2.server.web.WebServlet;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.util.ReflectionTestUtils;
import org.xnio.OptionMap;

import javax.servlet.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;

/**
 * Unit tests for the WebConfigurer class.
 *
 * @see WebConfigurer
 */
public class WebConfigurerTest {

    private WebConfigurer webConfigurer;

    private MockServletContext servletContext;

    private MockEnvironment env;

    private CoreProperties props;

    private MetricRegistry metricRegistry;

    @Before
    public void setup() {
        servletContext = spy(new MockServletContext());
        doReturn(mock(FilterRegistration.Dynamic.class))
            .when(servletContext).addFilter(anyString(), any(Filter.class));
        doReturn(mock(ServletRegistration.Dynamic.class))
            .when(servletContext).addServlet(anyString(), any(Servlet.class));

        env = new MockEnvironment();
        props = new CoreProperties();

        webConfigurer = new WebConfigurer(env, props);
        metricRegistry = new MetricRegistry();
        webConfigurer.setMetricRegistry(metricRegistry);
    }

    @Test
    public void testStartUpProdServletContext() throws ServletException {
        env.setActiveProfiles(CoreConstants.SPRING_PROFILE_PRODUCTION);
        webConfigurer.onStartup(servletContext);

//        assertThat(servletContext.getAttribute(InstrumentedFilter.REGISTRY_ATTRIBUTE)).isEqualTo(metricRegistry);
//        assertThat(servletContext.getAttribute(MetricsServlet.METRICS_REGISTRY)).isEqualTo(metricRegistry);
//        verify(servletContext).addFilter(eq("webappMetricsFilter"), any(InstrumentedFilter.class));
//        verify(servletContext).addServlet(eq("metricsServlet"), any(MetricsServlet.class));
        verify(servletContext, never()).addServlet(eq("H2Console"), any(WebServlet.class));
    }

    @Test
    public void testStartUpDevServletContext() throws ServletException {
        env.setActiveProfiles(CoreConstants.SPRING_PROFILE_DEVELOPMENT);
        webConfigurer.onStartup(servletContext);

//        assertThat(servletContext.getAttribute(InstrumentedFilter.REGISTRY_ATTRIBUTE)).isEqualTo(metricRegistry);
//        assertThat(servletContext.getAttribute(MetricsServlet.METRICS_REGISTRY)).isEqualTo(metricRegistry);
//        verify(servletContext).addFilter(eq("webappMetricsFilter"), any(InstrumentedFilter.class));
//        verify(servletContext).addServlet(eq("metricsServlet"), any(MetricsServlet.class));
//        verify(servletContext).addServlet(eq("H2Console"), any(WebServlet.class));
    }

    @Test
    public void testCustomizeServletContainer() {
        env.setActiveProfiles(CoreConstants.SPRING_PROFILE_PRODUCTION);
        UndertowServletWebServerFactory container = new UndertowServletWebServerFactory();
        webConfigurer.customize(container);
        assertThat(container.getMimeMappings().get("abs")).isEqualTo("audio/x-mpeg");
        assertThat(container.getMimeMappings().get("html")).isEqualTo("text/html;charset=utf-8");
        assertThat(container.getMimeMappings().get("json")).isEqualTo("text/html;charset=utf-8");

        Builder builder = Undertow.builder();
        container.getBuilderCustomizers().forEach(c -> c.customize(builder));
        OptionMap.Builder serverOptions = (OptionMap.Builder) ReflectionTestUtils.getField(builder, "serverOptions");
        assertThat(serverOptions.getMap().get(UndertowOptions.ENABLE_HTTP2)).isNull();
    }


}
