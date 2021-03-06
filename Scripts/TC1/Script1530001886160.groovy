import org.apache.http.Header
import org.apache.http.HttpHost
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.methods.HttpHead
import org.apache.http.impl.client.HttpClients

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * see https://forum.katalon.com/discussion/7571/verify-response-is-pdf
 */

String url = "http://files.shareholder.com/downloads/AAPL/6323171818x0xS320193-17-70/320193/filing.pdf"

HttpClient httpclient = HttpClients.custom().build()

// or in case you are behind companies Proxy server
/*
HttpHost proxy = new HttpHost('172.24.2.10', 8080);
RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
HttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(config).build();
 */

HttpHead headRequest = new HttpHead(url);
headRequest.setHeader("Content-Type", "application/pdf");

// make a request and get a response
HttpResponse response = httpclient.execute(headRequest);

// check the response status
def statusCode = response.getStatusLine().getStatusCode()

// check a Content-Type header
Header contentType = response.getFirstHeader("Content-Type");
String value = contentType.getValue()

// show the check result
WebUI.comment(">>> statudCode=${statusCode} contentType=${value}")

