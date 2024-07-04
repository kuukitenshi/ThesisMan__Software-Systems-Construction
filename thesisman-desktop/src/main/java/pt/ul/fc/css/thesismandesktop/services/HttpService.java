package pt.ul.fc.css.thesismandesktop.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.FileBody;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import pt.ul.fc.css.thesismandesktop.services.exceptions.HttpRequestException;

import java.io.File;
import java.io.IOException;

public final class HttpService {

  private static HttpService instance;

  private final HttpClient httpClient = HttpClientBuilder.create().build();
  private final ObjectMapper objectMapper = new ObjectMapper();

  private HttpService() {
  }

  public <T> T get(String endpoint, Class<? extends T> clazz) throws HttpRequestException {
    HttpGet request = new HttpGet(endpoint);
    return executeRequest(request, clazz);
  }

  public <T> T post(String endpoint, Object body, Class<? extends T> clazz) throws HttpRequestException {
    HttpPost request = new HttpPost(endpoint);
    request.setHeader("Content-Type", "application/json");
    try {
      String json = this.objectMapper.writeValueAsString(body);
      request.setEntity(new StringEntity(json));
      return executeRequest(request, clazz);
    } catch (JsonProcessingException e) {
      throw new HttpRequestException(-2);
    }
  }

  public <T> T uploadFile(String endpoint, File file, Class<? extends T> clazz) throws HttpRequestException {
    HttpPost request = new HttpPost(endpoint);
    HttpEntity entity = MultipartEntityBuilder.create().addPart("file", new FileBody(file)).build();
    request.setEntity(entity);
    return executeRequest(request, clazz);
  }

  public <T> T delete(String endpoint, Class<? extends T> clazz) throws HttpRequestException {
    HttpDelete request = new HttpDelete(endpoint);
    return executeRequest(request, clazz);
  }

  private <T> T executeRequest(ClassicHttpRequest request, Class<T> clazz) throws HttpRequestException {
    try {
      HttpResponseContent response = this.httpClient.execute(request, res -> {
        HttpEntity entity = res.getEntity();
        String body = EntityUtils.toString(entity);
        return new HttpResponseContent(res.getCode(), body);
      });
      if (response.statusCode() < 200 || response.statusCode() > 299) {
        throw new HttpRequestException(response.statusCode());
      }
      return this.objectMapper.readValue(response.content(), clazz);
    } catch (IOException e) {
      throw new HttpRequestException(-1, e);
    }
  }

  public static synchronized HttpService getInstance() {
    if (instance == null) {
      instance = new HttpService();
    }
    return instance;
  }
}
