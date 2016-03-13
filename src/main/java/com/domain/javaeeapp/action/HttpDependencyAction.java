package com.domain.javaeeapp.action;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.microsoft.applicationinsights.TelemetryClient;
import com.microsoft.applicationinsights.telemetry.Duration;

@RequestScoped
@Named
public class HttpDependencyAction {
	public String doRequest() {
		Instant start = Instant.now();
		Instant end;
		TelemetryClient telemetry = new TelemetryClient();
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			// もしくは
			// try (CloseableHttpClient httpClient =
			// HttpClientBuilder.create().build()) {
			HttpGet getMethod = new HttpGet("https://github.com/Microsoft/ApplicationInsights-Java");

			try (CloseableHttpResponse response = httpClient.execute(getMethod)) {
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity entity = response.getEntity();
					System.out.println(EntityUtils.toString(entity, StandardCharsets.UTF_8));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			end = Instant.now();
			Duration duration = new Duration(java.time.Duration.between(start, end).toMillis());
			telemetry.trackDependency("GithubHttp-Dependency", "Http", duration, false);
		}
		end = Instant.now();
		Duration duration = new Duration(java.time.Duration.between(start, end).toMillis());
		telemetry.trackDependency("GithubHttp-Dependency", "Http", duration, true);

		// Duration of Application Insights is milliseconds
		// https://github.com/Microsoft/ApplicationInsights-Java/blob/master/core/src/main/java/com/microsoft/applicationinsights/telemetry/Duration.java

		return "0";
	}
}
