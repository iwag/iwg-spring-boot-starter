package biz.iwag.blog.config;

import com.codahale.metrics.MetricRegistry;

import org.h2.server.web.WebServlet;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.util.ReflectionTestUtils;

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


    private MetricRegistry metricRegistry;

    @Before
    public void setup() {
        servletContext = spy(new MockServletContext());
        doReturn(mock(FilterRegistration.Dynamic.class))
            .when(servletContext).addFilter(anyString(), any(Filter.class));
        doReturn(mock(ServletRegistration.Dynamic.class))
            .when(servletContext).addServlet(anyString(), any(Servlet.class));

        env = new MockEnvironment();

        webConfigurer = new WebConfigurer(env);
        metricRegistry = new MetricRegistry();
        webConfigurer.setMetricRegistry(metricRegistry);
    }

    @Test
    public void testStartUpProdServletContext() throws ServletException {
        env.setActiveProfiles(Constants.SPRING_PROFILE_PRODUCTION);
        webConfigurer.onStartup(servletContext);

//        assertThat(servletContext.getAttribute(InstrumentedFilter.REGISTRY_ATTRIBUTE)).isEqualTo(metricRegistry);
//        assertThat(servletContext.getAttribute(MetricsServlet.METRICS_REGISTRY)).isEqualTo(metricRegistry);
//        verify(servletContext).addFilter(eq("webappMetricsFilter"), any(InstrumentedFilter.class));
//        verify(servletContext).addServlet(eq("metricsServlet"), any(MetricsServlet.class));
        verify(servletContext, never()).addServlet(eq("H2Console"), any(WebServlet.class));
    }

    @Test
    public void testStartUpDevServletContext() throws ServletException {
        env.setActiveProfiles(Constants.SPRING_PROFILE_DEVELOPMENT);
        webConfigurer.onStartup(servletContext);

//        assertThat(servletContext.getAttribute(InstrumentedFilter.REGISTRY_ATTRIBUTE)).isEqualTo(metricRegistry);
//        assertThat(servletContext.getAttribute(MetricsServlet.METRICS_REGISTRY)).isEqualTo(metricRegistry);
//        verify(servletContext).addFilter(eq("webappMetricsFilter"), any(InstrumentedFilter.class));
//        verify(servletContext).addServlet(eq("metricsServlet"), any(MetricsServlet.class));
//        verify(servletContext).addServlet(eq("H2Console"), any(WebServlet.class));
    }

}
